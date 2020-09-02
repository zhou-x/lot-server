package com.lot.dao;

import com.lot.entity.LotUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotUserDao extends JpaRepository<LotUserEntity,String> {
}