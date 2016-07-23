/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import tientx.supercode.myproejectdemov3.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author zOzDarKzOz
 */
public class BaseDao {

    protected Connection conn;

    public BaseDao() {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean manuallyExecute(PreparedStatement ps) {
        if (ps != null) {
            try {
                int numRow = ps.executeUpdate();
                if (numRow != 0) {
                    conn.commit();
                    return true;
                }
                conn.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean insert(PreparedStatement ps) {
        return manuallyExecute(ps);
    }

    public boolean update(PreparedStatement ps) {
        return manuallyExecute(ps);
    }

    public boolean delete(PreparedStatement ps) {
        return manuallyExecute(ps);
    }

    public ResultSet getData(PreparedStatement ps) {
        try {
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }
}
