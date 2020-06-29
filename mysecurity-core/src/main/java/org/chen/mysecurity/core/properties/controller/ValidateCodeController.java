package org.chen.mysecurity.core.properties.controller;

import org.chen.mysecurity.core.entity.ImageCode;
import org.chen.mysecurity.core.entity.ValidateCode;
import org.chen.mysecurity.core.inter.validateCode.SmsValidateCodeSender;
import org.chen.mysecurity.core.inter.validateCode.ValidateCodeGenerator;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    @Resource
    private ValidateCodeGenerator smsValidateCodeGenerator;
    //注入短信验证码发送实现类
    @Resource
    private SmsValidateCodeSender smsValidateCodeSender;


    /*
     * @Author:陈贵
     * @Description：生成图形验证码的工具类
     * @param
     * @param null
     * @return void
     * @Date：2020/6/27 21:01
     */
    @GetMapping("/getValidateCode")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         ImageCode imageCode = createImageCode(request);
        //调用自定义生成图形验证码接口
         ImageCode imageCode = (ImageCode) imageCodeGenerator.createImageCode(request);
         //将验证码存放到session中
         sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
         ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }
    /**
     * @Author:陈贵
     * @Description： 生成短信验证码
     * @param
     * @param null
     * @return void
     * @Date：2020/6/29 21:22
     */
    @GetMapping("/getSmsValidateCode")
    public void createSmsValidateCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        //调用自定义生成图形验证码接口
        ValidateCode imageCode = imageCodeGenerator.createImageCode(request);
        //将验证码存放到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        String phoneNum = ServletRequestUtils.getRequiredStringParameter(request,"phoneNum");
        //调用短信发送接口
        smsValidateCodeSender.sendRequestGetCode(phoneNum,"1234");
    }


}
