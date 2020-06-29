package org.chen.mysecurity.core.inter.validateCode.impl;

import org.chen.mysecurity.core.entity.ValidateCode;
import org.chen.mysecurity.core.inter.validateCode.AbstractValidateCodeProcessor;
import org.chen.mysecurity.core.inter.validateCode.SmsValidateCodeSender;
import org.chen.mysecurity.core.inter.validateCode.ValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * @ClassName: SmsCodeProcessor
 * @Description: 短信验证码类实现
 * @Author: chengui
 * @Date: 2020/6/29 23:09
 * @Version: 1.0
 **/
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    //注入短信验证码发送类
    @Resource
    private SmsValidateCodeSender smsValidateCodeSender;

    @Override
    protected void sendCode(ServletWebRequest request, ValidateCode validateCode) throws IOException, ServletRequestBindingException {
        //从请求中获取获取用户手机号
        String phoneNum = ServletRequestUtils.getRequiredStringParameter((ServletRequest) request.getRequest(),"phoneNum");
        /*发送验证码*/
        smsValidateCodeSender.sendRequestGetCode(phoneNum,validateCode.getCode());
    }
}
