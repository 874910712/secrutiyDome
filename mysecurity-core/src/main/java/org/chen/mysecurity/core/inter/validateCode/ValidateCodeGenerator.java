package org.chen.mysecurity.core.inter.validateCode;

import org.chen.mysecurity.core.entity.ImageCode;
import org.chen.mysecurity.core.entity.ValidateCode;

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
    ValidateCode createImageCode(HttpServletRequest request);


}
