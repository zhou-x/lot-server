package com.lot.service;

import com.lot.util.Msg;
import com.lot.vo.lotDemand.LotDemandSaveVo;
import com.lot.vo.lotDemand.LotDemandSearchVo;
import com.lot.vo.lotDemand.LotDemandVo;
import com.lot.vo.lotDemand.WxDemandSearchVo;

import java.util.List;

public interface LotDemandService {
    Msg findList(LotDemandSearchVo searchVo);
    Object get(String demandId);
    void save(LotDemandSaveVo saveVo);
    void delete(String demandId);

    List<LotDemandVo> getListByToday(WxDemandSearchVo searchVo);

    Msg getScreeningByBrand();
}
