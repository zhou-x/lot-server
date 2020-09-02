package com.lot.vo.lotNotice;

import com.lot.Base.BaseVo;
import com.lot.entity.LotFileEntity;

import java.util.List;

public class LotNoticeSaveVo extends BaseVo {
    private String noticeId;
    private String title;
    private String model;
    private String content;
    private String isHide;
    private Integer orders;
    private Integer readCount = 0;
    private List<LotFileEntity> imgPaths;
    private String status;
    private String isRecommend;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public List<LotFileEntity> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<LotFileEntity> imgPaths) {
        this.imgPaths = imgPaths;
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
}
