package com.lot.vo.lotDemand;

import java.sql.Timestamp;

public class WxDemandSearchVo {
    private Timestamp date;
    private String brand;
    private String material;


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
