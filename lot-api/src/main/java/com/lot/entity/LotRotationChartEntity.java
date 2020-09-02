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
@Table(name = "lot_rotationchart", schema = "hgz", catalog = "")
public class LotRotationChartEntity {
    private String rotationChartId;
    private String imgPath;
    private String imgDesc;
    private String isHide;
    private String orders;
    private String imgLink;
    private String remark;
    private String ynFlag;
    private String creator;
    private String editor;
    @CreatedDate
    private Timestamp createdTime;
    private Timestamp modifiedTime;

    @Id
    @Column(name = "rotationchart_id")
    public String getRotationChartId() {
        return rotationChartId;
    }

    public void setRotationChartId(String rotationChartId) {
        this.rotationChartId = rotationChartId;
    }

    @Basic
    @Column(name = "img_path")
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Basic
    @Column(name = "img_desc")
    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    @Basic
    @Column(name = "is_hide")
    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    @Basic
    @Column(name = "orders")
    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    @Basic
    @Column(name = "img_link")
    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
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
        LotRotationChartEntity that = (LotRotationChartEntity) o;
        return rotationChartId == that.rotationChartId &&
                Objects.equals(imgPath, that.imgPath) &&
                Objects.equals(imgDesc, that.imgDesc) &&
                Objects.equals(isHide, that.isHide) &&
                Objects.equals(orders, that.orders) &&
                Objects.equals(imgLink, that.imgLink) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(ynFlag, that.ynFlag) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rotationChartId, imgPath, imgDesc, isHide, orders, imgLink, remark, ynFlag, creator, editor, createdTime, modifiedTime);
    }
}
