package com.shiant.study.core.system.model;

import com.shiant.common.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-17 14:28
 **/
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "t_buss_school")
public class School extends BaseEntity implements Serializable {

    private static final long schoolVersionUID = 2997355432220175073L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "school_name_c")
    private String schoolNameC;

    @Column(name = "school_name_e")
    private String schoolNameE;

    @Column(name = "school_log")
    private String schoolLog;

    @Column(name = "schooling")
    private String schooLing;

    @Column(name = "school_property")
    private String schoolProperty;

    @Column(name = "new_students_enrollment")
    private String studentsEnrollment;

    @Column(name = "school_address")
    private String schoolAddress;

    @Column(name = "profession_num")
    private String professionNum;

    @Column(name = "school_about")
    private String schoolAbout;

    @Column(name = "create_date")
    // @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "update_date")
    // @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public static long getSchoolVersionUID() {
        return schoolVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolNameC() {
        return schoolNameC;
    }

    public void setSchoolNameC(String schoolNameC) {
        this.schoolNameC = schoolNameC;
    }

    public String getSchoolNameE() {
        return schoolNameE;
    }

    public void setSchoolNameE(String schoolNameE) {
        this.schoolNameE = schoolNameE;
    }

    public String getSchoolLog() {
        return schoolLog;
    }

    public void setSchoolLog(String schoolLog) {
        this.schoolLog = schoolLog;
    }

    public String getSchooLing() {
        return schooLing;
    }

    public void setSchooLing(String schooLing) {
        this.schooLing = schooLing;
    }

    public String getSchoolProperty() {
        return schoolProperty;
    }

    public void setSchoolProperty(String schoolProperty) {
        this.schoolProperty = schoolProperty;
    }

    public String getStudentsEnrollment() {
        return studentsEnrollment;
    }

    public void setStudentsEnrollment(String studentsEnrollment) {
        this.studentsEnrollment = studentsEnrollment;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getProfessionNum() {
        return professionNum;
    }

    public void setProfessionNum(String professionNum) {
        this.professionNum = professionNum;
    }

    public String getSchoolAbout() {
        return schoolAbout;
    }

    public void setSchoolAbout(String schoolAbout) {
        this.schoolAbout = schoolAbout;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public School() {
    }

    public School(
            String schoolNameC,
            String schoolNameE,
            String schoolLog,
            String schooLing,
            String schoolProperty,
            String studentsEnrollment,
            String schoolAddress,
            String professionNum,
            String schoolAbout,
            Date createDate,
            Date updateDate) {
        this.schoolNameC = schoolNameC;
        this.schoolNameE = schoolNameE;
        this.schoolLog = schoolLog;
        this.schooLing = schooLing;
        this.schoolProperty = schoolProperty;
        this.studentsEnrollment = studentsEnrollment;
        this.schoolAddress = schoolAddress;
        this.professionNum = professionNum;
        this.schoolAbout = schoolAbout;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
