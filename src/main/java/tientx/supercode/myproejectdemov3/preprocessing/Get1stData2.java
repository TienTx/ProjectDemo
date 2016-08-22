/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import tientx.supercode.myproejectdemov3.config.Variable;
import tientx.supercode.myproejectdemov3.service.TwitterService;
import tientx.supercode.myproejectdemov3.service.TwitterServiceImpl;
import tientx.supercode.myproejectdemov3.service.UserService;
import tientx.supercode.myproejectdemov3.service.UserServiceImpl;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 *
 * @author zOzDarKzOz
 */
public class Get1stData2
{

    private static final TwitterService TWITTER_SERVICE = new TwitterServiceImpl();
    private static final UserService USER_SERVICE = new UserServiceImpl();

//    private static final String ORIGIN_USER_SCREEN_NAME = "JVevermind";
    private static final String ORIGIN_USER_SCREEN_NAME = "BillGates";
//    private static final String ORIGIN_USER_SCREEN_NAME = "tim_cook";
    private static final String START_DATE = "2016-7-18";
    private static final String END_DATE = "2016-8-18";
    private static final Integer COUNT = 50;
    private static final Integer COUNT_HOME_TIMELINE = 300;

    private static final String TAG = "--Get1stData_";

    public static void preUserData()
    {
//        PagableResponseList<User> listUser = Get1000TwitterAccount.get1000TwitterUser(
        PagableResponseList<User> listUser = GetTwitterAccount.get2000Account(
                TWITTER_SERVICE,
                ORIGIN_USER_SCREEN_NAME
        );
        boolean isOK = USER_SERVICE.addListUserUseBatch(listUser);
        if (isOK) {
            System.out.println(TAG + "preUserData : OK");
        } else {
            System.out.println(TAG + "preUserData : Fail");
        }
    }

    public static void prePostingData(Date sdate, Date edate)
    {
        try {
            ArrayList<tientx.supercode.myproejectdemov3.model.User> listUser
                                                                    = USER_SERVICE.getAll();
            int l = listUser.size();
            for (int i = 0; i < l; i++) {
                try {
                    ResponseList<Status> listStatus = TWITTER_SERVICE.getTwUserTimeline(
                            Long.parseLong(listUser.get(i).getIdUser()), COUNT
                    );
                    int ll;
                    if (listStatus != null && (ll = listStatus.size()) > 0) {
                        System.out.println(TAG + "All: " + ll);
                        for (int j = 0; j < ll; j++) {
                            Date createDate = listStatus.get(j).getCreatedAt();
                            if (createDate.after(sdate) && createDate.before(edate)) {
                                System.out.println(createDate.toString());
                                System.out.println(listStatus.get(j).getText());
                            } else {
                                listStatus.remove(j);
                                ll--;
                                j--;
                            }
                        }
                        System.out.println(TAG + "Accept: " + ll);
                        if (ll > 0) {
                            boolean isOK = USER_SERVICE.addListPostEntryUseBatch(
                                    listStatus,
                                    Long.parseLong(listUser.get(i).getIdUser())
                            );
                            if (isOK) {
                                System.out.println(TAG + "prePostingData " + i + " : OK");
                            } else {
                                System.out.println(TAG + "prePostingData " + i + " : Fail");
                            }
                        }
                    } else {
                        System.out.println(TAG + "Authentication credentials! Can not get private data!");
                    }
                } catch (NumberFormatException | TwitterException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void preLikeData(Date sdate, Date edate)
    {
        try {
            ArrayList<tientx.supercode.myproejectdemov3.model.User> listUser
                                                                    = USER_SERVICE.getAll();
            int l = listUser.size();
            for (int i = 0; i < l; i++) {
                try {
                    ResponseList<Status> listStatus = TWITTER_SERVICE.getTwFavorites(
                            Long.parseLong(listUser.get(i).getIdUser()), COUNT
                    );
                    int ll;
                    if (listStatus != null && (ll = listStatus.size()) > 0) {
                        for (int j = 0; j < ll; j++) {
                            Date createDate = listStatus.get(j).getCreatedAt();
                            if (createDate.after(sdate) && createDate.before(edate)) {
                                System.out.println(createDate.toString());
                                System.out.println(listStatus.get(j).getText());
                            } else {
                                listStatus.remove(j);
                                ll--;
                                j--;
                            }
                        }
                        System.out.println(TAG + "Accept: " + listStatus.size());
                        if (ll > 0) {
                            boolean isOK = USER_SERVICE.addListLikeEntryUseBatch(
                                    listStatus, Long.parseLong(listUser.get(i).getIdUser())
                            );
                            if (isOK) {
                                System.out.println(TAG + "preLikeData " + i + " : OK");
                            } else {
                                System.out.println(TAG + "preLikeData " + i + " : Fail");
                            }
                        }
                    } else {
                        System.out.println(TAG + "Authentication credentials! Can not get private data!");
                    }
                } catch (NumberFormatException | TwitterException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void preCommentLikeData(Date sdate, Date edate)
    {
        try {
            ArrayList<tientx.supercode.myproejectdemov3.model.User> listUser
                                                                    = USER_SERVICE.getAll();
            int l = listUser.size();
            for (int i = 0; i < l; i++) {
                List<Status> statuses = new ArrayList<>();
                //get friend list
                PagableResponseList<User> friendList = GetTwitterAccount.getAllFriend(
                        TWITTER_SERVICE, Long.parseLong(listUser.get(i).getIdUser())
                );
                int ll;
                if (friendList != null && (ll = friendList.size()) > 0) {
                    System.out.println(TAG + "Friend: " + ll);
                    for (int j = 0; j < ll; j++) {
                        try {
                            ResponseList<Status> listStatus = TWITTER_SERVICE.getTwUserTimeline(
                                    friendList.get(j).getId(), COUNT
                            );
                            int lll;
                            if (listStatus != null && (lll = listStatus.size()) > 0) {
                                System.out.println(TAG + "All timeline: " + lll);
                                for (int k = 0; k < lll; k++) {
                                    Date createDate = listStatus.get(k).getCreatedAt();
                                    if (createDate.after(sdate)
                                        && createDate.before(edate)
                                        && listStatus.get(k).getRetweetCount() > 0) {
                                        IDs iDs;
                                        long cursor = -1;
                                        do {
                                            iDs = TWITTER_SERVICE.getListRetweeterId(
                                                    listStatus.get(k).getId(),
                                                    COUNT,
                                                    cursor
                                            );
                                            if (iDs != null
                                                && iDs.getIDs().length > 0
                                                && (Arrays.toString(iDs.getIDs()).contains(friendList.get(j).getId() + ",")
                                                    || Arrays.toString(iDs.getIDs()).contains(", " + friendList.get(j).getId()))) {
                                                System.out.println(listStatus.get(k).getText());
                                                System.out.println("--------------");
                                                statuses.add(listStatus.get(k));
                                                break;
                                            }
                                            cursor = iDs.getNextCursor();
                                            Thread.sleep(15000);
                                        } while (iDs != null && iDs.getIDs().length > 0);
                                    }
                                }
                            }
                        } catch (TwitterException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(TAG + "All comment/like " + (i + 1) + ": " + statuses.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
            throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(Variable.DEFAULT_DATE_FORMAT);
        Date sdate = sdf.parse(START_DATE);
        Date edate = sdf.parse(END_DATE);
//        Get1stData.preUserData();
//        Get1stData.prePostingData(sdate, edate);
//        Get1stData.preLikeData(sdate, edate);
        Get1stData2.preCommentLikeData(sdate, edate);
    }
}
