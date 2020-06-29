package org.chen.mysecurity.core.properties.controller;

import org.chen.mysecurity.core.entity.ImageCode;
import org.chen.mysecurity.core.entity.ValidateCode;
import org.chen.mysecurity.core.inter.validateCode.AbstractValidateCodeProcessor;
import org.chen.mysecurity.core.inter.validateCode.SmsValidateCodeSender;
import org.chen.mysecurity.core.inter.validateCode.ValidateCodeGenerator;
import org.chen.mysecurity.core.inter.validateCode.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName: ValidateCodeController
 * @Description: 登录验证码控制类
 * @Author: chengui
 * @Date: 2020/6/27 20:53
 * @Version: 1.0
 **/
@RestController
public class ValidateCodeController<C> {


    /*注入验证码处理类*/
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    /*
     * @Author:陈贵
     * @Description：验证码统一方法
     * @param
     * @param null
     * @return void
     * @Date：2020/6/27 21:01
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {

        validateCodeProcessorMap.get(type+"CodeProcessor").createValidateCode(new ServletWebRequest(request,response));

    }



}
