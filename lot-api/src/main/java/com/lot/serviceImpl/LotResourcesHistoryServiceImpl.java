package com.lot.serviceImpl;

import com.lot.entity.LotResourcesHistoryEntity;
import com.lot.mapper.LotResourcesHistoryMapper;
import com.lot.service.LotResourcesHistoryService;
import com.lot.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class LotResourcesHistoryServiceImpl implements LotResourcesHistoryService {
    @Autowired
    private LotResourcesHistoryMapper lotResourcesHistoryMapper;

    @Override
    public Msg findListByPrice(String resourcesId, int day) {
        List<LotResourcesHistoryEntity> list = lotResourcesHistoryMapper.findListByPrice(resourcesId, day);
        List<BigDecimal> priceList = new ArrayList<>();
        List<Timestamp> createTimeList = new ArrayList<>();
        list.forEach(e -> {
            priceList.add(e.getPrice());
            createTimeList.add(e.getCreatedTime());
        });
        return Msg.success().add("priceList", priceList).add("createTimeList", createTimeList);
    }
}
