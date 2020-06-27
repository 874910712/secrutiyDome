package org.chen.mysecurity.core.validate.code;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: ValidateCodeGenerator
 * @Description: 自定义图形校验码生成器
 * @Author: chengui
 * @Date: 2020/6/28 0:14
 * @Version: 1.0
 **/
public interface ValidateCodeGenerator {

    //生成图形验证码的方法
    ImageCode createImageCode(HttpServletRequest request);


}
