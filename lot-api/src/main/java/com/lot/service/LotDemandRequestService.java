package com.lot.service;

import com.lot.util.Msg;
import com.lot.vo.lotDemandRequest.LotDemandRequestSaveVo;
import com.lot.vo.lotDemandRequest.LotDemandRequestSearchVo;

public interface LotDemandRequestService {
    Msg findList(LotDemandRequestSearchVo searchVo);
    Object get(String demandRequestId);
    void save(LotDemandRequestSaveVo saveVo);
    void delete(String demandRequestId);
}
