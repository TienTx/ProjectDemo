/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zOzDarKzOz
 */
public class Entry
        implements Serializable
{

    private String idEntry;
    private List<String> sokCategory;
    private Double dSentiment;

    public void setIdEntry(String idEntry)
    {
        this.idEntry = idEntry;
    }

    public void setSokCategory(List<String> sokCategory)
    {
        this.sokCategory = sokCategory;
    }

    public void setdSentiment(Double dSentiment)
    {
        this.dSentiment = dSentiment;
    }

    public String getIdEntry()
    {
        return idEntry;
    }

    public List<String> getSokCategory()
    {
        return sokCategory;
    }

    public Double getdSentiment()
    {
        return dSentiment;
    }

    public Entry(String idEntry, List<String> sokCategory, Double dSentiment)
    {
        this.idEntry = idEntry;
        this.sokCategory = sokCategory;
        this.dSentiment = dSentiment;
    }

    public Entry()
    {
    }
}
