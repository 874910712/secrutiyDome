package org.chen.mysecurity.core.inter.validateCode.impl;

import org.chen.mysecurity.core.inter.validateCode.SmsValidateCodeSender;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DefaultSmsValidateCodeSender
 * @Description: 手机短信验证码发送实现类
 * @Author: chengui
 * @Date: 2020/6/29 21:29
 * @Version: 1.0
 **/
@Service
public class DefaultSmsValidateCodeSender implements SmsValidateCodeSender {



    @Override
    public void sendRequestGetCode(String phoneNum, String code) {
        System.out.println("向"+phoneNum+"发送了验证码"+code+"!!");
    }


}
