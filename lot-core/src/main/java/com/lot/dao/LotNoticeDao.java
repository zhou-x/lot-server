package com.lot.dao;

import com.lot.entity.LotNoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotNoticeDao extends JpaRepository<LotNoticeEntity,String> {
}
