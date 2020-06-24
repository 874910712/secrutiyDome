package org.chen.demo.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @ClassName: MyAuthenticationFailureHandler
 * @Description: 自定义登录失败处理类
 * @Author: chengui
 * @Date: 2020/6/23 23:10
 * @Version: 1.0
 **/
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    //自定义登录失败处理逻辑
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        System.out.println("登录失败！");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        Writer responseWriter = response.getWriter();
        responseWriter.write(objectMapper.writeValueAsString(e));
        responseWriter.close();
    }
}
