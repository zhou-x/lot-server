package com.lot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lot_demand_request", schema = "hgz", catalog = "")
public class LotDemandRequestEntity {
    private String demandRequestId;
    private String userId;
    private String demandContent;
    private String demandImgPath;
    private String remark;
    private String status;
    private String ynFlag;
    private String creator;
    private String editor;
    @CreatedDate
    private Timestamp createdTime;
    private Timestamp modifiedTime;

    @Id
    @Column(name = "demand_request_id")
    public String getDemandRequestId() {
        return demandRequestId;
    }

    public void setDemandRequestId(String demandRequestId) {
        this.demandRequestId = demandRequestId;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "demand_content")
    public String getDemandContent() {
        return demandContent;
    }

    public void setDemandContent(String demandContent) {
        this.demandContent = demandContent;
    }

    @Basic
    @Column(name = "demand_img_path")
    public String getDemandImgPath() {
        return demandImgPath;
    }

    public void setDemandImgPath(String demandImgPath) {
        this.demandImgPath = demandImgPath;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "yn_flag")
    public String getYnFlag() {
        return ynFlag;
    }

    public void setYnFlag(String ynFlag) {
        this.ynFlag = ynFlag;
    }

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "editor")
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Basic
    @Column(name = "created_time")
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "modified_time")
    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotDemandRequestEntity that = (LotDemandRequestEntity) o;
        return demandRequestId == that.demandRequestId &&
                userId == that.userId &&
                Objects.equals(demandContent, that.demandContent) &&
                Objects.equals(demandImgPath, that.demandImgPath) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(status, that.status) &&
                Objects.equals(ynFlag, that.ynFlag) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(demandRequestId, userId, demandContent, demandImgPath, remark, status, ynFlag, creator, editor, createdTime, modifiedTime);
    }
}
