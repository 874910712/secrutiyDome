package org.chen.demo.entity;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/21 23:17
 * @Version: 1.0
 **/
public class Users {
    private Integer userId;
    private String account;
    private String password;
    private String userNike;

    public Users() {
    }

    public Users(Integer userId, String account, String password, String userNike) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.userNike = userNike;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNike() {
        return userNike;
    }

    public void setUserNike(String userNike) {
        this.userNike = userNike;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
