package org.chen.demo.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chen.mysecurity.core.entity.LoginResponseType;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @ClassName: AuthenticationFailureHandler
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/23 23:29
 * @Version: 1.0
 **/
@Component
public class SetAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private MySecurityProperties mySecurityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if(LoginResponseType.JSON.equals(mySecurityProperties.getBrowser().getLoginResponseType())){
            response.setContentType("application/json;charset=UTF-8");
            Writer responseWriter = response.getWriter();
            responseWriter.write(objectMapper.writeValueAsString(exception));
            responseWriter.close();
        }else{
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
