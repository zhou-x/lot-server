package com.lot.dao;

import com.lot.entity.LotResourcesFreightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotResourcesFreightDao extends JpaRepository<LotResourcesFreightEntity,String> {
    LotResourcesFreightEntity getBySubordinateAreaIsAndYnFlagIs(String subordinateArea,String ynFlag);

    List<LotResourcesFreightEntity> findAllByAddress(String e);
}
