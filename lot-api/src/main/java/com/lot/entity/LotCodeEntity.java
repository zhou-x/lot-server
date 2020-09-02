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
@Table(name = "lot_code", schema = "lots")
public class LotCodeEntity {
    private String lotCodeId;
    private String code;
    private String value;
    private String codeBelong;
    private String codeDesc;
    private Integer showOrder;
    private String parentCode;
    private String ynFlag;
    private String creator;
    private String editor;
    @CreatedDate
    private Timestamp createdTime;
    private Timestamp modifiedTime;

    @Id
    @Column(name = "lot_code_id")
    public String getLotCodeId() {
        return lotCodeId;
    }

    public void setLotCodeId(String lotCodeId) {
        this.lotCodeId = lotCodeId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "code_belong")
    public String getCodeBelong() {
        return codeBelong;
    }

    public void setCodeBelong(String codeBelong) {
        this.codeBelong = codeBelong;
    }

    @Basic
    @Column(name = "code_desc")
    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    @Basic
    @Column(name = "show_order")
    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    @Basic
    @Column(name = "parent_code")
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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
        LotCodeEntity that = (LotCodeEntity) o;
        return lotCodeId == that.lotCodeId &&
                Objects.equals(code, that.code) &&
                Objects.equals(codeBelong, that.codeBelong) &&
                Objects.equals(codeDesc, that.codeDesc) &&
                Objects.equals(showOrder, that.showOrder) &&
                Objects.equals(parentCode, that.parentCode) &&
                Objects.equals(ynFlag, that.ynFlag) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotCodeId, code, codeBelong, codeDesc, showOrder, parentCode, ynFlag, creator, editor, createdTime, modifiedTime);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
