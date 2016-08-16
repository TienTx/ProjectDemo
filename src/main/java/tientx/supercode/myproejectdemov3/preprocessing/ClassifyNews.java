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

public class ClassifyNews {

    private static final File TRAINING_DIR
            = new File("C:/ProjectDemo/src/main/java/tientx/supercode/myproejectdemov3/data/fourNewsGroups/4news-test");

    private static final File TESTING_DIR
            = new File("C:/ProjectDemo/src/main/java/tientx/supercode/myproejectdemov3/data/fourNewsGroups/4news-test");

    private static final String[] CATEGORIES
            = {
                "alt.atheism",
//                "rec.autos",
//                "sci.crypt",
//                "sci.med",
//                "sci.space",
//                "talk.politics.guns",
//                "talk.politics.mideast",
//                "talk.politics.misc",
//                "sci.electronics",
//                "rec.sport.hockey",
//                "rec.sport.baseball",
//                "rec.motorcycles",
//                "comp.windows.x",
//                "comp.sys.mac.hardware",
//                "comp.sys.ibm.pc.hardware",
//                "misc.forsale",
//                "comp.graphics",
//                "soc.religion.christian",
//                "comp.os.ms-windows.misc",
                "talk.religion.misc"
            };

    private static final int NGRAM_SIZE = 6;

    public static void main(String[] args)
            throws ClassNotFoundException, IOException {

        DynamicLMClassifier<NGramProcessLM> classifier
                = DynamicLMClassifier.createNGramProcess(CATEGORIES, NGRAM_SIZE);

        for (int i = 0; i < CATEGORIES.length; ++i) {
            File classDir = new File(TRAINING_DIR, CATEGORIES[i]);
            if (!classDir.isDirectory()) {
                String msg = "Could not find training directory="
                        + classDir
                        + "\nHave you unpacked 4 newsgroups?";
                System.out.println(msg); // in case exception gets lost in shell
                throw new IllegalArgumentException(msg);
            }

            String[] trainingFiles = classDir.list();
            for (int j = 0; j < trainingFiles.length; ++j) {
                File file = new File(classDir, trainingFiles[j]);
                String text = Files.readFromFile(file, "ISO-8859-1");
                System.out.println("Training on " + CATEGORIES[i] + "/" + trainingFiles[j]);
                Classification classification
                        = new Classification(CATEGORIES[i]);
                Classified<CharSequence> classified
                        = new Classified<>(text, classification);
                classifier.handle(classified);
            }
        }
        //compiling
        System.out.println("Compiling");
        @SuppressWarnings("unchecked") // we created object so know it's safe
        JointClassifier<CharSequence> compiledClassifier
                = (JointClassifier<CharSequence>) AbstractExternalizable.compile(classifier);

        boolean storeCategories = true;
        JointClassifierEvaluator<CharSequence> evaluator
                = new JointClassifierEvaluator<>(compiledClassifier,
                        CATEGORIES,
                        storeCategories);
        for (int i = 0; i < CATEGORIES.length; ++i) {
            File classDir = new File(TESTING_DIR, CATEGORIES[i]);
            String[] testingFiles = classDir.list();
            for (int j = 0; j < testingFiles.length; ++j) {
                String text
                        = Files
                        .readFromFile(new File(classDir, testingFiles[j]), "ISO-8859-1");
                System.out.print("Testing on " + CATEGORIES[i] + "/" + testingFiles[j] + " ");
                Classification classification
                        = new Classification(CATEGORIES[i]);
                Classified<CharSequence> classified
                        = new Classified<>(text, classification);
                evaluator.handle(classified);
                JointClassification jc
                        = compiledClassifier.classify(text);
                String bestCategory = jc.bestCategory();
                String details = jc.toString();
                System.out.println("Got best category of: " + bestCategory);
                System.out.println(details);
                System.out.println("---------------");
            }
        }
        ConfusionMatrix confMatrix = evaluator.confusionMatrix();
        System.out.println("Total Accuracy: " + confMatrix.totalAccuracy());

        System.out.println("\nFULL EVAL");
        System.out.println(evaluator);
    }
}
