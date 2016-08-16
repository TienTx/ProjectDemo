/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.service;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.LMClassifier;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import tientx.supercode.myproejectdemov3.config.Variable;

/**
 *
 * @author zOzDarKzOz
 */
public class SentimentTrainingImpl
        implements SentimentTraining
{

    private static final String[] CATEGORIES = {"NEGATIVE", "NEUTRAL", "POSITIVE"};
    private static final int NGRAM = 7;
    private LMClassifier lmc;

//    @Override
//    public void train(String path) {
//        try {
//            lmc = DynamicLMClassifier.createNGramProcess(CATEGORIES, NGRAM);
//            File trainDir = new File(path);
//            for (int i = 0; i < CATEGORIES.length; ++i) {
//                String category = CATEGORIES[i];
//                Classification classification = new Classification(category);
//                File file = new File(trainDir, CATEGORIES[i]);
//                File[] trainFiles = file.listFiles();
//                for (int j = 0; j < trainFiles.length; ++j) {
//                    File trainFile = trainFiles[j];
//                    String review = Files.readFromFile(trainFile, Variable.DEFAULT_CHARSET);
//                    Classified classified = new Classified(review, classification);
//                    ((ObjectHandler) lmc).handle(classified);
//                    System.out.println("Training on " + CATEGORIES[i] + "/" + trainFiles[j]);
//                }
//            }
//            AbstractExternalizable.compileTo((Compilable) lmc, new File(Variable.SENTIMENT_CLASSIFFIER_FILE_NAME));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    private void preClassify(String dataPath, String dataTrainingPath)
    {
        int ne = 0, nl = 0, pe = 0;
        try {
            File[] trainFiles = new File(dataPath).listFiles();
            if (trainFiles != null) {
                for (int j = 0; j < trainFiles.length; ++j) {
                    BufferedReader bufferReader = null;
                    BufferedWriter bf = null;
                    try {
                        File trainFile = trainFiles[j];
                        bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(trainFile)));
                        String s = bufferReader.readLine();
                        while (s != null) {
                            if (s.contains("\tNEGATIVE\t")) {
                                try {
                                    bf = new BufferedWriter(
                                            new PrintWriter(
                                                    new FileOutputStream(
                                                            dataTrainingPath + "/negative/" + (++ne) + ".txt",
                                                            true
                                                    )
                                            )
                                    );
                                    bf.write(s.split("\t")[1]);
                                    bf.newLine();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        if (bf != null) {
                                            bf.close();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else if (s.contains("\tNEUTRAL\t")) {
                                try {
                                    bf = new BufferedWriter(
                                            new PrintWriter(
                                                    new FileOutputStream(
                                                            dataTrainingPath + "/neutral/" + (++nl) + ".txt",
                                                            true
                                                    )
                                            )
                                    );
                                    bf.write(s.split("\t")[1]);
                                    bf.newLine();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        if (bf != null) {
                                            bf.close();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else if (s.contains("\tPOSITIVE\t")) {
                                try {
                                    bf = new BufferedWriter(
                                            new PrintWriter(
                                                    new FileOutputStream(
                                                            dataTrainingPath + "/positive/" + (++pe) + ".txt",
                                                            true
                                                    )
                                            )
                                    );
                                    bf.write(s.split("\t")[1]);
                                    bf.newLine();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        if (bf != null) {
                                            bf.close();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            s = bufferReader.readLine();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (bufferReader != null) {
                                bufferReader.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String classify(String text)
    {
        try {
            lmc = (LMClassifier) AbstractExternalizable.readObject(new File(Variable.SENTIMENT_CLASSIFFIER_FILE_NAME));
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        ConditionalClassification classification = lmc.classify(text);
        return classification.bestCategory();
    }

    @Override
    public boolean train(String path)
    {
        try {
//            preClassify(Variable.SENTIMENT_CLASSIFFIER_DATA_PATH, path);
            lmc = DynamicLMClassifier.createNGramProcess(CATEGORIES, NGRAM);
            File trainDir = new File(path);
            if (!trainDir.isDirectory()) {
                System.out.println("Is not directory!");
            } else {
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
                        ((ObjectHandler) lmc).handle(classified);
                        System.out.println("Training on " + CATEGORIES[i] + "/" + trainFiles[j]);
                    }
                    System.out.println("-------------------------------------------------------------------All: " + l);
                }
            }
            AbstractExternalizable.compileTo((Compilable) lmc, new File(Variable.SENTIMENT_CLASSIFFIER_FILE_NAME));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
