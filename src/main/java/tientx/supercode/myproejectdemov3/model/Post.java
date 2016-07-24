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
public class Post implements Serializable {

    private String idPost;
    private SetsOfEntries soePost;

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public void setSoePost(SetsOfEntries soePost) {
        this.soePost = soePost;
    }

    public String getIdPost() {
        return idPost;
    }

    public SetsOfEntries getSoePost() {
        return soePost;
    }

    public Post(String idPost, SetsOfEntries soePost) {
        this.idPost = idPost;
        this.soePost = soePost;
    }

    public Post() {
    }
}
