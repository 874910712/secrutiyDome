package org.chen.mysecurity.core.properties;

/**
 * @ClassName: ImageCodeProperties
 * @Description: 图形验证码配置类
 * @Author: chengui
 * @Date: 2020/6/27 23:20
 * @Version: 1.0
 **/
public class ImageCodeProperties extends  SmsCodeProperties{
    private int width = 150;//图形验证码宽度
    private int height = 30;//图形验证码高度度

    public ImageCodeProperties() {
        //设置默认为4
       this.setLength(4);
    }

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

}
