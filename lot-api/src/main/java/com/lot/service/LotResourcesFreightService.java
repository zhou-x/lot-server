package com.lot.service;

import com.lot.entity.LotResourcesFreightEntity;
import com.lot.util.Msg;
import com.lot.vo.lotResourcesFreight.LotResourcesFreightSaveVo;
import com.lot.vo.lotResourcesFreight.LotResourcesFreightSearchVo;

public interface LotResourcesFreightService {
    Msg findList(LotResourcesFreightSearchVo searchVo);

    public LotResourcesFreightEntity get(String resourcesFreightId);

    public void save(LotResourcesFreightSaveVo saveVo);

    public void delete(String resourcesFreightId);
}
