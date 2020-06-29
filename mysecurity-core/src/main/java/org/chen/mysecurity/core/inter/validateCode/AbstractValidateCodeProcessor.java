package org.chen.mysecurity.core.inter.validateCode;

import org.apache.commons.lang.StringUtils;
import org.chen.mysecurity.core.entity.ValidateCode;
import org.chen.mysecurity.core.inter.validateCode.ValidateCodeGenerator;
import org.chen.mysecurity.core.inter.validateCode.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName: AbstractValidateCodeProcessor
 * @Description: 验证码处理器抽象实现接口
 * @Author: chengui
 * @Date: 2020/6/29 22:29
 * @Version: 1.0
 **/
public abstract class AbstractValidateCodeProcessor<C> implements ValidateCodeProcessor {

    //spring操作Session的对象
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //spring会自动将spring中所有ValidateCodeGenerator接口的实现类以实现类类名为key，将类对象存放到这个map中
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    /*实现接口的验证码处理方法*/
    @Override
    public void createValidateCode(ServletWebRequest request) throws Exception {
         C validateCode = createCode(request);
         saveCode(request,validateCode);
         sendCode(request,validateCode);
    }



    /*
     * @Author:陈贵
     * @Description：验证码生成的抽象方法
     * @param
     * @param null
     * @return C
     * @Date：2020/6/29 22:53
     */
    private C createCode(ServletWebRequest request){

        /*获取请求路径后半段*/
        String codeType = getCodeType(request);
        /*根据请求路径的不同来调用不同的验证码生成器*/
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(codeType+"CodeGenerator");
        if(validateCodeGenerator!=null){
            /*调用方法生成验证码*/
            return (C)validateCodeGenerator.createCode(request);
        }else{
            return null;
        }

    }
    /*验证码的保存方法*/
    private void saveCode(ServletWebRequest request, C validateCode){
        sessionStrategy.setAttribute(request,SESSION_KEY_PREFIX+getCodeType(request),validateCode);
    };

    /*发送验证码的抽象方法*/
    protected abstract void sendCode(ServletWebRequest request, C validateCode) throws IOException, ServletRequestBindingException;





    /*截取请求url后半段*/
    public String getCodeType(ServletWebRequest request){
         //返回请求路径后半段的路径
         return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    };


}
