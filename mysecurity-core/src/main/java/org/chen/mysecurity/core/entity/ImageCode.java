package org.chen.mysecurity.core.entity;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @ClassName: ImageCode
 * @Description: 图形验证码实体类
 * @Author: chengui
 * @Date: 2020/6/27 20:48
 * @Version: 1.0
 **/
public class ImageCode extends ValidateCode {
    private BufferedImage image;//验证码图片对象
    private String code;//
    private LocalDateTime expireTime;//过期时间

    /*
     * @Author:陈贵
     * @Description：根据传入的秒数来自动生成过期时间的构造函数
     * @param expireTime为过期秒数
     * @param null
     * @return
     * @Date：2020/6/27 20:51
     */
    public ImageCode(BufferedImage image, String code, int expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
