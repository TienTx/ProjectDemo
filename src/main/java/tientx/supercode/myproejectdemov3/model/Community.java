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
public class Community implements Serializable {

    private String idCommunity;
    private List<String> sokName;
    private List<String> sokCategory;
    private List<String> sokDescription;

    public void setIdCommunity(String idCommunity) {
        this.idCommunity = idCommunity;
    }

    public void setSokName(List<String> sokName) {
        this.sokName = sokName;
    }

    public void setSokCategory(List<String> sokCategory) {
        this.sokCategory = sokCategory;
    }

    public void setSokDescription(List<String> sokDescription) {
        this.sokDescription = sokDescription;
    }

    public String getIdCommunity() {
        return idCommunity;
    }

    public List<String> getSokName() {
        return sokName;
    }

    public List<String> getSokCategory() {
        return sokCategory;
    }

    public List<String> getSokDescription() {
        return sokDescription;
    }

    public Community(String idCommunity, List<String> sokName, List<String> sokCategory, List<String> sokDescription) {
        this.idCommunity = idCommunity;
        this.sokName = sokName;
        this.sokCategory = sokCategory;
        this.sokDescription = sokDescription;
    }

    public Community() {
    }
}
