package org.chen.demo.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chen.mysecurity.core.entity.LoginResponseType;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @ClassName: AuthenticationSuccessHandlerSet
 * @Description: 登录成功或者失败验证逻辑配置类
 * @Author: chengui
 * @Date: 2020/6/23 23:23
 * @Version: 1.0
 **/
@Component
public class SetAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private MySecurityProperties mySecurityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //如果设置的是JSON类型则返回JSON,否则为默认的跳转页面
        if(LoginResponseType.JSON.equals(mySecurityProperties.getBrowser().getLoginResponseType())){
            response.setContentType("application/json;charset=UTF-8");
            Writer responseWriter = response.getWriter();
            responseWriter.write(objectMapper.writeValueAsString(authentication));
            responseWriter.close();
        }else{
            super.onAuthenticationSuccess(request,response,authentication);
        }

    }
}
