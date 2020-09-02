package com.lot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lot_resources_freight", schema = "hgz", catalog = "")
public class LotResourcesFreightEntity {
    private String resourcesFreightId;
    private String address;
    private String subordinateArea;
    private BigDecimal freight;
    private String ynFlag;
    private String creator;
    private String editor;
    @CreatedDate
    private Timestamp createdTime;
    private Timestamp modifiedTime;

    @Id
    @Column(name = "resources_freight_id")
    public String getResourcesFreightId() {
        return resourcesFreightId;
    }

    public void setResourcesFreightId(String resourcesFreightId) {
        this.resourcesFreightId = resourcesFreightId;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "subordinate_area")
    public String getSubordinateArea() {
        return subordinateArea;
    }

    public void setSubordinateArea(String subordinateArea) {
        this.subordinateArea = subordinateArea;
    }

    @Basic
    @Column(name = "freight")
    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
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
        LotResourcesFreightEntity that = (LotResourcesFreightEntity) o;
        return Objects.equals(resourcesFreightId, that.resourcesFreightId) &&
                Objects.equals(address, that.address) &&
                Objects.equals(subordinateArea, that.subordinateArea) &&
                Objects.equals(freight, that.freight) &&
                Objects.equals(ynFlag, that.ynFlag) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourcesFreightId, address, subordinateArea, freight, ynFlag, creator, editor, createdTime, modifiedTime);
    }
}
