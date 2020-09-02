package com.lot.mapper;

import com.lot.entity.LotResourcesFreightEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotResourcesFreightMapper")
public interface LotResourcesFreightMapper {
    List<LotResourcesFreightEntity> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);

}
