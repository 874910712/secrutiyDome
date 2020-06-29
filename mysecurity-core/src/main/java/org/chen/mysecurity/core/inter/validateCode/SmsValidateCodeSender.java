package org.chen.mysecurity.core.inter.validateCode;

/**
 * @ClassName: SmsValidateCodeSender
 * @Description: 短信验证码发送接口
 * @Author: chengui
 * @Date: 2020/6/29 21:27
 * @Version: 1.0
 **/
public interface SmsValidateCodeSender {
    /*
     * @Author:陈贵
     * @Description：向用户手机发送验证码的方法
     * @param phoneNum：用户手机号
     *        Code：要发送的验证码
     * @param null
     * @return void
     * @Date：2020/6/29 21:28
     */
    void sendRequestGetCode(String phoneNum,String code);

}
