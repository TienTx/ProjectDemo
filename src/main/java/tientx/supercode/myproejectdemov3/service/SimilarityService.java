/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.service;

import tientx.supercode.myproejectdemov3.model.CommentLike;
import tientx.supercode.myproejectdemov3.model.Join;
import tientx.supercode.myproejectdemov3.model.Like;
import tientx.supercode.myproejectdemov3.model.Post;
import tientx.supercode.myproejectdemov3.model.User;

/**
 *
 * @author zOzDarKzOz
 */
public interface SimilarityService
{

    double postingBehaviourSimilarityOfUser(Post post1, Post post2);

    double likeBasedBehaviourSimilarityOfUser(Like lik1, Like like2);

    double joiningBehaviourSimilarityOfUser(Join join1, Join join2);

    double commentLikeCommentBasedBehaviourSimilarityOfUser(CommentLike cl1,
                                                            CommentLike cl2);

    double behaviourBasedSimilarityOfUser(User user1, User user2);
}
