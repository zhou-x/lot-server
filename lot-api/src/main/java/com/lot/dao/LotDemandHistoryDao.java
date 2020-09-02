package com.lot.dao;

import com.lot.entity.LotDemandHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotDemandHistoryDao extends JpaRepository<LotDemandHistoryEntity,String> {
}
