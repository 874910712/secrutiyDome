package org.chen.mysecurity.core.filter;

import org.apache.commons.lang.StringUtils;
import org.chen.mysecurity.core.entity.ImageCode;
import org.chen.mysecurity.core.inter.validateCode.AbstractValidateCodeProcessor;
import org.chen.mysecurity.core.inter.validateCode.ValidateCodeProcessor;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.chen.mysecurity.core.properties.controller.ValidateCodeController;
import org.chen.mysecurity.core.exception.ValidateCodeException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: ValidateCodeFilter
 * @Description: 自定义验证码过滤器
 * @Author: chengui
 * @Date: 2020/6/27 22:00
 * @Version: 1.0
 **/
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    //保存传入的失败处理器
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    //用于存放配置文件中配置的需要拦截的url
    private Set<String> urls = new HashSet<String>();
    //自定义配置类
    private MySecurityProperties mySecurityProperties;

    //spring工具类
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String userConfigUrls = mySecurityProperties.getCode().getImage().getUrl();
        if(StringUtils.isNotEmpty(userConfigUrls)){
            //获取配置文件里的所有url路径
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(userConfigUrls,",");
            for (String url:configUrls){
                urls.add(url);
            }
        }

        //添加默认的登录请求路径
        urls.add("/authentication/form");

    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //路径是否能与配置路径匹配的标识符
        boolean action = false;
        //循环匹配
        for (String url:urls){
            //如果请求路径能够和配置文件中的路径匹配上
            if(pathMatcher.match(url,request.getRequestURI())){
                action = true;
            }
        }
        //如果路径匹配成功则进行验证码校验
        if(action){
            try{
                validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                //调用传入的自定义失败处理器
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);

//        //判断请求路径是否为登录请求路径并且请求方式为post方式
//        if(StringUtils.equals("/authentication/form",request.getRequestURI())
//                &&StringUtils.equalsIgnoreCase(request.getMethod(),"post")){
//            try{
//                validate(new ServletWebRequest(request));
//            }catch (ValidateCodeException e){
//                //调用传入的自定义失败处理器
//                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
//                return;
//            }
//        }

    }
    /*
     * @Author:陈贵
     * @Description：校验验证码的方法
     * @param
     * @param null
     * @return void
     * @Date：2020/6/27 22:24
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        String codeType = StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
        //从Session中获取验证码
        ImageCode sessionCode = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+codeType);
        //从请求中获取用户输入验证码
        String requestCode = ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode");
        if(StringUtils.isBlank(requestCode)){
            throw new ValidateCodeException("验证码不能为空！");
        }
        if(sessionCode == null){
            throw new ValidateCodeException("验证码不存在！");
        }
        //如果验证码过期
        if(sessionCode.isExpried()){
            sessionStrategy.removeAttribute(request,ValidateCodeProcessor.SESSION_KEY_PREFIX+codeType);
            throw new ValidateCodeException("验证码已过期！");
        }

        if(!StringUtils.equals(sessionCode.getCode(),requestCode)){
            throw new ValidateCodeException("输入的验证码有误！");
        }
        //移除验证码
        sessionStrategy.removeAttribute(request,ValidateCodeProcessor.SESSION_KEY_PREFIX+codeType);

    }



    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public MySecurityProperties getMySecurityProperties() {
        return mySecurityProperties;
    }

    public void setMySecurityProperties(MySecurityProperties mySecurityProperties) {
        this.mySecurityProperties = mySecurityProperties;
    }


}
