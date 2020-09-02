package com.lot.mapper;

import com.lot.entity.LotDemandEntity;
import com.lot.vo.lotDemand.LotDemandVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotDemandMapper")
public interface LotDemandMapper {
    List<LotDemandEntity> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);

    List<LotDemandVo> getListByToday(Map<String,Object> map);
}
