package com.lot.vo.lotScheme;

import com.lot.entity.LotSpecificationEntity;

import java.sql.Timestamp;
import java.util.List;

public class LotSchemeVo{
    private String schemeId;
    private String brand;
    private String address;
    private String status;
    private Timestamp createdTime;
    private String specificationId;
    private String material;
    private String imgPaths;
    private String content;

    private List<LotSpecificationEntity> LotSpecificationList;


    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(String specificationId) {
        this.specificationId = specificationId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(String imgPaths) {
        this.imgPaths = imgPaths;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<LotSpecificationEntity> getLotSpecificationList() {
        return LotSpecificationList;
    }

    public void setLotSpecificationList(List<LotSpecificationEntity> lotSpecificationList) {
        LotSpecificationList = lotSpecificationList;
    }
}
