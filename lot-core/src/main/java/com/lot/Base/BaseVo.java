package com.lot.Base;

import com.lot.util.DateUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * @Author zel
 * 基础类
 */
public class BaseVo implements Serializable {
    //有效标志
    private String ynFlag = "Y";
    //创建人
    private String creator = "admin";
    //修改人
    private String editor = "admin";
    //创建时间
    private Timestamp createdTime;
    //最后修改时间
    private Timestamp modifiedTime = DateUtils.getNow();

    public Object setInitial(Object object) {
        try {
            Method method2 = object.getClass().getMethod("setYnFlag", String.class);
            Method method3 = object.getClass().getMethod("setCreator", String.class);
            Method method4 = object.getClass().getMethod("setEditor", String.class);
            Method method5 = object.getClass().getMethod("setModifiedTime", Timestamp.class);
            method2.invoke(object, this.getYnFlag());
            method3.invoke(object, this.getCreator());
            method4.invoke(object, this.getEditor());
            method5.invoke(object, this.getModifiedTime());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return object;
    }

    public String getYnFlag() {
        return ynFlag;
    }

    public void setYnFlag(String ynFlag) {
        this.ynFlag = ynFlag;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
