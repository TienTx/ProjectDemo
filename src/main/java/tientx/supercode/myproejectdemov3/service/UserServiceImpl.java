/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.service;

import java.util.ArrayList;
import tientx.supercode.myproejectdemov3.dao.OriginEntryDao;
import tientx.supercode.myproejectdemov3.dao.OriginEntryDaoImpl;
import tientx.supercode.myproejectdemov3.dao.UserDao;
import tientx.supercode.myproejectdemov3.dao.UserDaoImpl;
import tientx.supercode.myproejectdemov3.model.OriginEntry;
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

    public UserServiceImpl()
    {
        this.userDao = new UserDaoImpl();
        this.oeDao = new OriginEntryDaoImpl();
    }

    @Override
    public boolean addListUserUseBatch(PagableResponseList<User> list)
    {
        return userDao.insertListUserUseBatch(list);
    }

    @Override
    public ArrayList<tientx.supercode.myproejectdemov3.model.User> getAll()
    {
        return userDao.getAll();
    }

    @Override
    public boolean addListOriginEntryUseBatch(ResponseList<Status> list, Long id)
    {
        return oeDao.insertListOriginEntryUseBatch(list, id);
    }

    @Override
    public boolean editListOriginEntryUseBatch(ArrayList<OriginEntry> list)
    {
        return oeDao.updateListOriginEntryUseBatch(list);
    }

}
