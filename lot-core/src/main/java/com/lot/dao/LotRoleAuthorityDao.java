package com.lot.dao;

import com.lot.entity.LotRoleAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LotRoleAuthorityDao extends JpaRepository<LotRoleAuthorityEntity,String> {
    @Transactional
    void deleteByRoleId(String roleId);
}
