package org.chen.mysecurity.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName: ValidateCodeExcepetion
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/27 22:17
 * @Version: 1.0
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
