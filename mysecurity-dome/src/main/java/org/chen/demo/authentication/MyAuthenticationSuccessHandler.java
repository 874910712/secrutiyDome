package org.chen.demo.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @ClassName: MyAuthenticationSuccessHandler
 * @Description: 实现自定义登录成功处理类
 * @Author: chengui
 * @Date: 2020/6/23 22:41
 * @Version: 1.0
 **/
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功");
        response.setContentType("application/json;charset=UTF-8");
        Writer responseWriter = response.getWriter();
        responseWriter.write(objectMapper.writeValueAsString(authentication));
        responseWriter.close();
    }
}
