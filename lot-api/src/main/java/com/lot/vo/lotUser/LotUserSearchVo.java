package com.lot.vo.lotUser;

import com.lot.Base.BaseSearchVo;

import java.sql.Timestamp;
import java.util.List;

public class LotUserSearchVo extends BaseSearchVo {
    private String username;
    private List<Timestamp> createDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Timestamp> getCreateDate() {
        return createDate;
    }

    public void setCreateDate(List<Timestamp> createDate) {
        this.createDate = createDate;
    }
}
