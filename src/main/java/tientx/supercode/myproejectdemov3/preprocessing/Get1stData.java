/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import tientx.supercode.myproejectdemov3.dao.UserDao;
import tientx.supercode.myproejectdemov3.dao.UserDaoImpl;
import tientx.supercode.myproejectdemov3.service.TwitterService;
import tientx.supercode.myproejectdemov3.service.TwitterServiceImpl;
import twitter4j.PagableResponseList;
import twitter4j.User;

/**
 *
 * @author zOzDarKzOz
 */
public class Get1stData {

    private static TwitterService ts = new TwitterServiceImpl();
    private static UserDao userDao = new UserDaoImpl();
    private static final String ORIGIN_USER_SCREEN_NAME = "JVevermind";

    public static void preUserData() {
        PagableResponseList<User> listUser = Get500TwitterAccount.get1000TwitterUser(
                ts,
                ORIGIN_USER_SCREEN_NAME
        );
        boolean isOK = userDao.insertListUserUseBatch(listUser);
        if (isOK) {
            System.out.println("preUserData : OK");
        } else {
            System.out.println("preUserData : Fail");
        }
    }

    public static void main(String[] args) {
        Get1stData.preUserData();
    }
}
