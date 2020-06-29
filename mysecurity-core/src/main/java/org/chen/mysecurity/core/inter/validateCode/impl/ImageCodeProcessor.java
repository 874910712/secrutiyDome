package org.chen.mysecurity.core.inter.validateCode.impl;

import org.chen.mysecurity.core.entity.ImageCode;
import org.chen.mysecurity.core.inter.validateCode.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: ImageCodeProcessor
 * @Description: 图片验证码处理类
 * @Author: chengui
 * @Date: 2020/6/29 23:01
 * @Version: 1.0
 **/
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {


    /*图形验证码特有的发送方法*/
    @Override
    protected void sendCode(ServletWebRequest request, ImageCode validateCode) throws IOException {

        ImageIO.write(validateCode.getImage(),"JPEG",request.getResponse().getOutputStream());

    }


}
