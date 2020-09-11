package com.lot.mapper;

import com.lot.entity.LotRoutesEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LotRoutesMapper")
public interface LotRoutesMapper {
    List<LotRoutesEntity> findList();
}
