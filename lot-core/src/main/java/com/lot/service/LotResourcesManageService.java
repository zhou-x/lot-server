package com.lot.service;

import com.lot.entity.LotResourcesManageEntity;
import com.lot.util.Msg;
import com.lot.vo.lotResourcesManage.LotResourcesManageSaveVo;
import com.lot.vo.lotResourcesManage.LotResourcesManageSearchVo;

import java.util.Map;

public interface LotResourcesManageService {
    Msg findList(LotResourcesManageSearchVo searchVo);

    public LotResourcesManageEntity get(String resourcesManageId);

    public void save(LotResourcesManageSaveVo saveVo);

    public void delete(String resourcesManageId);

    Map<String, Object> getSimpleList(String address);

    Msg getAddressList();
}
