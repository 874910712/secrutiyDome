package org.chen.demo.test;

import org.chen.mysecurity.core.validate.code.ImageCode;
import org.chen.mysecurity.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: DomeImageCodeGenerator
 * @Description: 测试图片验证码生成器
 * @Author: chengui
 * @Date: 2020/6/28 0:36
 * @Version: 1.0
 **/
@Component("imageCodeGenerator")
public class DomeImageCodeGenerator implements ValidateCodeGenerator {


    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        System.out.println("自定义图形验证码生成器!!");
        return null;
    }
}
