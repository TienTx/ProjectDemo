/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import tientx.supercode.myproejectdemov3.model.User;
import twitter4j.PagableResponseList;

/**
 *
 * @author zOzDarKzOz
 */
public class UserDaoImpl
        extends BaseDao
        implements UserDao
{

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public boolean insert(User user)
    {
        String sqlInsert = "INSERT INTO tblUser(idUser, screenName) "
                           + "VALUES(?, ?);";
        try {
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, user.getIdUser());
            ps.setString(2, user.getScreenName());

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
    public boolean insertListUseBatch(PagableResponseList<twitter4j.User> list)
    {
        boolean isError = false;
        String sqlInsert = "INSERT INTO tblUser(idUser, screenName) "
                           + "VALUES(?, ?);";
        try {
            ps = conn.prepareStatement(sqlInsert);
            list.stream().forEach((l) -> {
                try {
                    ps.setString(1, l.getId() + "");
                    ps.setString(2, l.getScreenName());
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

    @Override
    public ArrayList<User> getAll()
    {
        String sqlSelect = "SELECT * FROM tblUser;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = getData(ps);
            if (rs != null) {
                ArrayList<User> list = new ArrayList<>();
                while (rs.next()) {
                    User user = new User(rs.getString(1), rs.getString(2), null, null, null, null);
                    list.add(user);
                }
                if (list != null) {
                    return list;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean standardList()
    {
        return false;
    }

}
