package com.lot.vo.lotResources;

import java.math.BigDecimal;

/**
 * 返回的方案中间结果类
 * */
public class ResultSchemeVo {
    private String address; //地区
    private String brand;    //品牌
    private String model;     //规格
    private String material;   //品类（材质）
    private BigDecimal price;   //单价   /吨
    private String weight;   //重量 /吨
    private String resourcesId;   //资源Id

    private String subordinateArea; //二级地区

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getSubordinateArea() {
        return subordinateArea;
    }

    public void setSubordinateArea(String subordinateArea) {
        this.subordinateArea = subordinateArea;
    }
}
