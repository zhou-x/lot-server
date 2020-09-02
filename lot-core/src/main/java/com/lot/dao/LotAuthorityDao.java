package com.lot.dao;

import com.lot.entity.LotAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LotAuthorityDao extends JpaRepository<LotAuthorityEntity,String> {
}
