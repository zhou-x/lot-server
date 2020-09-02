package com.lot.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zel
 * @date 2020-05-22
 * @description  用于返回前端json数据的工具类
 */
public class Msg implements Serializable{

    // 状态码
    private int status;
    // 提示信息
    private String message;
    //角色
    private String currentAuthority;

    // 封装Map有效数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public static Msg success() {
        Msg result = new Msg();
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setStatus(400);
        result.setMessage("fail");
        return result;
    }

    public static Msg noPermission() {
        Msg result = new Msg();
        result.setStatus(401);
        result.setMessage("no permission");
        return result;
    }

    public static Msg login(String currentAuthority) {
        Msg result = new Msg();
        result.setStatus(200);
        result.setMessage("success");
        result.setCurrentAuthority(currentAuthority);
        return result;
    }

    public static Msg noLogin() {
        Msg result = new Msg();
        result.setStatus(401);
        result.setMessage("no login");
        return result;
    }

    public static Msg unauthorized() {
        Msg result = new Msg();
        result.setStatus(500);
        result.setMessage("unauthorized");
        return result;
    }

    public static Msg error() {
        Msg result = new Msg();
        result.setStatus(500);
        result.setMessage("error");
        return result;
    }

    public static Msg error(String errorMsg) {
        Msg result = new Msg();
        result.setStatus(4001);
        result.setMessage(errorMsg);
        return result;
    }

    public static Msg errorUserPassword() {
        Msg result = new Msg();
        result.setStatus(501);
        result.setMessage("error");
        return result;
    }

    public static Msg code(int code){
        Msg result = new Msg();
        result.setStatus(code);
        result.setMessage("exception");
        return result;
    }

    public static Msg success(int code, String message){
        Msg result = new Msg();
        result.setStatus(code);
        result.setMessage(message);
        return result;
    }

    public Msg add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getCurrentAuthority() {
        return currentAuthority;
    }

    public void setCurrentAuthority(String currentAuthority) {
        this.currentAuthority = currentAuthority;
    }
}