package com.lot.vo.lotUser;

import com.lot.Base.BaseVo;
import com.lot.entity.LotFileEntity;

import java.util.List;

public class LotUserSaveVo extends BaseVo {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String status;
    private List<LotFileEntity> avatars;     //头像文件信息
    private String telphone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LotFileEntity> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<LotFileEntity> avatars) {
        this.avatars = avatars;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
