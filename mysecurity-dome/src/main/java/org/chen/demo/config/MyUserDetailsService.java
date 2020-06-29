package org.chen.demo.config;

import org.chen.demo.entity.Users;
import org.chen.demo.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName: MyUserDetailsService
 * @Description: 登录
 * @Author: chengui
 * @Date: 2020/6/21 23:12
 * @Version: 1.0
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserLoginService userLoginService;
    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*调用DAO获取用户对象*/
        Users a = userLoginService.userLogin(username);
        /*返回userDetails对象*/
        return new User(a.getAccount(),passwordEncoder.encode(a.getPassword()),
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

