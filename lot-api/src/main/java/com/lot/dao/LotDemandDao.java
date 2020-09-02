package com.lot.dao;

import com.lot.entity.LotDemandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotDemandDao extends JpaRepository<LotDemandEntity,String> {
}
