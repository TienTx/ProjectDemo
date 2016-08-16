/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.connection;

import tientx.supercode.myproejectdemov3.config.TwitterConfig;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author zOzDarKzOz
 */
public class TwitterConnection {

    public static TwitterConnection instance = null;
    private Twitter twitter;

    private TwitterConnection() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TwitterConfig.API_KEY)
                .setOAuthConsumerSecret(TwitterConfig.API_SECRET)
                .setOAuthAccessToken(TwitterConfig.ACCES_TOKEN)
//                .setOAuthAccessTokenSecret(TwitterConfig.ACCES_TOKEN_SECRET).setHttpConnectionTimeout(100000)
                                .setOAuthAccessTokenSecret(TwitterConfig.ACCES_TOKEN_SECRET).setHttpConnectionTimeout(100000);
//                .setHttpProxyHost(TwitterConfig.PROXY_HOST)
//                .setHttpProxyPort(TwitterConfig.PROXY_PORT);
        TwitterFactory factory = new TwitterFactory(cb.build());
        twitter = factory.getInstance();
    }

    public static Twitter getInstanceTwiiter() {
        if (instance == null) {
            instance = new TwitterConnection();
        }
        return instance.getTwitter();
    }

    private Twitter getTwitter() {
        return twitter;
    }
}
