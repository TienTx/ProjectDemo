/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import java.util.ArrayList;
import tientx.supercode.myproejectdemov3.model.OriginEntry;
import twitter4j.ResponseList;
import twitter4j.Status;

/**
 *
 * @author zOzDarKzOz
 */
public interface OriginEntryDao
{

    ArrayList<OriginEntry> getAll();

    boolean insertPostEntry(OriginEntry oe);

    boolean insertLikeEntry(OriginEntry oe);

    boolean insertListPostEntryUseBatch(ResponseList<Status> list, Long id);

    boolean insertListLikeEntryUseBatch(ResponseList<Status> list, Long id);

    boolean insertListCommentLikeEntryUseBatch(ResponseList<Status> list,
                                               Long id);

    boolean updateListOriginEntryUseBatch(ArrayList<OriginEntry> list);
}
