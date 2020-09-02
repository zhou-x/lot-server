package com.lot.mapper;

import com.lot.Base.LotRole;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotRoleMapper")
public interface LotRoleMapper {
    List<LotRole> getRoleBy(String username);

    List<LotRole> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);
}
