package org.chen.demo.controller;

import org.chen.demo.entity.MyResponse;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/21 22:42
 * @Version: 1.0
 **/
@RestController
public class LoginController {

    /*缓存请求*/
    private RequestCache requestCache = new HttpSessionRequestCache ();
    /*跳转器*/
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private MySecurityProperties mySecurityProperties;
    /*
     * @Author:陈贵
     * @Description：获取当前登录的用户信息
     * @param
     * @param null
     * @return java.lang.Object
     * @Date：2020/6/27 20:03
     */
    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication){
        return authentication;
    }

    @RequestMapping("/logintest")
    public String logintest(){

        return "hello Security!";
    }

    /*
     * @Author:陈贵
     * @Description：当需要身份认证时调转到这里
     * @param
     * @param null
     * @return java.lang.String
     * @Date：2020/6/23 21:11
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)/*返回401*/
    public MyResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            //获取引发跳转请求的url
            String targetUrl = savedRequest.getRedirectUrl();
            System.out.println("引发跳转请求的URL为"+ targetUrl);
            //如果是以html结尾
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,mySecurityProperties.getBrowser().getLoginPage());
            }
        }
        return new MyResponse("访问的服务需要认证！！");
    }


}
