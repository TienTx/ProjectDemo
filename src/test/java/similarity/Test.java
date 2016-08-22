/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;

import tientx.supercode.myproejectdemov3.dao.PostDao;
import tientx.supercode.myproejectdemov3.dao.PostDaoImpl;
import tientx.supercode.myproejectdemov3.service.SimilarityServiceImpl;

/**
 *
 * @author zOzDarKzOz
 */
public class Test
{

    public static void main(String[] args)
    {
        Long idUser1 = 1002171944l;
        Long idUser2 = 1005840727l;
        PostDao postDao = new PostDaoImpl();
        System.out.println(
                new SimilarityServiceImpl().postingBehaviourSimilarityOfUser(
                        postDao.getByUserId(idUser1),
                        postDao.getByUserId(idUser2)
                )
        );
    }
}
