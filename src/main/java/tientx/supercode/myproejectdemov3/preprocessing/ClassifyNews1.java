/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.ConfusionMatrix;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.JointClassification;
import com.aliasi.classify.JointClassifier;
import com.aliasi.classify.JointClassifierEvaluator;

import com.aliasi.lm.NGramProcessLM;

import com.aliasi.util.AbstractExternalizable;

import java.io.File;
import java.io.IOException;

import com.aliasi.util.Files;
import java.util.ArrayList;
import tientx.supercode.myproejectdemov3.config.Variable;
import tientx.supercode.myproejectdemov3.service.TwitterService;
import tientx.supercode.myproejectdemov3.service.TwitterServiceImpl;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

public class ClassifyNews1 {

    private static final File TRAINING_DIR
            //            = new File("C:/ProjectDemo/src/main/java/tientx/supercode/myproejectdemov3/data/fourNewsGroups/4news-test");
            = new File("C:/data_training/fourNewsGroups/4news-test");

    private static final String[] CATEGORIES
            = {
                "alt.atheism",
                "rec.autos",
                "sci.crypt",
                "sci.med",
                "sci.space",
                "talk.politics.guns",
                "talk.politics.mideast",
                "talk.politics.misc",
                "sci.electronics",
                "rec.sport.hockey",
                "rec.sport.baseball",
                "rec.motorcycles",
                "comp.windows.x",
                "comp.sys.mac.hardware",
                "comp.sys.ibm.pc.hardware",
                "misc.forsale",
                "comp.graphics",
                "soc.religion.christian",
                "comp.os.ms-windows.misc",
                "talk.religion.misc"
            };

    private static final String[] CATEGORIES2
            = {
                "alt.atheism",
                "talk.religion.misc"
            };

    private static TwitterService ts = new TwitterServiceImpl();
//    private static UserDao userDao = new UserDaoImpl();
//    private static final String ORIGIN_USER_SCREEN_NAME = "BillGates";
    private static final String ORIGIN_USER_SCREEN_NAME = "tim_cook";

    private static final int NGRAM_SIZE = 6;

    public static void main(String[] args)
            throws ClassNotFoundException, IOException, TwitterException, InterruptedException {

        DynamicLMClassifier<NGramProcessLM> classifier
                = DynamicLMClassifier.createNGramProcess(CATEGORIES, NGRAM_SIZE);

        for (int i = 0; i < CATEGORIES.length; ++i) {
            try {
                File classDir = new File(TRAINING_DIR, CATEGORIES[i]);

                String[] trainingFiles = classDir.list();
                for (int j = 0; j < trainingFiles.length; ++j) {
                    File file = new File(classDir, trainingFiles[j]);
                    String text = Files.readFromFile(file, Variable.DEFAULT_CHARSET);
                    Classification classification = new Classification(CATEGORIES[i]);
                    Classified<CharSequence> classified = new Classified<>(text, classification);
                    classifier.handle(classified);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //compiling
        System.out.println("Compiling");
        @SuppressWarnings("unchecked") // we created object so know it's safe
        JointClassifier<CharSequence> compiledClassifier
                = (JointClassifier<CharSequence>) AbstractExternalizable.compile(classifier);

        boolean storeCategories = true;
        JointClassifierEvaluator<CharSequence> evaluator = new JointClassifierEvaluator<>(
                compiledClassifier,
                CATEGORIES,
                storeCategories
        );
//        ArrayList<String> listContent = new ArrayList<>();
//        File classDir = new File(TESTING_DIR, "all");
//        String[] testingFiles = classDir.list();
//        for (int j = 0; j < testingFiles.length; ++j) {
//            String text = Files.readFromFile(new File(classDir, testingFiles[j]), "ISO-8859-1");
//            listContent.add(text);
//        }
        ResponseList<Status> listContent = ts.getTwUserHomeTimeline(ORIGIN_USER_SCREEN_NAME);
        Classification classification = new Classification(CATEGORIES2[0]);
//        for (int i = 0; i < CATEGORIES2.length; ++i) {
//            System.out.print("Testing on " + CATEGORIES2[i] + " ");
        for (Status content : listContent) {
            String text = content.getText();
            System.out.println(text);
            Classified<CharSequence> classified = new Classified<>(text, classification);
            evaluator.handle(classified);
            JointClassification jc = compiledClassifier.classify(text);
            String bestCategory = jc.bestCategory();
            System.out.println("Got best category of: " + bestCategory);
            System.out.println("---------------");
        }
//        }

//        ConfusionMatrix confMatrix = evaluator.confusionMatrix();
//        System.out.println("Total Accuracy: " + confMatrix.totalAccuracy());
//        System.out.println("\nFULL EVAL");
//        System.out.println(evaluator);
    }
}
