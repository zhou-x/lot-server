package com.lot.vo.lotRotationChart;

import com.lot.Base.BaseSearchVo;

import java.sql.Timestamp;
import java.util.List;

public class LotRotationChartSearchVo extends BaseSearchVo {
    private String isHide;
    private List<Timestamp> createDate;

    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public List<Timestamp> getCreateDate() {
        return createDate;
    }

    public void setCreateDate(List<Timestamp> createDate) {
        this.createDate = createDate;
    }
}
