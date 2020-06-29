package org.chen.mysecurity.core.validate.code;

import org.chen.mysecurity.core.entity.ImageCode;
import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.web.bind.ServletRequestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName: ImageCodeGenerator
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/28 0:17
 * @Version: 1.0
 **/
public class ImageCodeGenerator implements ValidateCodeGenerator {

    //注入自定义配置文件类
    @Resource
    private MySecurityProperties mySecurityProperties;

    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        /*读取图形验证码宽度、高度、字数、过期时间参数*/
        int width = ServletRequestUtils.getIntParameter(request,"codeWidth",mySecurityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request,"codeHeight",mySecurityProperties.getCode().getImage().getHeight());
        int codeLength = ServletRequestUtils.getIntParameter(request,"codeLength",mySecurityProperties.getCode().getImage().getLength());
        int expireIn = ServletRequestUtils.getIntParameter(request,"codeExpireIn",mySecurityProperties.getCode().getImage().getExpireIn());

        String[] arr = {"1","2","3","4","5","6","7","8","9","0"};
        String code = "";
        for(int i = 0; i < codeLength; i ++) {
            Random random = new Random();
            code += arr[random.nextInt(arr.length)];
        }
        // 生成验证码后，需要保存验证码
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        // 绘制图片
        // 参数：图片的宽和高
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics grap = image.getGraphics();
        grap.setColor(new Color(85,183,164)); // 设置画笔灰色，如果不绘制背景，那么背景可能是黑色的
        grap.fillRect(0, 0, width, height);
        // 绘制验证码
        grap.setColor(Color.WHITE);
        grap.setFont(new Font("微软雅黑", Font.BOLD, 15));
        for(int i = 0; i < code.length(); i ++) {
            grap.drawString(code.charAt(i) + "", 30*i + 25, 20);
        }
        // 生成干扰线
        grap.setColor(new Color(89,108,126));
        for(int i = 0; i < 10; i ++) {
            Random random = new Random();
            grap.drawLine(random.nextInt(150), random.nextInt(30), random.nextInt(150) , random.nextInt(30));
        }
        return new ImageCode(image,code,expireIn);
    }



    public MySecurityProperties getMySecurityProperties() {
        return mySecurityProperties;
    }

    public void setMySecurityProperties(MySecurityProperties mySecurityProperties) {
        this.mySecurityProperties = mySecurityProperties;
    }
}
