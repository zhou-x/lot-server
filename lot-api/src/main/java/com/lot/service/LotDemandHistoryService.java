package com.lot.service;

import com.lot.vo.lotDemandHistory.LotDemandHistorySearchVo;
import com.lot.vo.lotDemandHistory.LotDemandQuotationVo;
import com.lot.vo.lotDemandHistory.LotUserAgree;

import java.util.List;

public interface LotDemandHistoryService {
    List<Object> findListByUserId(LotDemandHistorySearchVo searchVo);

    void demandHistorySave(LotDemandQuotationVo vo);

    void userAgree(LotUserAgree saveVo);
}
