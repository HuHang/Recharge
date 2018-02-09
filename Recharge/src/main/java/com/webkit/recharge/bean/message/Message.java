package com.webkit.recharge.bean.message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HH on 2018/1/22.
 */
public class Message {
    private Integer code = 200;
    private Boolean result = false;
    private Object data;
    private List<String> messages = new ArrayList<>();
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
