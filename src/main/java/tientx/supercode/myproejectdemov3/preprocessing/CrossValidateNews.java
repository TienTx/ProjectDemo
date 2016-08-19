/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.preprocessing;

import com.aliasi.corpus.XValidatingObjectCorpus;

import com.aliasi.classify.Classified;
import com.aliasi.classify.JointClassifierEvaluator;
import com.aliasi.classify.JointClassifier;
import com.aliasi.classify.Classification;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Files;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CrossValidateNews {

    private static final File TRAINING_DIR
            = new File("C:/data_training/fourNewsGroups/4news-train");

    private static final File TESTING_DIR
            = new File("C:/data_training/fourNewsGroups/4news-test");

    private static final String[] CATEGORIES
            = {
                "alt.atheism",
                "rec.autos",
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

    private static final int NUM_FOLDS = 10;

    public static void main(String[] args)
            throws ClassNotFoundException, IOException {

        XValidatingObjectCorpus<Classified<CharSequence>> corpus
                = new XValidatingObjectCorpus<>(NUM_FOLDS);

        System.out.println("Reading data.");
        // read data for train and test both
        for (String category : CATEGORIES) {
            Classification c = new Classification(category);

            File trainCatDir = new File(TRAINING_DIR, category);
            for (File trainingFile : trainCatDir.listFiles()) {
                String text = Files.readFromFile(trainingFile, "ISO-8859-1");
                Classified<CharSequence> classified
                        = new Classified<>(text, c);
                corpus.handle(classified);
            }

            File testCatDir = new File(TESTING_DIR, category);
            for (File testFile : testCatDir.listFiles()) {
                String text = Files.readFromFile(testFile, "ISO-8859-1");
                Classified<CharSequence> classified
                        = new Classified<>(text, c);
                corpus.handle(classified);
            }
        }

        System.out.println("Num instances=" + corpus.size() + ".");
        System.out.println("Permuting corpus.");
        long seed = 42L;
        corpus.permuteCorpus(new Random(seed));

        System.out.printf("%5s  %10s\n", "FOLD", "ACCU");
        for (int fold = 0; fold < NUM_FOLDS; ++fold) {
            corpus.setFold(fold);

            DynamicLMClassifier<NGramProcessLM> classifier
                    = DynamicLMClassifier.createNGramProcess(CATEGORIES, NGRAM_SIZE);
            corpus.visitTrain(classifier);
            @SuppressWarnings("unchecked") // know type is ok by compilation
            JointClassifier<CharSequence> compiledClassifier
                    = (JointClassifier<CharSequence>) AbstractExternalizable.compile(classifier);

            boolean storeInputs = false;
            JointClassifierEvaluator<CharSequence> evaluator
                    = new JointClassifierEvaluator<>(compiledClassifier,
                            CATEGORIES,
                            storeInputs);
            corpus.visitTest(evaluator);
            System.out.printf("%5d  %4.2f +/- %4.2f\n", fold,
                    evaluator.confusionMatrix().totalAccuracy(),
                    evaluator.confusionMatrix().confidence95());
        }

    }
}
