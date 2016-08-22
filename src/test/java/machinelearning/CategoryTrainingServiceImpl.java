/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.LMClassifier;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author zOzDarKzOz
 */
public class CategoryTrainingServiceImpl
        implements CategoryTrainingService
{

    private static final String[] CATEGORIES = {
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
    private static final int NGRAM = 7;
    private static LMClassifier lmcC;
    private static LMClassifier lmcT;

    @Override
    public String classify(String text)
    {
        try {
            if (lmcC == null) {
                lmcC = (LMClassifier) AbstractExternalizable.readObject(new File(Variable.CATEGORY_CLASSIFFIER_FILE_NAME));
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        ConditionalClassification classification = lmcC.classify(text);
        return classification.bestCategory();
    }

    @Override
    public boolean train(String path)
    {
        try {
            if (lmcT == null) {
                lmcT = DynamicLMClassifier.createNGramProcess(CATEGORIES, NGRAM);
            }
            File trainDir = new File(path);
            if (!trainDir.isDirectory()) {
                System.out.println("Is not directory!");
                return false;
            }
            for (int i = 0; i < CATEGORIES.length; ++i) {
                String category = CATEGORIES[i];
                Classification classification = new Classification(category);
                File file = new File(trainDir, CATEGORIES[i]);
                File[] trainFiles = file.listFiles();
                int l = trainFiles.length;
                for (int j = 0; j < l; ++j) {
                    File trainFile = trainFiles[j];
                    String review = Files.readFromFile(trainFile, Variable.DEFAULT_CHARSET);
                    Classified classified = new Classified(review, classification);
                    ((ObjectHandler) lmcT).handle(classified);
                    System.out.println("Training on " + CATEGORIES[i] + "/" + trainFiles[j]);
                }
                System.out.println("----------------------------------------------------------------------------------------------------------All: " + l);
            }
            AbstractExternalizable.compileTo((Compilable) lmcT, new File(Variable.CATEGORY_CLASSIFFIER_FILE_NAME));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
