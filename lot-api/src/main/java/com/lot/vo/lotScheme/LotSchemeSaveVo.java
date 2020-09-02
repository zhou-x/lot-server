package com.lot.vo.lotScheme;

import com.lot.entity.LotFileEntity;
import com.lot.entity.LotSpecificationEntity;

import java.util.List;

public class LotSchemeSaveVo {
    private String brand;
    private String address;
    private List<LotSpecificationEntity> specificationList;
    private List<LotFileEntity> imgPaths;
    private String content;

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

    public List<LotSpecificationEntity> getSpecificationList() {
        return specificationList;
    }

    public void setSpecificationList(List<LotSpecificationEntity> specificationList) {
        this.specificationList = specificationList;
    }

    public List<LotFileEntity> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<LotFileEntity> imgPaths) {
        this.imgPaths = imgPaths;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
