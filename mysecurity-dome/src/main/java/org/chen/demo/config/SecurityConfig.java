package org.chen.demo.config;

import org.chen.demo.authentication.MyAuthenticationFailureHandler;
import org.chen.demo.authentication.MyAuthenticationSuccessHandler;
import org.chen.demo.authentication.SetAuthenticationFailureHandler;
import org.chen.demo.authentication.SetAuthenticationSuccessHandler;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

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
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")/*配置登录页码*/
                .loginProcessingUrl("/authentication/form")/*登录URL*/
                .successHandler(setAuthenticationSuccessHandler) /*设置使用自定义的登录成功逻辑*/
                .failureHandler(setAuthenticationFailureHandler) /*设置使用自定义登录失败处理逻辑*/
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",mySecurityProperties.getBrowser().getLoginPage()).permitAll()/*配置放行路径*/
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();/*关闭跨站伪造请求防护*/

    }
}
