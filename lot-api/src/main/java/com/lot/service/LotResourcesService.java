package com.lot.service;

import com.lot.entity.LotResourcesEntity;
import com.lot.entity.LotResourcesSaveEntity;
import com.lot.util.Msg;
import com.lot.vo.lotResources.GenerateSchemeVo;
import com.lot.vo.lotResources.LotResourcesSaveVo;
import com.lot.vo.lotResources.LotResourcesSearchVo;
import com.lot.vo.lotResources.SubPrices;

import java.sql.Timestamp;
import java.util.List;

public interface LotResourcesService {
    Msg findList(LotResourcesSearchVo searchVo);

    public LotResourcesEntity get(String resourcesId);

    public void save(LotResourcesSaveVo saveVo);

    public void delete(String resourcesId);

    List<LotResourcesEntity> getList(String address, String brand);

    List<LotResourcesEntity> searchList(String searchField);

    Msg getConditionList();

    Msg generateScheme(GenerateSchemeVo vo);

    void addSubPrice(int prices);

    Msg getAddressListByAddress(String address);

    Msg getResourcesListByDate(Timestamp date, String mark, String address, String brand);

    void addSubPrices(SubPrices subPrices);

    LotResourcesSaveEntity getSaveData();
}
