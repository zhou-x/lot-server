package com.lot.mapper;

import com.lot.entity.LotNoticeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotNoticeMapper")
public interface LotNoticeMapper {
    List<LotNoticeEntity> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);

    List<LotNoticeEntity> getNoticeList();

}
