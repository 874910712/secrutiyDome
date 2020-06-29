package org.chen.mysecurity.core.inter.validateCode;

import org.chen.mysecurity.core.entity.ImageCode;
import org.chen.mysecurity.core.entity.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: ValidateCodeGenerator
 * @Description: 自定义校验码生成器
 * @Author: chengui
 * @Date: 2020/6/28 0:14
 * @Version: 1.0
 **/
public interface ValidateCodeGenerator {

    //生成验证码的方法
    ValidateCode createCode(ServletWebRequest request);


}
