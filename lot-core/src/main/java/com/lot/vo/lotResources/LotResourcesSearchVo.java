package com.lot.vo.lotResources;

import com.lot.Base.BaseSearchVo;

import java.sql.Timestamp;
import java.util.List;

public class LotResourcesSearchVo extends BaseSearchVo {
    private String brand;
    private String material_or_model;
    private List<Timestamp> createDate;
    private String selectDate;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaterial_or_model() {
        return material_or_model;
    }

    public void setMaterial_or_model(String material_or_model) {
        this.material_or_model = material_or_model;
    }

    public List<Timestamp> getCreateDate() {
        return createDate;
    }

    public void setCreateDate(List<Timestamp> createDate) {
        this.createDate = createDate;
    }

    public String getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }
}
