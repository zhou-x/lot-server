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
@Table(name = "lot_demand_history", schema = "hgz2", catalog = "")
public class LotDemandHistoryEntity {
    private String demandHistoryId;
    private String startUserId;
    private String offerUserId;
    private String endUserId;
    private BigDecimal offerPrice;
    private BigDecimal endPrice;
    private String demandId;
    private String status;
    private String ynFlag;
    private String creator;
    private String editor;
    @CreatedDate
    private Timestamp createdTime;
    private Timestamp modifiedTime;

    @Id
    @Column(name = "demand_history_id")
    public String getDemandHistoryId() {
        return demandHistoryId;
    }

    public void setDemandHistoryId(String demandHistoryId) {
        this.demandHistoryId = demandHistoryId;
    }

    @Basic
    @Column(name = "start_user_id")
    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    @Basic
    @Column(name = "offer_user_id")
    public String getOfferUserId() {
        return offerUserId;
    }

    public void setOfferUserId(String offerUserId) {
        this.offerUserId = offerUserId;
    }

    @Basic
    @Column(name = "end_user_id")
    public String getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(String endUserId) {
        this.endUserId = endUserId;
    }

    @Basic
    @Column(name = "offer_price")
    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    @Basic
    @Column(name = "end_price")
    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
    }

    @Basic
    @Column(name = "demand_id")
    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
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
        LotDemandHistoryEntity that = (LotDemandHistoryEntity) o;
        return demandHistoryId == that.demandHistoryId &&
                Objects.equals(startUserId, that.startUserId) &&
                Objects.equals(offerUserId, that.offerUserId) &&
                Objects.equals(endUserId, that.endUserId) &&
                Objects.equals(offerPrice, that.offerPrice) &&
                Objects.equals(endPrice, that.endPrice) &&
                Objects.equals(demandId, that.demandId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(ynFlag, that.ynFlag) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(demandHistoryId, startUserId, offerUserId, endUserId, offerPrice, endPrice, demandId, status, ynFlag, creator, editor, createdTime, modifiedTime);
    }
}
