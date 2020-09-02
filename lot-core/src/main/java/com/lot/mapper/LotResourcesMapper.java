package com.lot.mapper;

import com.lot.entity.LotResourcesEntity;
import com.lot.entity.LotResourcesHistoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotResourcesMapper")
public interface LotResourcesMapper {
    List<LotResourcesEntity> findList(Map<String,Object> map);

    int getCount(Map<String, Object> map);

    List<LotResourcesEntity> getSimpleList(String address, String brand);

    List<LotResourcesEntity> searchList(String searchField);

    List<LotResourcesEntity> generateScheme(Map<String, Object> conMap);

    List<LotResourcesEntity> findAllByBrandAndModel(String address,List<String> brandList,String material,String model);

    List<LotResourcesHistoryEntity> getResourcesListByDate(Map<String, Object> map);
}
