/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author zOzDarKzOz
 */
public class OriginEntry implements Serializable {

    private String idOriginEntry;
    private Date createDate;
    private String sContent;
    private String idUser;

    public OriginEntry() {
    }

    public OriginEntry(String idOriginEntry, Date createDate, String sContent, String idUser) {
        this.idOriginEntry = idOriginEntry;
        this.createDate = createDate;
        this.sContent = sContent;
        this.idUser = idUser;
    }

    public String getIdOriginEntry() {
        return idOriginEntry;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getsContent() {
        return sContent;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdOriginEntry(String idOriginEntry) {
        this.idOriginEntry = idOriginEntry;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setsContent(String sContent) {
        this.sContent = sContent;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    
}
