package com.lot.vo.lotAuthority;

import java.util.List;

public class LotAuthorityIdList {
    List<String> authorityIdList;
    private String roleId;

    public List<String> getAuthorityIdList() {
        return authorityIdList;
    }

    public void setAuthorityIdList(List<String> authorityIdList) {
        this.authorityIdList = authorityIdList;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
