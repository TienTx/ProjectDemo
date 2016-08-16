/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import tientx.supercode.myproejectdemov3.model.OriginEntry;
import twitter4j.ResponseList;
import twitter4j.Status;

/**
 *
 * @author zOzDarKzOz
 */
public interface OriginEntryDao
{

    boolean insertOriginEntry(OriginEntry oe);

    boolean insertListOriginEntryUseBatch(ResponseList<Status> list, Long id);
}
