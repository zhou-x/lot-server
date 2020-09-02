package com.lot.mapper;

import com.lot.vo.lotDemandHistory.LotDemandHistoryVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotDemandHistoryMapper")
public interface LotDemandHistoryMapper {
//    List<LotDemandHistoryVo> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);

    List<LotDemandHistoryVo> findListByUserId(Map<String,Object> map);
}
