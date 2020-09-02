package com.lot.Base;

import java.io.Serializable;

/**
 * 这是一个提供给获取后台全部url地址以及说明的实体类（ps：其中的说明是根据@ApiOperation("value")值来的）
 * @author zel
 * */
public class UrlDesc implements Serializable,Cloneable {

    private static final long serialVersionUID = 1L;

    /**
     * 路径
     */
    private String url;

    /**
     * 描述
     */
    private String desc;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

