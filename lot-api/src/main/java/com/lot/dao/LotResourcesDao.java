package com.lot.dao;

import com.lot.entity.LotResourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotResourcesDao extends JpaRepository<LotResourcesEntity, String> {
    List<LotResourcesEntity> findAllByAddressAndBrandAndSubordinateArea(String address, String brand, String subordinateArea);

    List<LotResourcesEntity> findAllByAddress(String address);

    List<LotResourcesEntity> findAllByAddressAndBrandAndMaterialAndModelAndSubordinateArea(String address, String brand, String material, String model, String subordinateArea);
}
