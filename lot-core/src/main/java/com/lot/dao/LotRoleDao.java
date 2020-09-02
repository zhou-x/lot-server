package com.lot.dao;

import com.lot.entity.LotRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRoleDao extends JpaRepository<LotRoleEntity,String> {
    LotRoleEntity getByName(String name);
}
