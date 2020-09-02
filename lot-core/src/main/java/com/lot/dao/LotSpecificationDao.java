package com.lot.dao;

import com.lot.entity.LotSpecificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotSpecificationDao extends JpaRepository<LotSpecificationEntity,String> {
    List<LotSpecificationEntity> findAllByIdentifi(String identifi);
}
