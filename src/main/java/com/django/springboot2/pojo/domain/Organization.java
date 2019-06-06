package com.django.springboot2.pojo.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liulongyun
 * @create 2019/6/4 21:23
 **/

@Alias("organization")
public class Organization implements Serializable {

  private BigDecimal id;
  private String orgName;
  private String orgAddress;
  private String orgWebSit;
  private String orgNum;

  private String orgTelNum;
  private Date orgRegistDate;
  private String note;

    public String getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(String orgNum) {
        this.orgNum = orgNum;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgWebSit() {
        return orgWebSit;
    }

    public void setOrgWebSit(String orgWebSit) {
        this.orgWebSit = orgWebSit;
    }

    public String getOrgTelNum() {
        return orgTelNum;
    }

    public void setOrgTelNum(String orgTelNum) {
        this.orgTelNum = orgTelNum;
    }

    public Date getOrgRegistDate() {
        return orgRegistDate;
    }

    public void setOrgRegistDate(Date orgRegistDate) {
        this.orgRegistDate = orgRegistDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
