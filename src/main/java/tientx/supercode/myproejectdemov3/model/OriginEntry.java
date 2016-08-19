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
public class OriginEntry
        implements Serializable
{

    private String idOriginEntry;
    private Date createDate;
    private String sContent;
    private String idUser;
    private String sCategory;
    private String sSentiment;

    public OriginEntry()
    {
    }

    public String getIdOriginEntry()
    {
        return idOriginEntry;
    }

    public void setIdOriginEntry(String idOriginEntry)
    {
        this.idOriginEntry = idOriginEntry;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public String getsContent()
    {
        return sContent;
    }

    public void setsContent(String sContent)
    {
        this.sContent = sContent;
    }

    public String getIdUser()
    {
        return idUser;
    }

    public void setIdUser(String idUser)
    {
        this.idUser = idUser;
    }

    public String getsCategory()
    {
        return sCategory;
    }

    public void setsCategory(String sCategory)
    {
        this.sCategory = sCategory;
    }

    public String getsSentiment()
    {
        return sSentiment;
    }

    public void setsSentiment(String sSentiment)
    {
        this.sSentiment = sSentiment;
    }

    public OriginEntry(String idOriginEntry, Date createDate, String sContent,
                       String idUser, String sCategory, String sSentiment)
    {
        this.idOriginEntry = idOriginEntry;
        this.createDate = createDate;
        this.sContent = sContent;
        this.idUser = idUser;
        this.sCategory = sCategory;
        this.sSentiment = sSentiment;
    }
}
