/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import tientx.supercode.myproejectdemov3.service.TwitterService;
import tientx.supercode.myproejectdemov3.service.TwitterServiceImpl;
import twitter4j.PagableResponseList;
//import twitter4j.ResponseList;
//import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 *
 * @author zOzDarKzOz
 */
public class Get500TwitterAccount {

    public static void main(String[] args) throws TwitterException, InterruptedException {
        String userName1 = "z0zdarkz0z";
        String userName = "JVevermind";
        long idUser = 988209746;
        TwitterService ts = new TwitterServiceImpl();
//        ResponseList<Status> listStatus = ts.getTwFavorites(idUser);
//        System.out.println(listStatus.size());
//        listStatus.stream().forEach((list) -> {
//            System.out.println("ID bai dang: " + list.getId());
//            System.out.println("Noi dung bai dang: " + list.getText());
//            System.out.println("Source bai dang: " + list.getSource());
//            System.out.println("Ngay dang bai: " + list.getCreatedAt());
//            System.out.println("Lang cua bai dang: " + list.getLang());
//            System.out.println("-------------------------");
//        });thuluong-hangphan
        PagableResponseList<User> listFollower;
        listFollower = ts.getTwUserFollowerList(userName, (long) -1, 200);
        System.out.println("Turn " + 1 + " : " + listFollower.size());
        listFollower.stream().forEach((list) -> {
            System.out.println("ID follower : " + list.getId());
            System.out.println("Follower screen name : " + list.getScreenName());
            System.out.println("-------------------------");
        });

        Thread.sleep(15000);
        listFollower = ts.getTwUserFollowerList(userName, listFollower.getNextCursor(), 200);
        System.out.println("Turn " + 2 + " : " + listFollower.size());
        listFollower.stream().forEach((list) -> {
            System.out.println("ID follower : " + list.getId());
            System.out.println("Follower screen name : " + list.getScreenName());
            System.out.println("-------------------------");
        });
        Thread.sleep(15000);
        listFollower = ts.getTwUserFollowerList(userName, listFollower.getNextCursor(), 100);
        System.out.println("Turn " + 2 + " : " + listFollower.size());
        listFollower.stream().forEach((list) -> {
            System.out.println("ID follower : " + list.getId());
            System.out.println("Follower screen name : " + list.getScreenName());
            System.out.println("-------------------------");
        });
    }
}
