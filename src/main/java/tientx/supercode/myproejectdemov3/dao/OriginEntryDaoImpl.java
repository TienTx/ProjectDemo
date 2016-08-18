/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tientx.supercode.myproejectdemov3.model.OriginEntry;
import twitter4j.ResponseList;
import twitter4j.Status;

/**
 *
 * @author zOzDarKzOz
 */
public class OriginEntryDaoImpl
        extends BaseDao
        implements OriginEntryDao
{

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public boolean insertOriginEntry(OriginEntry oe)
    {
        String sqlInsert = "SET NAMES utf8mb4;INSERT INTO tblOriginEntry("
//        String sqlInsert = "INSERT INTO tblOriginEntry("
                           + "idOriginEntry, createDate, content, idUser"
                           + ") VALUES(?, ?, ?, ?);";
        try {
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, oe.getIdOriginEntry());
            ps.setDate(2, oe.getCreateDate());
            ps.setString(3, oe.getsContent());
            ps.setString(4, oe.getIdUser());

            return insert(ps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean insertListOriginEntryUseBatch(ResponseList<Status> list,
                                                 Long id)
    {
        boolean isError = false;
        String sqlInsert = "INSERT INTO tblOriginEntry("
                           + "idOriginEntry, createDate, content, idUser"
                           + ") VALUES(?, ?, ?, ?);";
        try {
            ps = conn.prepareStatement(sqlInsert);
            list.stream().forEach((l) -> {
                try {
                    ps.setString(1, l.getId() + "");
                    java.util.Date utilDate = l.getCreatedAt();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    ps.setDate(2, sqlDate);
                    ps.setString(3, l.getText());
                    ps.setString(4, id + "");
                    ps.addBatch();
                    ps.clearParameters();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            int[] kq = ps.executeBatch();
            if (kq != null && kq.length > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isError = true;
        } finally {
            try {
                if (isError) {
                    conn.rollback();
                } else {
                    conn.commit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
