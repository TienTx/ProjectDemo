/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;

import tientx.supercode.myproejectdemov3.dao.LikeDao;
import tientx.supercode.myproejectdemov3.dao.LikeDaoImpl;
import tientx.supercode.myproejectdemov3.dao.PostDao;
import tientx.supercode.myproejectdemov3.dao.PostDaoImpl;
import tientx.supercode.myproejectdemov3.model.User;
import tientx.supercode.myproejectdemov3.service.SimilarityService;
import tientx.supercode.myproejectdemov3.service.SimilarityServiceImpl;

/**
 *
 * @author zOzDarKzOz
 */
public class Test
{

    private static PostDao postDao = new PostDaoImpl();
    private static LikeDao likeDao = new LikeDaoImpl();
    private static SimilarityService similarityService = new SimilarityServiceImpl();

    public static void main(String[] args)
    {
        Long idUser1 = 4651610848l;
        Long idUser2 = 766005009834242048l;

        User user1 = new User(
                "4651610848",
                "User1",
                postDao.getByUserId(idUser1),
                likeDao.getByUserId(idUser1),
                null,
                null
        );
        User user2 = new User(
                "766005009834242048",
                "User2", postDao.getByUserId(idUser2),
                likeDao.getByUserId(idUser2),
                null,
                null
        );

        System.out.println("postingBehaviourSimilarityOfUser: "
                           + similarityService.postingBehaviourSimilarityOfUser(
                        user1.getPost(),
                        user2.getPost()
                )
        );
        System.out.println("likeBasedBehaviourSimilarityOfUser: "
                           + similarityService.likeBasedBehaviourSimilarityOfUser(
                        user1.getLike(),
                        user2.getLike()
                )
        );
        System.out.println("behaviourBasedSimilarityOfUser: "
                           + similarityService.behaviourBasedSimilarityOfUser(
                        user1, user2
                )
        );
    }
}
