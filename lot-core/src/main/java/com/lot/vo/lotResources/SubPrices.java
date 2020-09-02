package com.lot.vo.lotResources;

import java.math.BigDecimal;

public class SubPrices {
    private BigDecimal price;
    private String address;
    private String brand;
    private String material;
    private String model;
    private String status;
    private String subordinateArea;
    private ModelDetailed modelDetailed;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ModelDetailed getModelDetailed() {
        return modelDetailed;
    }

    public void setModelDetailed(ModelDetailed modelDetailed) {
        this.modelDetailed = modelDetailed;
    }

    public String getSubordinateArea() {
        return subordinateArea;
    }

    public void setSubordinateArea(String subordinateArea) {
        this.subordinateArea = subordinateArea;
    }
}
