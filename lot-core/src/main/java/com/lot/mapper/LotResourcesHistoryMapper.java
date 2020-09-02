package com.lot.mapper;

import com.lot.entity.LotResourcesHistoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LotResourcesHistoryMapper")
public interface LotResourcesHistoryMapper {
    List<LotResourcesHistoryEntity> findListByPrice(String resourcesId, int day);
}
