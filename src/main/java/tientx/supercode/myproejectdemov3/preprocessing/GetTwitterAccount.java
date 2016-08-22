/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import tientx.supercode.myproejectdemov3.service.TwitterService;
import twitter4j.PagableResponseList;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 *
 * @author zOzDarKzOz
 */
public class GetTwitterAccount
{

    public static PagableResponseList<User> get2000Account(
            TwitterService ts, String originUserScreenName
    )
    {
        try {
            PagableResponseList<User> listFollower = ts.getTwUserFollowerList(
                    originUserScreenName,
                    (long) -1,
                    200
            );
            System.out.println("Turn " + 1 + " : " + listFollower.size());
            Thread.sleep(15000);

            long cursor = listFollower.getNextCursor();
            for (int i = 2; i <= 10; i++) {
                PagableResponseList<User> list = ts.getTwUserFollowerList(
                        originUserScreenName,
                        cursor,
                        200
                );
                System.out.println("Turn " + i + " : " + list.size());
                if (list != null && list.size() > 0) {
                    listFollower.addAll(list);
                }
                cursor = list.getNextCursor();
                if (i != 10) {
                    Thread.sleep(15000);
                }
            }

            System.out.println("All : " + listFollower.size());
            listFollower.stream().forEach((list) -> {
                System.out.println("ID follower : " + list.getId());
                System.out.println("Follower screen name : " + list.getScreenName());
                System.out.println("-------------------------");
            });
            return listFollower;
        } catch (TwitterException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PagableResponseList<User> getAllFriend(
            TwitterService ts, Long idUser
    )
    {
        try {
            int i = 1;
            PagableResponseList<User> listFriend = ts.getTwUserFriendListId(
                    idUser,
                    (long) -1,
                    200
            );
            System.out.println("Turn " + i + " : " + listFriend.size());
            Thread.sleep(15000);

            long cursor = listFriend.getNextCursor();
            PagableResponseList<User> list;
            do {
                list = ts.getTwUserFollowerList(
                        idUser,
                        cursor,
                        200
                );
                System.out.println("Turn " + ++i + " : " + list.size());
                if (list != null && list.size() > 0) {
                    listFriend.addAll(list);
                }
                cursor = list.getNextCursor();
                Thread.sleep(15000);
            } while (list != null && list.size() > 0);
            System.out.println("All : " + listFriend.size());
            return listFriend;
        } catch (TwitterException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
