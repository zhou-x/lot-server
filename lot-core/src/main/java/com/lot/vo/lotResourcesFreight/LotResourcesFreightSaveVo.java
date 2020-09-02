package com.lot.vo.lotResourcesFreight;

import com.lot.Base.BaseVo;

import java.math.BigDecimal;

public class LotResourcesFreightSaveVo extends BaseVo {
    private String resourcesFreightId;
    private String address;
    private String subordinateArea;
    private BigDecimal freight;

    public String getResourcesFreightId() {
        return resourcesFreightId;
    }

    public void setResourcesFreightId(String resourcesFreightId) {
        this.resourcesFreightId = resourcesFreightId;
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

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }
}
