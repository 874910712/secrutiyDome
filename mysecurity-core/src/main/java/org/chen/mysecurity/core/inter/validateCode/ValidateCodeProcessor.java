package org.chen.mysecurity.core.inter.validateCode;

import org.springframework.web.context.request.ServletWebRequest;

/*
 * @Author:陈贵
 * @Description：验证码处理接口（封装不同类型验证码的处理方法）
 * @param
 * @param null
 * @return
 * @Date：2020/6/29 22:23
 */
public interface ValidateCodeProcessor {

    //验证码存放在session中的key的前缀
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";


    /*
     * @Author:陈贵
     * @Description：验证码处理方法
     * @param   ServletWebRequest:Spring的一个封装对象里面可以存放请求和响应
     * @param null
     * @return void
     * @Date：2020/6/29 22:26
     */
    void createValidateCode(ServletWebRequest request)throws Exception;




}
