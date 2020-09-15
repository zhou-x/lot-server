package com.lot.vo.LotRoutes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lot.entity.LotRoutesEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 用于返回路由信息
 */
public class LotRoutesVo implements Serializable {
    private String routesId;
    private String path;
    private String name;
    private String icon;
    private String component;
    private String[] authority;
    private String redirect;
    private String pId;
    private String title;

    private List<LotRoutesVo> children;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String[] getAuthority() {
        return authority;
    }

    public void setAuthority(String[] authority) {
        this.authority = authority;
    }

    public List<LotRoutesVo> getChildren() {
        return children;
    }

    public void setChildren(List<LotRoutesVo> children) {
        this.children = children;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    @JsonBackReference
    public String getRoutesId() {
        return routesId;
    }

    @JsonBackReference
    public void setRoutesId(String routesId) {
        this.routesId = routesId;
    }

    @JsonBackReference
    public String getpId() {
        return pId;
    }

    @JsonBackReference
    public void setpId(String pId) {
        this.pId = pId;
    }

    public LotRoutesVo from(LotRoutesEntity lotRoutesEntity) {
        LotRoutesVo lotRoutesVo = new LotRoutesVo();
        if (lotRoutesEntity.getAuthority() != null) {
            lotRoutesVo.setAuthority(lotRoutesEntity.getAuthority().split(","));
        } else {
            lotRoutesVo.setAuthority(new String[0]);
        }
        lotRoutesVo.setComponent(lotRoutesEntity.getComponent() != null ? lotRoutesEntity.getComponent() : "");
        lotRoutesVo.setIcon(lotRoutesEntity.getIcon() != null ? lotRoutesEntity.getIcon() : "");
        lotRoutesVo.setName(lotRoutesEntity.getName() != null ? lotRoutesEntity.getName() : "");
        lotRoutesVo.setRedirect(lotRoutesEntity.getRedirect() != null ? lotRoutesEntity.getRedirect() : "");
        lotRoutesVo.setPath(lotRoutesEntity.getPath() != null ? lotRoutesEntity.getPath() : "");
        lotRoutesVo.setpId(lotRoutesEntity.getpId() != null ? lotRoutesEntity.getpId() : "");
        lotRoutesVo.setRoutesId(lotRoutesEntity.getRoutesId() != null ? lotRoutesEntity.getRoutesId() : "");
        lotRoutesVo.setTitle(lotRoutesEntity.getTitle() != null ? lotRoutesEntity.getTitle() : "");
        return lotRoutesVo;
    }

    @Override
    public String toString() {
        return "LotRoutesVo{" +
                "routesId='" + routesId + '\'' +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", component='" + component + '\'' +
                ", authority='" + authority + '\'' +
                ", redirect='" + redirect + '\'' +
                ", pId='" + pId + '\'' +
                ", children=" + children +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
