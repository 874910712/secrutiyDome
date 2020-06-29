package org.chen.mysecurity.core.config;

import org.chen.mysecurity.core.entity.ValidateCode;
import org.chen.mysecurity.core.inter.validateCode.SmsValidateCodeSender;
import org.chen.mysecurity.core.inter.validateCode.impl.DefaultSmsValidateCodeSender;
import org.chen.mysecurity.core.inter.validateCode.impl.ImageCodeGenerator;
import org.chen.mysecurity.core.inter.validateCode.ValidateCodeGenerator;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

/**
 * @ClassName: ValidateCodeBeanConfig
 * @Description: 自定义图形生成器配置类
 * @Author: chengui
 * @Date: 2020/6/28 0:22
 * @Version: 1.0
 **/
@Configuration
public class ValidateCodeBeanConfig {
    //注入自定义配置文件
    @Resource
    private MySecurityProperties mySecurityProperties;


    @Bean//生成的bean类默认名和方法名相同
    @ConditionalOnMissingBean(name = "imageCodeGenerator")//这个注解代表只有在spring中不存在名字为imageCodeGenerator的bean时才调用这段代码
    public ValidateCodeGenerator imageCodeGenerator(){
        //创建图形验证码生成器
        ImageCodeGenerator  codeGenerator = new ImageCodeGenerator();
        //传入自定义配置文件
        codeGenerator.setMySecurityProperties(mySecurityProperties);

        return codeGenerator;
    }

    @Bean//生成的bean类默认名和方法名相同
    @ConditionalOnMissingBean(name = "smsCodeSender")//这个注解代表只有在spring中不存在名字为imageCodeGenerator的bean时才调用这段代码
    public SmsValidateCodeSender smsValidateCodeSender(){

         return new DefaultSmsValidateCodeSender();
    }




}
