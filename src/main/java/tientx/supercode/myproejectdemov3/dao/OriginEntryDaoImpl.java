/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    public boolean insertPostEntry(OriginEntry oe)
    {
        String sqlInsert = "SET NAMES utf8mb4;INSERT INTO tblOriginEntry("
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
    public boolean insertListPostEntryUseBatch(ResponseList<Status> list,
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

    @Override
    public boolean updateListOriginEntryUseBatch(ArrayList<OriginEntry> list)
    {
        boolean isError = false;
        String sqlInsert = "UPDATE tblOriginEntry "
                           + "SET "
                           + "category = ?, "
                           + "sentiment = ? "
                           + "WHERE idOriginEntry = ?;";
        try {
            ps = conn.prepareStatement(sqlInsert);
            list.stream().forEach((l) -> {
                try {
                    ps.setString(1, l.getsCategory());
                    ps.setString(2, l.getsSentiment());
                    ps.setString(3, l.getIdOriginEntry());
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
    public ArrayList<OriginEntry> getAll()
    {
        String sqlSelect = "SELECT "
                           + "idOriginEntry, "
                           + "createDate, "
                           + "content, "
                           + "idUser, "
                           + "category, "
                           + "sentiment, "
                           + "type "
                           + "FROM tblOriginEntry;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = getData(ps);
            if (rs != null) {
                ArrayList<OriginEntry> list = new ArrayList<>();
                while (rs.next()) {
                    OriginEntry oe = new OriginEntry(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                    list.add(oe);
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
    public boolean insertLikeEntry(OriginEntry oe)
    {
        String sqlInsert = "SET NAMES utf8mb4;INSERT INTO tblOriginEntry("
                           + "idOriginEntry, createDate, content, idUser, type"
                           + ") VALUES(?, ?, ?, ?, ?);";
        try {
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, oe.getIdOriginEntry());
            ps.setDate(2, oe.getCreateDate());
            ps.setString(3, oe.getsContent());
            ps.setString(4, oe.getIdUser());
            ps.setInt(5, 2);

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
    public boolean insertListLikeEntryUseBatch(ResponseList<Status> list,
                                               Long id)
    {
        boolean isError = false;
        String sqlInsert = "INSERT INTO tblOriginEntry("
                           + "idOriginEntry, createDate, content, idUser, type"
                           + ") VALUES(?, ?, ?, ?, ?);";
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
                    ps.setInt(5, 2);
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
    public boolean insertListCommentLikeEntryUseBatch(ResponseList<Status> list,
                                                      Long id)
    {
        boolean isError = false;
        String sqlInsert = "INSERT INTO tblOriginEntry("
                           + "idOriginEntry, createDate, content, idUser, type"
                           + ") VALUES(?, ?, ?, ?, ?);";
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
                    ps.setInt(5, 3);
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
