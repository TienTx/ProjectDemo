/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import java.util.ArrayList;
import tientx.supercode.myproejectdemov3.config.Variable;
import tientx.supercode.myproejectdemov3.dao.OriginEntryDao;
import tientx.supercode.myproejectdemov3.dao.OriginEntryDaoImpl;
import tientx.supercode.myproejectdemov3.model.OriginEntry;
import tientx.supercode.myproejectdemov3.service.SentimentTrainingServiceImpl;
import tientx.supercode.myproejectdemov3.service.TwitterService;
import tientx.supercode.myproejectdemov3.service.TwitterServiceImpl;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import tientx.supercode.myproejectdemov3.service.SentimentTrainingService;
import tientx.supercode.myproejectdemov3.service.UserService;
import tientx.supercode.myproejectdemov3.service.UserServiceImpl;

/**
 *
 * @author zOzDarKzOz
 */
public class SentimentClassify
{

    private static TwitterService ts = new TwitterServiceImpl();
    private static SentimentTrainingService st = new SentimentTrainingServiceImpl();
    private static OriginEntryDao oed = new OriginEntryDaoImpl();
    private static UserService us = new UserServiceImpl();
//    private static final String ORIGIN_USER_SCREEN_NAME = "tim_cook";
    private static final String ORIGIN_USER_SCREEN_NAME = "BillGates";

    private static void sentimentTrain(String path)
    {
        if (st.train(path)) {
            System.out.println("Trained");
        } else {
            System.out.println("Trainng fail!");
        }
    }

    private static void sentimentClassifyTest()
    {
        try {
            ResponseList<Status> listContent = ts.getTwUserHomeTimeline(ORIGIN_USER_SCREEN_NAME);
            for (Status content : listContent) {
                String text = content.getText();
                System.out.println(text);
                String sent = st.classify(text);
                System.out.println("--------Sentiment: " + sent);
            }
        } catch (TwitterException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private static void sentimentClassify()
    {
        try {
            ArrayList<OriginEntry> listContent = oed.getAll();
            for (OriginEntry content : listContent) {
                String text = content.getsContent();
//                System.out.println(text);
                String sent = st.classify(text);
                System.out.println("--------Sentiment: " + sent);
                content.setsSentiment(sent);
            }
            boolean isOK = us.editListOriginEntryUseBatch(listContent);
            if (isOK) {
                System.out.println("Updated!");
            } else {
                System.out.println("Update Fail");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
//        SentimentClassify.sentimentTrain(Variable.SENTIMENT_CLASSIFFIER_DATATRAIN_PATH);
//        SentimentClassify.sentimentClassifyTest();
        SentimentClassify.sentimentClassify();
    }
}
