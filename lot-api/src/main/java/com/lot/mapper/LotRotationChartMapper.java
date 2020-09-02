package com.lot.mapper;

import com.lot.entity.LotRotationChartEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotRotationChartMapper")
public interface LotRotationChartMapper {
    List<LotRotationChartEntity> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);

    List<LotRotationChartEntity> getList();
}
