package com.lot.vo.lotDemandHistory;

public class LotUserAgree {
    private String status;   //Y表示同意   S表示拒绝
    private String offerUserId;   //报价的用户   变成start用户
    private String demandId;  //需求id


    //当前用户的id


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfferUserId() {
        return offerUserId;
    }

    public void setOfferUserId(String offerUserId) {
        this.offerUserId = offerUserId;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }
}
