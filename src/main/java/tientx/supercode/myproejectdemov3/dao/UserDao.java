/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import java.util.ArrayList;
import tientx.supercode.myproejectdemov3.model.User;
import twitter4j.PagableResponseList;

/**
 *
 * @author zOzDarKzOz
 */
public interface UserDao {

    boolean insertUser(User user);

    boolean insertListUserUseBatch(PagableResponseList<twitter4j.User> list);

    ArrayList<User> getAll();
}
