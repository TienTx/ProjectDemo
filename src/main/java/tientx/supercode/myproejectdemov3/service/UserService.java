/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.service;

import java.util.ArrayList;
import tientx.supercode.myproejectdemov3.model.Like;
import tientx.supercode.myproejectdemov3.model.OriginEntry;
import tientx.supercode.myproejectdemov3.model.Post;
import twitter4j.PagableResponseList;
import tientx.supercode.myproejectdemov3.model.User;
import twitter4j.ResponseList;
import twitter4j.Status;

/**
 *
 * @author zOzDarKzOz
 */
public interface UserService
{

    boolean addListUserUseBatch(PagableResponseList<twitter4j.User> list);

    ArrayList<User> getAll();

    boolean addListPostEntryUseBatch(ResponseList<Status> list, Long id);

    boolean addListLikeEntryUseBatch(ResponseList<Status> list, Long id);

    boolean addListCommentLikeEntryUseBatch(ResponseList<Status> list, Long id);

    boolean editListOriginEntryUseBatch(ArrayList<OriginEntry> list);

    boolean standardList();

    Post getPost(Long id);

    Like getLike(Long id);
}
