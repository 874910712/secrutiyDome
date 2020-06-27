package org.chen.mysecurity.core.properties;

import org.chen.mysecurity.core.validate.code.ImageCodeGenerator;
import org.chen.mysecurity.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
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


}
