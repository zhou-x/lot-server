package com.lot.mapper;

import com.lot.Base.LotAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotAuthorityMapper")
public interface LotAuthorityMapper {

    List<LotAuthority> getAuthorityBy(String username);

    List<LotAuthority> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);

    List<LotAuthority> getAuthorityByOpenId(String openId);
}
