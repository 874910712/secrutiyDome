package org.chen.mysecurity.core;

import org.chen.mysecurity.core.properties.MySecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: SecurityCoreConfig
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/23 21:35
 * @Version: 1.0
 **/
@Configuration
@EnableConfigurationProperties(MySecurityProperties.class)
public class SecurityCoreConfig {
}
