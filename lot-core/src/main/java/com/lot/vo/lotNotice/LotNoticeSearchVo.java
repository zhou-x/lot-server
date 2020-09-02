package com.lot.vo.lotNotice;

import com.lot.Base.BaseSearchVo;

public class LotNoticeSearchVo extends BaseSearchVo {
    private String title;
    private String isHide;
    private String status;
    private String isRecommend;
    private String model;
    private String todayDate;
    private String isReadCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getIsReadCount() {
        return isReadCount;
    }

    public void setIsReadCount(String isReadCount) {
        this.isReadCount = isReadCount;
    }
}
