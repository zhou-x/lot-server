package com.lot.vo.lotDemandRequest;

import com.lot.Base.BaseVo;
import com.lot.entity.LotFileEntity;

import java.util.List;

public class LotDemandRequestSaveVo extends BaseVo {
    private String demandRequestId;
    private String userId;
    private String demandContent;
    private List<LotFileEntity> imgPaths;
    private String remark;
    private String status;

    public String getDemandRequestId() {
        return demandRequestId;
    }

    public void setDemandRequestId(String demandRequestId) {
        this.demandRequestId = demandRequestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDemandContent() {
        return demandContent;
    }

    public void setDemandContent(String demandContent) {
        this.demandContent = demandContent;
    }

    public List<LotFileEntity> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<LotFileEntity> imgPaths) {
        this.imgPaths = imgPaths;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
