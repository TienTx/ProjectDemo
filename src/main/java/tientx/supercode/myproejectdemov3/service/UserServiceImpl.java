/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.service;

import java.util.ArrayList;
import tientx.supercode.myproejectdemov3.dao.LikeDao;
import tientx.supercode.myproejectdemov3.dao.OriginEntryDao;
import tientx.supercode.myproejectdemov3.dao.OriginEntryDaoImpl;
import tientx.supercode.myproejectdemov3.dao.PostDao;
import tientx.supercode.myproejectdemov3.dao.UserDao;
import tientx.supercode.myproejectdemov3.dao.UserDaoImpl;
import tientx.supercode.myproejectdemov3.model.Like;
import tientx.supercode.myproejectdemov3.model.OriginEntry;
import tientx.supercode.myproejectdemov3.model.Post;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.User;

/**
 *
 * @author zOzDarKzOz
 */
public class UserServiceImpl
        implements UserService
{

    private UserDao userDao;
    private OriginEntryDao oeDao;
    private PostDao postDao;
    private LikeDao likeDao;

    public UserServiceImpl()
    {
        this.userDao = new UserDaoImpl();
        this.oeDao = new OriginEntryDaoImpl();
    }

    @Override
    public boolean addListUserUseBatch(PagableResponseList<User> list)
    {
        return userDao.insertListUseBatch(list);
    }

    @Override
    public ArrayList<tientx.supercode.myproejectdemov3.model.User> getAll()
    {
        return userDao.getAll();
    }

    @Override
    public boolean addListPostEntryUseBatch(ResponseList<Status> list, Long id)
    {
        return oeDao.insertListPostEntryUseBatch(list, id);
    }

    @Override
    public boolean editListOriginEntryUseBatch(ArrayList<OriginEntry> list)
    {
        return oeDao.updateListOriginEntryUseBatch(list);
    }

    @Override
    public boolean addListLikeEntryUseBatch(ResponseList<Status> list, Long id)
    {
        return oeDao.insertListLikeEntryUseBatch(list, id);
    }

    @Override
    public boolean standardList()
    {
        return userDao.standardList();
    }

    @Override
    public boolean addListCommentLikeEntryUseBatch(ResponseList<Status> list,
                                                   Long id)
    {
        return oeDao.insertListCommentLikeEntryUseBatch(list, id);
    }

    @Override
    public Post getPost(Long id)
    {
        return postDao.getByUserId(id);
    }

    @Override
    public Like getLike(Long id)
    {
        return likeDao.getByUserId(id);
    }

}
