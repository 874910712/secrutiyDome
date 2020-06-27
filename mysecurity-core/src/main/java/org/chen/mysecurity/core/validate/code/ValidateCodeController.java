package org.chen.mysecurity.core.validate.code;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @ClassName: ValidateCodeController
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/27 20:53
 * @Version: 1.0
 **/
@RestController
public class ValidateCodeController {

    private static  final  String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /*
     * @Author:陈贵
     * @Description：生成验证码的工具类
     * @param
     * @param null
     * @return void
     * @Date：2020/6/27 21:01
     */
    @GetMapping("/getValidateCode")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
         ImageCode imageCode = createImageCode(request);
         sessionStrategy.setAttribute(new ServletWebRequest(request),this.SESSION_KEY,imageCode);
         ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }






    private ImageCode createImageCode(HttpServletRequest request) {
        String[] arr = {"1","2","3","4","5","6","7","8","9","0"};
        String code = "";
        for(int i = 0; i < 4; i ++) {
            Random random = new Random();
            code += arr[random.nextInt(arr.length)];
        }
        // 生成验证码后，需要保存验证码
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        // 绘制图片
        // 参数：图片的宽和高
        BufferedImage image = new BufferedImage(150, 30, BufferedImage.TYPE_INT_RGB);
        Graphics grap = image.getGraphics();
        grap.setColor(new Color(85,183,164)); // 设置画笔灰色，如果不绘制背景，那么背景可能是黑色的
        grap.fillRect(0, 0, 150, 30);
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
        return new ImageCode(image,code,60);
    };


}
