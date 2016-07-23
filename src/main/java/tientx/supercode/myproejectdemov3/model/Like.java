/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.model;

import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class Like implements Serializable {

    private Integer idLike;
    private SetsOfEntries soeLike;

    public void setIdLike(Integer idLike) {
        this.idLike = idLike;
    }

    public void setSoeLike(SetsOfEntries soeLike) {
        this.soeLike = soeLike;
    }

    public Integer getIdLike() {
        return idLike;
    }

    public SetsOfEntries getSoeLike() {
        return soeLike;
    }

    public Like(Integer idLike, SetsOfEntries soeLike) {
        this.idLike = idLike;
        this.soeLike = soeLike;
    }

    public Like() {
    }
}
