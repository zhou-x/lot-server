package com.lot.vo.lotRotationChart;

import com.lot.Base.BaseVo;
import com.lot.entity.LotFileEntity;

import java.util.List;

public class LotRotationChartSaveVo extends BaseVo {
    private String rotationChartId;
    private String imgPath;
    private String imgDesc;
    private String isHide;
    private int orders;
    private String imgLink;
    private String remark;
    private List<LotFileEntity> imgPaths;

    public String getRotationChartId() {
        return rotationChartId;
    }

    public void setRotationChartId(String rotationChartId) {
        this.rotationChartId = rotationChartId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<LotFileEntity> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<LotFileEntity> imgPaths) {
        this.imgPaths = imgPaths;
    }
}
