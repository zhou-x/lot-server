package com.lot.dao;

import com.lot.entity.LotResourcesManageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotResourcesManageDao extends JpaRepository<LotResourcesManageEntity, String> {
    List<LotResourcesManageEntity> findAllByAddressAndBrandAndSubordinateArea(String address, String brand, String subordinateArea);

    List<LotResourcesManageEntity> findAllByAddress(String address);

    List<LotResourcesManageEntity> findAllBySubordinateArea(String subordinateArea);
}
