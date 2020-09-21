package com.lot.service;

import com.lot.vo.LotRoutes.LotRoutesVo;

import java.util.List;

public interface LotRoutesService {

    /**
     * 获取所有的路由信息
     * @return
     */
    public List<LotRoutesVo> findList();

    LotRoutesVo get(String routesId);
}
