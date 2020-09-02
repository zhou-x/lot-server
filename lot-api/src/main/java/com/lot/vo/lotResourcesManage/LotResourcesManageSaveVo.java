package com.lot.vo.lotResourcesManage;

import com.lot.Base.BaseVo;

import java.math.BigDecimal;

public class LotResourcesManageSaveVo extends BaseVo {
    private String resourcesManageId;
    private String brand;
    private String model;
    private BigDecimal price;
    private String material;
    private String address;
    private String subordinateArea;

    public String getResourcesManageId() {
        return resourcesManageId;
    }

    public void setResourcesManageId(String resourcesManageId) {
        this.resourcesManageId = resourcesManageId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubordinateArea() {
        return subordinateArea;
    }

    public void setSubordinateArea(String subordinateArea) {
        this.subordinateArea = subordinateArea;
    }
}
