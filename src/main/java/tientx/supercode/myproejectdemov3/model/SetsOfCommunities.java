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
public class SetsOfCommunities implements Serializable {

    private String idSetsOfCommunities;
    private List<Community> listCommunities;

    public void setIdSetsOfCommunities(String idSetsOfCommunities) {
        this.idSetsOfCommunities = idSetsOfCommunities;
    }

    public void setListCommunities(List<Community> listCommunities) {
        this.listCommunities = listCommunities;
    }

    public String getIdSetsOfCommunities() {
        return idSetsOfCommunities;
    }

    public List<Community> getListCommunities() {
        return listCommunities;
    }

    public SetsOfCommunities(String idSetsOfCommunities, List<Community> listCommunities) {
        this.idSetsOfCommunities = idSetsOfCommunities;
        this.listCommunities = listCommunities;
    }

    public SetsOfCommunities() {
    }
}
