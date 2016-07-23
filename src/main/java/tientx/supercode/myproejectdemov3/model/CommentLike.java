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
public class CommentLike implements Serializable {

    private Integer idCommentLike;
    private SetsOfEntries soePositive;
    private SetsOfEntries soeNegative;

    public void setIdCommentLike(Integer idCommentLike) {
        this.idCommentLike = idCommentLike;
    }

    public void setSoePositive(SetsOfEntries soePositive) {
        this.soePositive = soePositive;
    }

    public void setSoeNegative(SetsOfEntries soeNegative) {
        this.soeNegative = soeNegative;
    }

    public Integer getIdCommentLike() {
        return idCommentLike;
    }

    public SetsOfEntries getSoePositive() {
        return soePositive;
    }

    public SetsOfEntries getSoeNegative() {
        return soeNegative;
    }

    public CommentLike(Integer idCommentLike, SetsOfEntries soePositive, SetsOfEntries soeNegative) {
        this.idCommentLike = idCommentLike;
        this.soePositive = soePositive;
        this.soeNegative = soeNegative;
    }

    public CommentLike() {
    }
}
