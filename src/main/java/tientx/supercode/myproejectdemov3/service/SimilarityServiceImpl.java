/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import tientx.supercode.myproejectdemov3.model.CommentLike;
import tientx.supercode.myproejectdemov3.model.Community;
import tientx.supercode.myproejectdemov3.model.Entry;
import tientx.supercode.myproejectdemov3.model.Join;
import tientx.supercode.myproejectdemov3.model.Like;
import tientx.supercode.myproejectdemov3.model.Post;
import tientx.supercode.myproejectdemov3.model.SetsOfCommunities;
import tientx.supercode.myproejectdemov3.model.SetsOfEntries;
import tientx.supercode.myproejectdemov3.model.User;

/**
 *
 * @author zOzDarKzOz
 */
public class SimilarityServiceImpl
        implements SimilarityService
{

    //the weight of the feature Name, Description, and Category
    private final double W1;
    private final double W2;
    private final double W3;

    //WE1,WE2,WE3,WE4 are respectively the weight of the similarity based on 
    //Posting, Joining communities, Liking entries, and Comment/like comment
    private final double WE1;
    private final double WE2;
    private final double WE3;
    private final double WE4;

    public SimilarityServiceImpl()
    {
        this.W1 = 0.5;
        this.W2 = 0.3;
        this.W3 = 0.2;
        //
        this.WE1 = 0.4;
        this.WE2 = 0.3;
        this.WE3 = 0.2;
        this.WE4 = 0.1;
    }

    //similarity between two sets of keywords
    //similarity = 0 if one of two sets of books is empty
    private double similarityBetweenTwoSetsOfKeywords(List<String> sok1,
                                                      List<String> sok2)
    {
        if (sok1 == null || sok1.size() <= 0 || sok2 == null || sok2.size() <= 0) {
            return 0;
        }
        int m, n, k = 0;
        m = sok1.size();
        n = sok2.size();
        if (m < n) {
            for (int i = 0; i < m; i++) {
                if (sok2.contains(sok1.get(i))) {
                    k++;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (sok1.contains(sok2.get(i))) {
                    k++;
                }
            }
        }
        double sml = (double) (2 * k) / (m + n);
        return sml;
    }

    //similarity between two entries
    private double similarityOnCategory(Entry et1, Entry et2)
    {
        return similarityBetweenTwoSetsOfKeywords(et1.getSokCategory(), et2.getSokCategory());
    }

    private double similarityOnSentiment(Entry et1, Entry et2)
    {
        if (Objects.equals(et1.getdSentiment(), et2.getdSentiment())) {
            return 1;
        } else if (et1.getdSentiment() == 0.5 || et2.getdSentiment() == 0.5) {
            return 0.5;
        } else {
            return 0;
        }
    }

    //similarity = 0 if one of two entries is empty
    private double similarityBetweenTwoEntries(Entry et1, Entry et2)
    {
        if (et1 == null || et2 == null) {
            return 0;
        }
        double d1 = similarityOnCategory(et1, et2);
        double d2 = similarityOnSentiment(et1, et2);
        return (d1 + d2 + (d1 * d2)) / 3;
    }

    //similarity between two sets of entries
    private List<Double> preNonOrderedSemanticVectorTforSOE(SetsOfEntries soet1,
                                                            SetsOfEntries soet2)
    {
        if (soet1.getListEntries() == null || soet2.getListEntries() == null
            || soet1.getListEntries().size() <= 0
            || soet2.getListEntries().size() <= 0) {
            return null;
        }
        int m = soet1.getListEntries().size();
        int n = soet2.getListEntries().size();
        List<Double> T = new ArrayList<>();
        double d1, d2, dmax1, dmax2, dmin;
        Entry ei;

        for (int i = 0; i < (m + n); i++) {
            if (i < m) {
                ei = soet1.getListEntries().get(i);
            } else {
                ei = soet2.getListEntries().get(i - m);
            }
            //dmax1 = Sentry(ei,e10)
            dmax1 = similarityBetweenTwoEntries(ei, soet1.getListEntries().get(0));
            for (int k = 1; k < m; k++) {
                //Sentry(ei,e1k)
                d1 = similarityBetweenTwoEntries(ei, soet1.getListEntries().get(k));
                //dmax1 = max(Sentry(ei,e1k))
                dmax1 = Math.max(dmax1, d1);
            }
            //dmax2 = Sentry(ei,e20)
            dmax2 = similarityBetweenTwoEntries(ei, soet2.getListEntries().get(0));
            for (int v = 1; v < n; v++) {
                //Sentry(ei,e2v)
                d2 = similarityBetweenTwoEntries(ei, soet2.getListEntries().get(v));
                //dmax2 = max(Sentry(ei,e2v))
                dmax2 = Math.max(dmax2, d2);
            }

            dmin = Math.min(dmax1, dmax2);
            T.add(dmin);
        }
        return T;
    }

    //similarity = 0 if one of two sets of entries is empty
    private double similarityBetweenTwoSetsOfEntries(SetsOfEntries soet1,
                                                     SetsOfEntries soet2)
    {
        if (soet1 == null || soet2 == null) {
            return 0;
        }
        List<Double> T = preNonOrderedSemanticVectorTforSOE(soet1, soet2);
        if (T != null && T.size() > 0) {
            double total = 0, size = T.size();
            for (int i = 0; i < size; i++) {
                total += T.get(i);
            }
            return (double) total / size;
        }
        return 0;
    }

    //similarity between two communities
    private double similarityBetweenTwoCommunities(Community cm1, Community cm2)
    {
        //similarity of name
        double dS1 = W1 * similarityBetweenTwoSetsOfKeywords(cm1.getSokName(), cm2.getSokName());
        //similarity of category
        double dS2 = W2 * similarityBetweenTwoSetsOfKeywords(cm1.getSokCategory(), cm2.getSokCategory());
        //similarity of description
        double dS3 = W3 * similarityBetweenTwoSetsOfKeywords(cm1.getSokDescription(), cm2.getSokDescription());
        return (dS1 + dS2 + dS3);
    }

    //similarity between two sets of communities
    private List<Double> preNonOrderedSemanticVectorTforSOC(
            SetsOfCommunities soc1, SetsOfCommunities soc2)
    {
        if (soc1.getListCommunities() == null || soc2.getListCommunities() == null
            || soc1.getListCommunities().size() <= 0
            || soc2.getListCommunities().size() <= 0) {
            return null;
        }
        int m = soc1.getListCommunities().size();
        int n = soc2.getListCommunities().size();
        List<Double> T = new ArrayList<>();
        double d1, d2, dmax1, dmax2, dmin;
        Community ei;

        for (int i = 0; i < (m + n); i++) {
            if (i < m) {
                ei = soc1.getListCommunities().get(i);
            } else {
                ei = soc2.getListCommunities().get(i - m);
            }
            //dmax1 = Scommunities(ci,c10)
            dmax1 = similarityBetweenTwoCommunities(ei, soc1.getListCommunities().get(0));
            for (int k = 1; k < m; k++) {
                //Scommunities(ci,c1k)
                d1 = similarityBetweenTwoCommunities(ei, soc1.getListCommunities().get(k));
                //dmax1 = max(Scommunities(ci,c1k))
                dmax1 = Math.max(dmax1, d1);
            }
            //dmax2 = Scommunities(ci,c20)
            dmax2 = similarityBetweenTwoCommunities(ei, soc2.getListCommunities().get(0));
            for (int v = 1; v < n; v++) {
                //Scommunities(ci,c2v)
                d2 = similarityBetweenTwoCommunities(ei, soc2.getListCommunities().get(v));
                //dmax2 = max(Scommunities(ci,c2v))
                dmax2 = Math.max(dmax2, d2);
            }

            dmin = Math.min(dmax1, dmax2);
            T.add(dmin);
        }
        return T;
    }

    //similarity = 0 if one of two sets of communities is empty
    private double similarityBetweenTwoSetsOfCommunities(SetsOfCommunities soc1,
                                                         SetsOfCommunities soc2)
    {
        if (soc1 == null || soc2 == null) {
            return 0;
        }
        List<Double> T = preNonOrderedSemanticVectorTforSOC(soc1, soc2);
        if (T != null && T.size() > 0) {
            double total = 0, size = T.size();
            for (int i = 0; i < size; i++) {
                total += T.get(i);
            }
            return (double) total / size;
        }
        return 0;
    }

    //posting behaviour similarity of user
    //similarity = 0 if one of two posts is empty
    @Override
    public double postingBehaviourSimilarityOfUser(Post post1, Post post2)
    {
        if (post1 == null || post2 == null) {
            return 0;
        }
        return similarityBetweenTwoSetsOfEntries(post1.getSoePost(), post2.getSoePost());
    }

    //like behaviour similarity of user
    //similarity = 0 if one of two likes is empty
    @Override
    public double likeBasedBehaviourSimilarityOfUser(Like lik1, Like like2)
    {
        if (lik1 == null || like2 == null) {
            return 0;
        }
        return similarityBetweenTwoSetsOfEntries(lik1.getSoeLike(), like2.getSoeLike());
    }

    //joining behaviour similarity of user
    //similarity = 0 if one of two joins is empty
    @Override
    public double joiningBehaviourSimilarityOfUser(Join join1, Join join2)
    {
        if (join1 == null || join2 == null) {
            return 0;
        }
        return similarityBetweenTwoSetsOfCommunities(join1.getSocJoin(), join2.getSocJoin());
    }

    //comment/like comment based behaviour similarity of user
    //similarity = 0 if one of two comments/like comments is empty
    @Override
    public double commentLikeCommentBasedBehaviourSimilarityOfUser(
            CommentLike cl1,
            CommentLike cl2)
    {
        if (cl1 == null || cl2 == null) {
            return 0;
        }
        double d1 = similarityBetweenTwoSetsOfEntries(cl1.getSoePositive(), cl2.getSoePositive());
        double d2 = similarityBetweenTwoSetsOfEntries(cl1.getSoeNegative(), cl2.getSoeNegative());
        double d3 = similarityBetweenTwoSetsOfEntries(cl1.getSoePositive(), cl2.getSoeNegative());
        double d4 = similarityBetweenTwoSetsOfEntries(cl1.getSoeNegative(), cl2.getSoePositive());
        return Math.min(1, Math.max(0, d1 + d2 - d3 - d4));
    }

    //behaviour based similarity of user
    //similarity = 0 if one of two users is empty
    @Override
    public double behaviourBasedSimilarityOfUser(User user1, User user2)
    {
        if (user1 == null || user2 == null) {
            return 0;
        }
        //the similarity between the two users based on post entries
        double d1 = WE1 * postingBehaviourSimilarityOfUser(user1.getPost(), user2.getPost());
        //the similarity between the two users based on join communities
        double d2 = WE2 * joiningBehaviourSimilarityOfUser(user1.getJoin(), user2.getJoin());
        //the similarity between the two users based on like entries
        double d3 = WE3 * likeBasedBehaviourSimilarityOfUser(user1.getLike(), user2.getLike());
        //the similarity between the two users based on comment/like behaviour
        double d4 = WE4 * commentLikeCommentBasedBehaviourSimilarityOfUser(user1.getCommentlike(), user2.getCommentlike());
        return (d1 + d2 + d3 + d4);
    }

}
