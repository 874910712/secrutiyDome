package org.chen.mysecurity.core.properties;

/**
 * @ClassName: ImageCodeProperties
 * @Description: 图形验证码配置类
 * @Author: chengui
 * @Date: 2020/6/27 23:20
 * @Version: 1.0
 **/
public class ImageCodeProperties {
    private int width = 150;//图形验证码宽度
    private int height = 30;//图形验证码高度度
    private int length = 4;//生成的验证码数字数量
    private int expireIn = 60;//失效时间，单位为秒
    private String url;//需要拦截验证码的url

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
