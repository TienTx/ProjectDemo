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
import tientx.supercode.myproejectdemov3.service.CategoryTrainingService;
import tientx.supercode.myproejectdemov3.service.CategoryTrainingServiceImpl;
import tientx.supercode.myproejectdemov3.service.UserService;
import tientx.supercode.myproejectdemov3.service.UserServiceImpl;

/**
 *
 * @author zOzDarKzOz
 */
public class CategoryClassify
{

    private static CategoryTrainingService st = new CategoryTrainingServiceImpl();
    private static OriginEntryDao oed = new OriginEntryDaoImpl();
    private static UserService us = new UserServiceImpl();

    private static void categoryTrain(String path)
    {
        if (st.train(path)) {
            System.out.println("Trained");
        } else {
            System.out.println("Trainng fail!");
        }
    }

    private static void categoryClassify()
    {
        try {
            ArrayList<OriginEntry> listContent = oed.getAll();
            int i = 1;
            for (OriginEntry content : listContent) {
                String text = content.getsContent();
//                System.out.println(text);
                String cat = st.classify(text);
                System.out.println(i + "--------Category: " + cat);
                content.setsCategory(cat);
                i++;
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
//        CategoryClassify.categoryTrain(Variable.CATEGORY_CLASSIFFIER_DATATRAIN_PATH);
        CategoryClassify.categoryClassify();
    }
}
