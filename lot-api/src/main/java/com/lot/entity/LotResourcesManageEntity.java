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
@Table(name = "lot_resources_manage", schema = "hgz", catalog = "")
public class LotResourcesManageEntity {
    private String resourcesManageId;
    private String address;
    private String brand;
    @CreatedDate
    private Timestamp createdTime;
    private String creator;
    private String editor;
    private String material;
    private String model;
    private Timestamp modifiedTime;
    private BigDecimal price;
    private String ynFlag;
    private Long orders;
    private Integer freight;
    private String subordinateArea;

    @Id
    @Column(name = "resources_manage_id")
    public String getResourcesManageId() {
        return resourcesManageId;
    }

    public void setResourcesManageId(String resourcesManageId) {
        this.resourcesManageId = resourcesManageId;
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
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
    @Column(name = "material")
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "modified_time")
    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "yn_flag")
    public String getYnFlag() {
        return ynFlag;
    }

    public void setYnFlag(String ynFlag) {
        this.ynFlag = ynFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotResourcesManageEntity that = (LotResourcesManageEntity) o;
        return Objects.equals(resourcesManageId, that.resourcesManageId) &&
                Objects.equals(address, that.address) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(material, that.material) &&
                Objects.equals(model, that.model) &&
                Objects.equals(modifiedTime, that.modifiedTime) &&
                Objects.equals(price, that.price) &&
                Objects.equals(ynFlag, that.ynFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourcesManageId, address, brand, createdTime, creator, editor, material, model, modifiedTime, price, ynFlag);
    }

    @Basic
    @Column(name = "orders")
    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    @Basic
    @Column(name = "freight")
    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    @Basic
    @Column(name = "subordinate_area")
    public String getSubordinateArea() {
        return subordinateArea;
    }

    public void setSubordinateArea(String subordinateArea) {
        this.subordinateArea = subordinateArea;
    }
}
