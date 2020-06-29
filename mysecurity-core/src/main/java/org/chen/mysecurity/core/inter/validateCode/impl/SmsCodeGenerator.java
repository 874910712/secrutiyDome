package org.chen.mysecurity.core.inter.validateCode.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.Validate;
import org.chen.mysecurity.core.entity.ImageCode;
import org.chen.mysecurity.core.entity.ValidateCode;
import org.chen.mysecurity.core.inter.validateCode.ValidateCodeGenerator;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName: SmsCodeGenerator
 * @Description: 短信验证码生成类
 * @Author: chengui
 * @Date: 2020/6/28 0:17
 * @Version: 1.0
 **/
public class SmsCodeGenerator implements ValidateCodeGenerator {

    //注入自定义配置文件类
    @Resource
    private MySecurityProperties mySecurityProperties;

    @Override
    public ValidateCode createCode(ServletWebRequest request) {
        //根据配置文件生成位随机数
       String code = RandomStringUtils.randomNumeric(mySecurityProperties.getCode().getSmsCode().getLength());
       
       return new ValidateCode(code, mySecurityProperties.getCode().getSmsCode().getExpireIn());
    }



    public MySecurityProperties getMySecurityProperties() {
        return mySecurityProperties;
    }

    public void setMySecurityProperties(MySecurityProperties mySecurityProperties) {
        this.mySecurityProperties = mySecurityProperties;
    }
}
