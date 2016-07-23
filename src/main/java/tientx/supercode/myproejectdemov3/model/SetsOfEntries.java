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
public class SetsOfEntries implements Serializable {

    private Integer idSetsOfEntries;
    private List<Entry> listEntries;

    public void setIdSetsOfEntries(Integer idSetsOfEntries) {
        this.idSetsOfEntries = idSetsOfEntries;
    }

    public void setListEntries(List<Entry> listEntries) {
        this.listEntries = listEntries;
    }

    public Integer getIdSetsOfEntries() {
        return idSetsOfEntries;
    }

    public List<Entry> getListEntries() {
        return listEntries;
    }

    public SetsOfEntries(Integer idSetsOfEntries, List<Entry> listEntries) {
        this.idSetsOfEntries = idSetsOfEntries;
        this.listEntries = listEntries;
    }

    public SetsOfEntries() {
    }
}
