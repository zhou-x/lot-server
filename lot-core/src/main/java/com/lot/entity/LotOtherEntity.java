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
@Table(name = "lot_other", schema = "hgz", catalog = "")
public class LotOtherEntity {
    private String otherId;
    private String aboutUs;
    private String contactUs;
    private String userProtocol;
    private String privacyPolicy;
    private String demandExample;
    private String commonProblem;
    private String ynFlag;
    private String creator;
    private String editor;
    @CreatedDate
    private Timestamp createdTime;
    private Timestamp modifiedTime;
    private String startImg;

    @Id
    @Column(name = "other_id")
    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    @Basic
    @Column(name = "about_us")
    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    @Basic
    @Column(name = "contact_us")
    public String getContactUs() {
        return contactUs;
    }

    public void setContactUs(String contactUs) {
        this.contactUs = contactUs;
    }

    @Basic
    @Column(name = "user_protocol")
    public String getUserProtocol() {
        return userProtocol;
    }

    public void setUserProtocol(String userProtocol) {
        this.userProtocol = userProtocol;
    }

    @Basic
    @Column(name = "privacy_policy")
    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    @Basic
    @Column(name = "demand_example")
    public String getDemandExample() {
        return demandExample;
    }

    public void setDemandExample(String demandExample) {
        this.demandExample = demandExample;
    }

    @Basic
    @Column(name = "common_problem")
    public String getCommonProblem() {
        return commonProblem;
    }

    public void setCommonProblem(String commonProblem) {
        this.commonProblem = commonProblem;
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
        LotOtherEntity that = (LotOtherEntity) o;
        return otherId == that.otherId &&
                Objects.equals(aboutUs, that.aboutUs) &&
                Objects.equals(contactUs, that.contactUs) &&
                Objects.equals(userProtocol, that.userProtocol) &&
                Objects.equals(privacyPolicy, that.privacyPolicy) &&
                Objects.equals(demandExample, that.demandExample) &&
                Objects.equals(commonProblem, that.commonProblem) &&
                Objects.equals(ynFlag, that.ynFlag) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(otherId, aboutUs, contactUs, userProtocol, privacyPolicy, demandExample, commonProblem, ynFlag, creator, editor, createdTime, modifiedTime);
    }

    @Basic
    @Column(name = "start_img")
    public String getStartImg() {
        return startImg;
    }

    public void setStartImg(String startImg) {
        this.startImg = startImg;
    }
}
