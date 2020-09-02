package com.lot.vo.lotDemandHistory;

import com.lot.Base.BaseVo;

import java.math.BigDecimal;

public class LotDemandQuotationVo extends BaseVo {
    private String demandId;
    private String startUserId;
    private BigDecimal offerPrice;

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }
}
