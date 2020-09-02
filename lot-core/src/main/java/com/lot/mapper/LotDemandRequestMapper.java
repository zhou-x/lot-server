package com.lot.mapper;

import com.lot.entity.LotDemandRequestEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotDemandRequestMapper")
public interface LotDemandRequestMapper {
    List<LotDemandRequestEntity> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);
}
