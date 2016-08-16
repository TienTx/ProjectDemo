/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tientx.supercode.myproejectdemov3.dao.UserDao;
import tientx.supercode.myproejectdemov3.dao.UserDaoImpl;
import tientx.supercode.myproejectdemov3.service.TwitterService;
import tientx.supercode.myproejectdemov3.service.TwitterServiceImpl;
import tientx.supercode.myproejectdemov3.service.UserService;
import tientx.supercode.myproejectdemov3.service.UserServiceImpl;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.User;

/**
 *
 * @author zOzDarKzOz
 */
public class Get1stData
{

    private static TwitterService ts = new TwitterServiceImpl();
    private static UserService us = new UserServiceImpl();
//    private static final String ORIGIN_USER_SCREEN_NAME = "JVevermind";
    private static final String ORIGIN_USER_SCREEN_NAME = "BillGates";
//    private static final String ORIGIN_USER_SCREEN_NAME = "tim_cook";

    public static void preUserData()
    {
//        PagableResponseList<User> listUser = Get1000TwitterAccount.get1000TwitterUser(
        PagableResponseList<User> listUser = Get2000TwitterAccount.get(
                ts,
                ORIGIN_USER_SCREEN_NAME
        );
        boolean isOK = us.addListUserUseBatch(listUser);
        if (isOK) {
            System.out.println("preUserData : OK");
        } else {
            System.out.println("preUserData : Fail");
        }
    }

    public static void preOriginEntryData()
    {
        try {
            ArrayList<tientx.supercode.myproejectdemov3.model.User> listUser
                                                                    = us.getAll();
//            int l = listUser.size();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2016-7-16");
            for (int i = 2; i < 3; i++) {
                ResponseList<Status> listStatus = GetOriginEntry.getOriginEntry(
                        ts,
                        Long.parseLong(listUser.get(i).getIdUser())
                );
                if (listStatus != null) {
                    int ll = listStatus.size();
                    for (int j = 0; j < ll; j++) {
                        Date createDate = listStatus.get(j).getCreatedAt();
                        if (createDate.after(date)) {
                            System.out.println(createDate.toString());
                        } else {
                            listStatus.remove(j);
                            ll--;
                            j--;
                        }
                    }
                    System.out.println("Accep: " + listStatus.size());
                    if (ll > 0) {
                        boolean isOK = us.addListOriginEntryUseBatch(listStatus,
                                                                     Long.parseLong(listUser.get(i).getIdUser()));
                        if (isOK) {
                            System.out.println("preOriginEntryData " + i + " : OK");
                        } else {
                            System.out.println("preOriginEntryData " + i + " : Fail");
                        }
                    }
                } else {
                    System.out.println("Authentication credentials! Can not get private data!");
                }
            }
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
//        Get1stData.preUserData();
        Get1stData.preOriginEntryData();
    }
}
