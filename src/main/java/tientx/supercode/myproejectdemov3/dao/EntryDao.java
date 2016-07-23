/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import tientx.supercode.myproejectdemov3.model.Entry;

/**
 *
 * @author zOzDarKzOz
 */
public interface EntryDao {

    boolean insertEntry(Entry e);

    Entry getEntryById(Integer id);
}
