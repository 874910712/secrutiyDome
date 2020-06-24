package org.chen.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chen.demo.entity.Users;

/**
 * @ClassName: LoginMapper
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/21 23:14
 * @Version: 1.0
 **/
@Mapper
public interface LoginMapper {

    Users userLogin(@Param("useraccount")String useraccount);


}
