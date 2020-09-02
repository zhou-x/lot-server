package com.lot.dao;

import com.lot.entity.LotUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotUserRoleDao extends JpaRepository<LotUserRoleEntity,String> {
    LotUserRoleEntity getByUserId(String userId);
}
