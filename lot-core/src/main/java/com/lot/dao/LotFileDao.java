package com.lot.dao;

import com.lot.entity.LotFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotFileDao extends JpaRepository<LotFileEntity,String> {
}
