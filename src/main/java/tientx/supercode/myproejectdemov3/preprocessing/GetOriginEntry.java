/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import tientx.supercode.myproejectdemov3.service.TwitterService;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 *
 * @author zOzDarKzOz
 */
public class GetOriginEntry
{

    private static final Integer COUNT = 50;

    public static ResponseList<Status> getOriginEntry(
            TwitterService ts, long originUserId
    )
    {
        try {
//            ResponseList<Status> listStatus = ts.getTwUserHomeTimeline(originUserId);
            ResponseList<Status> listStatus = ts.getTwUserTimeline(originUserId, COUNT);
//            ResponseList<Status> listStatus = ts.getTwUserHomeTimeline(originUserId, COUNT);
            if (listStatus != null) {
                System.out.println("All : " + listStatus.size());
                listStatus.stream().forEach((list) -> {
                    System.out.println("ID status : " + list.getId());
                    System.out.println("Reweet user id : " + list.getCurrentUserRetweetId());
                    System.out.println("-------------------------");
                });
            }
            return listStatus;
        } catch (TwitterException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
