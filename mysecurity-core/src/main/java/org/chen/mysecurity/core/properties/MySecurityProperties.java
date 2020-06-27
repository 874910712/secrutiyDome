package org.chen.mysecurity.core.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SecurityProperties
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/23 21:26
 * @Version: 1.0
 **/
@ConfigurationProperties(prefix="chen.security")/*读取配置文件中所有以chen.security开头的配置项*/
public class MySecurityProperties {
      /*自定义浏览器配置类*/
      private BrowserProperties browser = new BrowserProperties();
      /*自定义验证码配置类*/
      private ValidateCodeProperties code = new ValidateCodeProperties();













      public BrowserProperties getBrowser() {
            return browser;
      }

      public void setBrowser(BrowserProperties browser) {
            this.browser = browser;
      }

      public ValidateCodeProperties getCode() {
            return code;
      }

      public void setCode(ValidateCodeProperties code) {
            this.code = code;
      }
}
