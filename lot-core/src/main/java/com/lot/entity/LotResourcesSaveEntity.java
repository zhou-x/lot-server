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
@Table(name = "lot_resources_save", schema = "hgz2", catalog = "")
public class LotResourcesSaveEntity {
    private String resourcesSaveId;
    private BigDecimal hbr400EP6;
    private BigDecimal hbr400EP8;
    private BigDecimal hbr400EP10;
    private BigDecimal hbr400EX6;
    private BigDecimal hbr400EX8;
    private BigDecimal hbr400EX10;
    private BigDecimal hbr400EL14;
    private BigDecimal hbr400EL16;
    private BigDecimal hbr400EL18;
    private BigDecimal hbr400EL20;
    private BigDecimal hbr400EL22;
    private BigDecimal hbr400EL25;
    private BigDecimal hbr400EL28;
    private BigDecimal hbr400EL32;
    private String ynFlag;
    private String creator;
    private String editor;
    @CreatedDate
    private Timestamp createdTime;
    private Timestamp modifiedTime;
    private BigDecimal hrb400EL12;

    @Id
    @Column(name = "resources_save_id")
    public String getResourcesSaveId() {
        return resourcesSaveId;
    }

    public void setResourcesSaveId(String resourcesSaveId) {
        this.resourcesSaveId = resourcesSaveId;
    }

    @Basic
    @Column(name = "HBR400E_P_6")
    public BigDecimal getHbr400EP6() {
        return hbr400EP6;
    }

    public void setHbr400EP6(BigDecimal hbr400EP6) {
        this.hbr400EP6 = hbr400EP6;
    }

    @Basic
    @Column(name = "HBR400E_P_8")
    public BigDecimal getHbr400EP8() {
        return hbr400EP8;
    }

    public void setHbr400EP8(BigDecimal hbr400EP8) {
        this.hbr400EP8 = hbr400EP8;
    }

    @Basic
    @Column(name = "HBR400E_P_10")
    public BigDecimal getHbr400EP10() {
        return hbr400EP10;
    }

    public void setHbr400EP10(BigDecimal hbr400EP10) {
        this.hbr400EP10 = hbr400EP10;
    }

    @Basic
    @Column(name = "HBR400E_X_6")
    public BigDecimal getHbr400EX6() {
        return hbr400EX6;
    }

    public void setHbr400EX6(BigDecimal hbr400EX6) {
        this.hbr400EX6 = hbr400EX6;
    }

    @Basic
    @Column(name = "HBR400E_X_8")
    public BigDecimal getHbr400EX8() {
        return hbr400EX8;
    }

    public void setHbr400EX8(BigDecimal hbr400EX8) {
        this.hbr400EX8 = hbr400EX8;
    }

    @Basic
    @Column(name = "HBR400E_X_10")
    public BigDecimal getHbr400EX10() {
        return hbr400EX10;
    }

    public void setHbr400EX10(BigDecimal hbr400EX10) {
        this.hbr400EX10 = hbr400EX10;
    }

    @Basic
    @Column(name = "HBR400E_L_14")
    public BigDecimal getHbr400EL14() {
        return hbr400EL14;
    }

    public void setHbr400EL14(BigDecimal hbr400EL14) {
        this.hbr400EL14 = hbr400EL14;
    }

    @Basic
    @Column(name = "HBR400E_L_16")
    public BigDecimal getHbr400EL16() {
        return hbr400EL16;
    }

    public void setHbr400EL16(BigDecimal hbr400EL16) {
        this.hbr400EL16 = hbr400EL16;
    }

    @Basic
    @Column(name = "HBR400E_L_18")
    public BigDecimal getHbr400EL18() {
        return hbr400EL18;
    }

    public void setHbr400EL18(BigDecimal hbr400EL18) {
        this.hbr400EL18 = hbr400EL18;
    }

    @Basic
    @Column(name = "HBR400E_L_20")
    public BigDecimal getHbr400EL20() {
        return hbr400EL20;
    }

    public void setHbr400EL20(BigDecimal hbr400EL20) {
        this.hbr400EL20 = hbr400EL20;
    }

    @Basic
    @Column(name = "HBR400E_L_22")
    public BigDecimal getHbr400EL22() {
        return hbr400EL22;
    }

    public void setHbr400EL22(BigDecimal hbr400EL22) {
        this.hbr400EL22 = hbr400EL22;
    }

    @Basic
    @Column(name = "HBR400E_L_25")
    public BigDecimal getHbr400EL25() {
        return hbr400EL25;
    }

    public void setHbr400EL25(BigDecimal hbr400EL25) {
        this.hbr400EL25 = hbr400EL25;
    }

    @Basic
    @Column(name = "HBR400E_L_28")
    public BigDecimal getHbr400EL28() {
        return hbr400EL28;
    }

    public void setHbr400EL28(BigDecimal hbr400EL28) {
        this.hbr400EL28 = hbr400EL28;
    }

    @Basic
    @Column(name = "HBR400E_L_32")
    public BigDecimal getHbr400EL32() {
        return hbr400EL32;
    }

    public void setHbr400EL32(BigDecimal hbr400EL32) {
        this.hbr400EL32 = hbr400EL32;
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
        LotResourcesSaveEntity that = (LotResourcesSaveEntity) o;
        return Objects.equals(resourcesSaveId, that.resourcesSaveId) &&
                Objects.equals(hbr400EP6, that.hbr400EP6) &&
                Objects.equals(hbr400EP8, that.hbr400EP8) &&
                Objects.equals(hbr400EP10, that.hbr400EP10) &&
                Objects.equals(hbr400EX6, that.hbr400EX6) &&
                Objects.equals(hbr400EX8, that.hbr400EX8) &&
                Objects.equals(hbr400EX10, that.hbr400EX10) &&
                Objects.equals(hbr400EL14, that.hbr400EL14) &&
                Objects.equals(hbr400EL16, that.hbr400EL16) &&
                Objects.equals(hbr400EL18, that.hbr400EL18) &&
                Objects.equals(hbr400EL20, that.hbr400EL20) &&
                Objects.equals(hbr400EL22, that.hbr400EL22) &&
                Objects.equals(hbr400EL25, that.hbr400EL25) &&
                Objects.equals(hbr400EL28, that.hbr400EL28) &&
                Objects.equals(hbr400EL32, that.hbr400EL32) &&
                Objects.equals(ynFlag, that.ynFlag) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourcesSaveId, hbr400EP6, hbr400EP8, hbr400EP10, hbr400EX6, hbr400EX8, hbr400EX10, hbr400EL14, hbr400EL16, hbr400EL18, hbr400EL20, hbr400EL22, hbr400EL25, hbr400EL28, hbr400EL32, ynFlag, creator, editor, createdTime, modifiedTime);
    }

    @Basic
    @Column(name = "HRB400E_L_12")
    public BigDecimal getHrb400EL12() {
        return hrb400EL12;
    }

    public void setHrb400EL12(BigDecimal hrb400EL12) {
        this.hrb400EL12 = hrb400EL12;
    }
}
