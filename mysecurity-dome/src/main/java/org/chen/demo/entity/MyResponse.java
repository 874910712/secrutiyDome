package org.chen.demo.entity;

/**
 * @ClassName: MyResponse
 * @Description: TODO
 * @Author: chengui
 * @Date: 2020/6/23 21:19
 * @Version: 1.0
 **/
public class MyResponse {
    private Object content;

    public MyResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
