package org.chen.mysecurity.core.properties;

/**
 * @ClassName: ValidateCodeProperties
 * @Description: 自定义验证码配置类
 * @Author: chengui
 * @Date: 2020/6/27 23:25
 * @Version: 1.0
 **/
public class ValidateCodeProperties  {
    //自定义图形验证码配置类
    private ImageCodeProperties image = new ImageCodeProperties();
    //自定义图形验证码配置类
    private SmsCodeProperties smsCode = new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SmsCodeProperties smsCode) {
        this.smsCode = smsCode;
    }
}
