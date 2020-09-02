package com.lot.dao;

import com.lot.entity.LotDemandRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotDemandRequestDao extends JpaRepository<LotDemandRequestEntity,String> {
}
