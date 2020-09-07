package com.lot.service;

import com.lot.entity.LotRoutesEntity;

import java.util.List;

public interface LotRoutesService {

    /**
     * 获取所有的路由信息
     */
    public List<LotRoutesEntity> findList();
}
