package com.lot.service;

import com.lot.util.Msg;
import com.lot.vo.lotAuthority.LotAuthorityIdList;
import com.lot.vo.lotAuthority.LotAuthoritySearchVo;

import java.util.Set;

public interface LotAuthorityService {
    public Set<String> getAuthority(String username);

    public Msg findList(LotAuthoritySearchVo lotAuthoritySearchVo);

    /**
     * 判断是否有Url地址
     */
    public Boolean isAuthorityUrl(String url);

    void save(LotAuthorityIdList lotAuthorityIdList);

    public Set<String> getAuthorityByOpenId(String userId);
}
