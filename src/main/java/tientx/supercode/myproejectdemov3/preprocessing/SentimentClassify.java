/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import java.util.logging.Level;
import java.util.logging.Logger;
import tientx.supercode.myproejectdemov3.config.Variable;
import tientx.supercode.myproejectdemov3.service.SentimentTraining;
import tientx.supercode.myproejectdemov3.service.SentimentTrainingImpl;
import tientx.supercode.myproejectdemov3.service.TwitterService;
import tientx.supercode.myproejectdemov3.service.TwitterServiceImpl;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 *
 * @author zOzDarKzOz
 */
public class SentimentClassify {

    private static TwitterService ts = new TwitterServiceImpl();
    private static SentimentTraining st = new SentimentTrainingImpl();
//    private static final String ORIGIN_USER_SCREEN_NAME = "tim_cook";
    private static final String ORIGIN_USER_SCREEN_NAME = "BillGates";

    private static void sentimentTrain(String path) {
        if (st.train(path)) {
            System.out.println("Trained");
        } else {
            System.out.println("Trainng fail!");
        }
    }

    private static void sentimentClassifyTest() {
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

    public static void main(String[] args) {
//        SentimentClassify.sentimentTrain(Variable.SENTIMENT_CLASSIFFIER_DATATRAIN_PATH);
        SentimentClassify.sentimentClassifyTest();
    }
}
