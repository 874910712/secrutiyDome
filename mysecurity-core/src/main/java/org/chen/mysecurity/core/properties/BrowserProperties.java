package org.chen.mysecurity.core.properties;

import org.chen.mysecurity.core.entity.LoginResponseType;

/**
 * @ClassName: BrowserProperties
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/23 21:26
 * @Version: 1.0
 **/
public class BrowserProperties {
    /*配置自定义登录页面*/
    private String loginPage = "/MyLogin.html";
    /*设置登录成功或失败返回的信息类型,默认设置为返回JSON字符串*/
    private LoginResponseType loginResponseType = LoginResponseType.JSON;



    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginResponseType() {
        return loginResponseType;
    }

    public void setLoginResponseType(LoginResponseType loginResponseType) {
        this.loginResponseType = loginResponseType;
    }
}
