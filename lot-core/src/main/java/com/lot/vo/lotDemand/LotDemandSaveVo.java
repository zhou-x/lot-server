package com.lot.vo.lotDemand;

import com.lot.Base.BaseVo;
import com.lot.entity.LotFileEntity;

import java.util.List;

public class LotDemandSaveVo extends BaseVo {
    private String demandId;
    private String userId;
    private String brand;
    private String material;
    private String model;
    private String weight;
    private String status;
    private List<LotFileEntity> imgPaths;
    private String demandRequestId;

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LotFileEntity> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<LotFileEntity> imgPaths) {
        this.imgPaths = imgPaths;
    }

    public String getDemandRequestId() {
        return demandRequestId;
    }

    public void setDemandRequestId(String demandRequestId) {
        this.demandRequestId = demandRequestId;
    }
}
