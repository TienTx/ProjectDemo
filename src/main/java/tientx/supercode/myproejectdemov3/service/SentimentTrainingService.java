/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.service;

/**
 *
 * @author zOzDarKzOz
 */
public interface SentimentTrainingService {
//    void train(String path);
    boolean train(String path);
    String classify(String text);
}
