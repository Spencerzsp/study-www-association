package com.shiant.study.core.system.model;

import com.shiant.common.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import sun.awt.SunHints;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-22 13:49
 **/
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_buss_text")
public class Article extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text_type")
    private String textType;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "update_date")
    private String updateDate;

    public Article() {
    }

    public Article(String textType, String title, String content, String createDate, String updateDate) {
        this.textType = textType;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
