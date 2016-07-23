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
public class User implements Serializable {

    private Integer idUser;
    private String fullName;
    private Post post;
    private Like like;
    private Join join;
    private CommentLike commentlike;

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    public void setJoin(Join join) {
        this.join = join;
    }

    public void setCommentlike(CommentLike commentlike) {
        this.commentlike = commentlike;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public Post getPost() {
        return post;
    }

    public Like getLike() {
        return like;
    }

    public Join getJoin() {
        return join;
    }

    public CommentLike getCommentlike() {
        return commentlike;
    }

    public User(Integer idUser, String fullName, Post post, Like like, Join join, CommentLike commentlike) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.post = post;
        this.like = like;
        this.join = join;
        this.commentlike = commentlike;
    }

    public User() {
    }
}
