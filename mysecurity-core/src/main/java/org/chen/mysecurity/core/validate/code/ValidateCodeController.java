package org.chen.mysecurity.core.validate.code;

import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
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
 * @Description: 登录验证码控制类
 * @Author: chengui
 * @Date: 2020/6/27 20:53
 * @Version: 1.0
 **/
@RestController
public class ValidateCodeController {

    public static  final  String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    //spring操作Session的对象
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//    //引入自定义验证码配置类
//    @Resource
//    private MySecurityProperties mySecurityProperties;
    //注入默认的图形验证码生成器,注意此处变量名需要和验证码生成器接口中的验证码方法相同才行
    @Resource
    private ValidateCodeGenerator imageCodeGenerator;



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
//         ImageCode imageCode = createImageCode(request);
        //调用自定义生成图形验证码接口
         ImageCode imageCode = imageCodeGenerator.createImageCode(request);
         //将验证码存放到session中
         sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
         ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }





//     /*
//      * @Author:陈贵
//      * @Description：生成验证码的方法
//      * @param
//      * @param null
//      * @return 自定义ImageCode对象
//      * @Date：2020/6/27 23:18
//      */
//    private ImageCode createImageCode(HttpServletRequest request) {
//        /*读取图形验证码宽度、高度、字数、过期时间参数*/
//        int width = ServletRequestUtils.getIntParameter(request,"codeWidth",mySecurityProperties.getCode().getImage().getWidth());
//        int height = ServletRequestUtils.getIntParameter(request,"codeHeight",mySecurityProperties.getCode().getImage().getHeight());
//        int codeLength = ServletRequestUtils.getIntParameter(request,"codeLength",mySecurityProperties.getCode().getImage().getLength());
//        int expireIn = ServletRequestUtils.getIntParameter(request,"codeExpireIn",mySecurityProperties.getCode().getImage().getExpireIn());
//
//        String[] arr = {"1","2","3","4","5","6","7","8","9","0"};
//        String code = "";
//        for(int i = 0; i < codeLength; i ++) {
//            Random random = new Random();
//            code += arr[random.nextInt(arr.length)];
//        }
//        // 生成验证码后，需要保存验证码
//        HttpSession session = request.getSession();
//        session.setAttribute("code", code);
//        // 绘制图片
//        // 参数：图片的宽和高
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics grap = image.getGraphics();
//        grap.setColor(new Color(85,183,164)); // 设置画笔灰色，如果不绘制背景，那么背景可能是黑色的
//        grap.fillRect(0, 0, width, height);
//        // 绘制验证码
//        grap.setColor(Color.WHITE);
//        grap.setFont(new Font("微软雅黑", Font.BOLD, 15));
//        for(int i = 0; i < code.length(); i ++) {
//            grap.drawString(code.charAt(i) + "", 30*i + 25, 20);
//        }
//        // 生成干扰线
//        grap.setColor(new Color(89,108,126));
//        for(int i = 0; i < 10; i ++) {
//            Random random = new Random();
//            grap.drawLine(random.nextInt(150), random.nextInt(30), random.nextInt(150) , random.nextInt(30));
//        }
//        return new ImageCode(image,code,expireIn);
//    };


}
