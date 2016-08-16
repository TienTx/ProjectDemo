/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.service;

import java.util.Map;
import tientx.supercode.myproejectdemov3.config.TwitterConfig;
import tientx.supercode.myproejectdemov3.connection.TwitterConnection;
import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 *
 * @author zOzDarKzOz
 */
public class TwitterServiceImpl
        implements TwitterService
{

    private Twitter twitter;

    public TwitterServiceImpl()
    {
        twitter = TwitterConnection.getInstanceTwiiter();
    }

    private void overError88(String command) throws TwitterException, InterruptedException
    {
        Map<String, RateLimitStatus> temp = twitter.getRateLimitStatus();
        RateLimitStatus temp2 = temp.get(command);
        System.out.println("------------------------------------------------Remaining:" + temp2.getRemaining());
        if (temp2.getRemaining() == 0) {
            Thread.sleep((temp2.getSecondsUntilReset() + 5) * 1000);
            return;
        }
        System.out.println("------------------------------------------------SecondsUntilReset:" + temp2.getSecondsUntilReset());
        int secondstosleep = 1 + temp2.getSecondsUntilReset() / temp2.getRemaining();
        System.out.println("------------------------------------------------secondstosleep:" + secondstosleep);
        Thread.sleep(secondstosleep * 1000);
    }

    @Override
    public ResponseList<Status> getTwFavorites(String screenName) throws TwitterException, InterruptedException
    {
        Paging p = new Paging(1, 500);
        overError88(TwitterConfig.USER_FAVORITES_LIST);
        return twitter.getFavorites(screenName, p);
//        System.out.println(listStatus.size());
//        listStatus.stream().forEach((list) -> {
//            System.out.println("@" + list.getText());
//        });
//        return listStatus;
    }

    @Override
    public ResponseList<Status> getTwFavorites(Long idUser) throws TwitterException, InterruptedException
    {
        Paging p = new Paging(1, 500);
        overError88(TwitterConfig.USER_FAVORITES_LIST);
        return twitter.getFavorites(idUser, p);
//        System.out.println(listStatus.size());
//        listStatus.stream().forEach((list) -> {
//            System.out.println("@" + list.getText());
//        });
//        return listStatus;
    }

    @Override
    public ResponseList<Status> getTwUserTimeline(String screenName) throws TwitterException, InterruptedException
    {
        overError88(TwitterConfig.USER_TIMELINE);
        return twitter.getUserTimeline(screenName);
//        System.out.println(listStatus.size());
//        listStatus.stream().forEach((list) -> {
//            System.out.println("@" + list.getText());
//        });
//        return listStatus;
    }

    @Override
    public ResponseList<Status> getTwUserTimeline(Long idUser) throws TwitterException, InterruptedException
    {
        overError88(TwitterConfig.USER_TIMELINE);
        return twitter.getUserTimeline(idUser);
//        System.out.println(listStatus.size());
//        listStatus.stream().forEach((list) -> {
//            System.out.println("@" + list.getText());
//        });
//        return listStatus;
    }

    @Override
    public ResponseList<Status> getTwUserHomeTimeline(String screenName) throws TwitterException, InterruptedException
    {
        overError88(TwitterConfig.USER_HOME_TIMELINE);
        return twitter.getUserTimeline(screenName);
//        System.out.println(listStatus.size());
//        listStatus.stream().forEach((list) -> {
//            System.out.println("@" + list.getText());
//        });
//        return listStatus;
    }

    @Override
    public ResponseList<Status> getTwUserHomeTimeline(Long idUser) throws TwitterException, InterruptedException
    {
        overError88(TwitterConfig.USER_HOME_TIMELINE);
        return twitter.getUserTimeline(idUser);
//        System.out.println(listStatus.size());
//        listStatus.stream().forEach((list) -> {
//            System.out.println("@" + list.getText());
//        });
//        return listStatus;
    }

    @Override
    public PagableResponseList<User> getTwUserFriendList(String screenName)
            throws TwitterException, InterruptedException
    {
        overError88(TwitterConfig.USER_FRIEND_LIST);
        return twitter.getFriendsList(screenName, 20);
//        System.out.println(listFriends.size());
//        listFriends.stream().forEach((list) -> {
//            System.out.println("@" + list.getId() + " : " + list.getScreenName());
//        });
//        return listFriends;
    }

    @Override
    public PagableResponseList<User> getTwUserFriendList(Long idUser) throws TwitterException, InterruptedException
    {
        overError88(TwitterConfig.USER_FRIEND_LIST);
        return twitter.getFriendsList(idUser, 20);
//        System.out.println(listFriends.size());
//        listFriends.stream().forEach((list) -> {
//            System.out.println("@" + list.getId() + " : " + list.getScreenName());
//        });
//        return listFriends;
    }

    @Override
    public PagableResponseList<User> getTwUserFollowerList(String screenName,
                                                           Long cursor,
                                                           Integer count) throws TwitterException, InterruptedException
    {
        overError88(TwitterConfig.USER_FOLLOWER_LIST);
        return twitter.getFollowersList(screenName, cursor, count);
    }

    @Override
    public PagableResponseList<User> getTwUserFollowerList(Long idUser,
                                                           Long cursor,
                                                           Integer count) throws TwitterException, InterruptedException
    {
        overError88(TwitterConfig.USER_FOLLOWER_LIST);
        return twitter.getFollowersList(idUser, cursor, count);
//        System.out.println(listFollower.size());
//        listFollower.stream().forEach((list) -> {
//            System.out.println("@" + list.getId() + " : " + list.getScreenName());
//        });
    }

    @Override
    public ResponseList<Status> getTwUserTimeline(Long idUser, Integer count)
            throws TwitterException, InterruptedException
    {
//        Paging pg = new Paging();
//        pg.setCount(count);
        Paging pg = new Paging(1, count);
        overError88(TwitterConfig.USER_TIMELINE);
        return twitter.getUserTimeline(idUser, pg);
    }

    @Override
    public ResponseList<Status> getTwUserHomeTimeline(Long idUser, Integer count)
            throws TwitterException, InterruptedException
    {
        Paging pg = new Paging();
        pg.setCount(count);
        overError88(TwitterConfig.USER_HOME_TIMELINE);
        return twitter.getUserTimeline(idUser, pg);
    }

}
