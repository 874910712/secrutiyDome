package org.chen.demo.service.impl;

import org.chen.demo.dao.LoginMapper;
import org.chen.demo.entity.Users;
import org.chen.demo.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserLoginServiceImpl
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/21 23:28
 * @Version: 1.0
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Users userLogin(String userAccount) {
        return loginMapper.userLogin(userAccount);
    }


}
