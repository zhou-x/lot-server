package com.lot.mapper;

import com.lot.entity.LotResourcesManageEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotResourcesManageMapper")
public interface LotResourcesManageMapper {
    List<LotResourcesManageEntity> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);

    List<LotResourcesManageEntity> getSimpleList(String address);

}
