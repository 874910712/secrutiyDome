package org.chen.demo.config;

import org.chen.demo.authentication.SetAuthenticationFailureHandler;
import org.chen.demo.authentication.SetAuthenticationSuccessHandler;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.chen.mysecurity.core.filter.ValidateCodeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @ClassName: BrowerSecurityConfig
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/21 22:55
 * @Version: 1.0
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MySecurityProperties mySecurityProperties;
//    /*自定义成功处理器*/
//    @Resource
//    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
//    /*自定义失败处理器*/
//    @Resource
//    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    /*自定义的登录处理配置类*/
    @Resource
    private SetAuthenticationSuccessHandler setAuthenticationSuccessHandler;
    @Resource
    private SetAuthenticationFailureHandler setAuthenticationFailureHandler;
    /*配置文件中的数据源对象*/
    @Resource
    private DataSource dataSource;
    @Resource
    private UserDetailsService userDetailsService;

    /*配置加密器*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        /*默认加密器*/
        return  new BCryptPasswordEncoder();


        /*返回自定义加密方法*/
//        return  new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return null;
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return false;
//            }
//        };
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .and()
//                .userDetailsService(myCustomUserDetailsService);
//    }
    //配置登录页记住我功能
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //设置数据源
        tokenRepository.setDataSource(dataSource);
        //在系统启动时自动生成表记录用户和token关系（只能在第一次启动时使用，第二次应该注释掉否则会报错）
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }





    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //自定义的验证码类
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        //传入自定义的登陆错误处理方法类
        validateCodeFilter.setAuthenticationFailureHandler(setAuthenticationFailureHandler);
        //传入自定义配置文件
        validateCodeFilter.setMySecurityProperties(mySecurityProperties);
        //调用方法读取配置
        validateCodeFilter.afterPropertiesSet();



        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)  /*添加自定义的验证码过滤器*/
                .formLogin()
                    .loginPage("/authentication/require")/*配置登录页面*/
                    .loginProcessingUrl("/authentication/form")/*登录URL*/
                    .successHandler(setAuthenticationSuccessHandler) /*设置使用自定义的登录成功逻辑*/
                    .failureHandler(setAuthenticationFailureHandler) /*设置使用自定义登录失败处理逻辑*/
                .and()
                    /*登录页码记住我功能配置*/
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(mySecurityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",mySecurityProperties.getBrowser().getLoginPage(),"/getValidateCode").permitAll()/*配置放行路径*/
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();/*关闭跨站伪造请求防护*/

    }
}
