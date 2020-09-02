package com.lot.dao;

import com.lot.entity.LotCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotCodeDao extends JpaRepository<LotCodeEntity,String> {
}
