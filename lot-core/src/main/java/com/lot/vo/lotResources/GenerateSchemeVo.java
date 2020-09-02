package com.lot.vo.lotResources;

import java.util.List;

public class GenerateSchemeVo {
    private String address;
    private List<String> brandList;
    private List<MaterialAndModel> materialAndModelList;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<String> brandList) {
        this.brandList = brandList;
    }

    public List<MaterialAndModel> getMaterialAndModelList() {
        return materialAndModelList;
    }

    public void setMaterialAndModelList(List<MaterialAndModel> materialAndModelList) {
        this.materialAndModelList = materialAndModelList;
    }
}
