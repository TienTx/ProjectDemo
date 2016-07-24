/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tientx.supercode.myproejectdemov3.model.Entry;

/**
 *
 * @author zOzDarKzOz
 */
public class EntryDaoImpl extends BaseDao implements EntryDao {

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public boolean insertEntry(Entry e) {
        String s = "";
        int size;
        if (e.getSokCategory() != null && (size = e.getSokCategory().size()) > 0) {
            for (int i = 0; i < size; i++) {
                if (i != (size - 1)) {
                    s += e.getSokCategory().get(i) + ",";
                } else {
                    s += e.getSokCategory().get(i);
                }
            }
        }
        String sqlInsert = "INSERT INTO tblEntry("
                + "idSetsOfEntries, "
                + "sokCategory, "
                + "dSentiment "
                + ") VALUES(?, ?, ?);";
        try {
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, e.getIdEntry());
            ps.setString(2, s);
            ps.setDouble(3, e.getdSentiment());

            return insert(ps);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Entry getEntryById(String id) {
        String sqlSelect = "SELECT "
                + "tblEntry.idEntry, "
                + "tblEntry.sokCategory, "
                + "tblEntry.dSentiment "
                + "FROM tblEntry WHERE "
                + "tblEntry.idEntry = ?;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setString(1, id);

            rs = getData(ps);
            if (rs != null && rs.next()) {
                String[] s = rs.getString(2).split(",");
                List<String> sokCategory = new ArrayList<>();
                sokCategory.addAll(Arrays.asList(s));
                Entry entry = new Entry(rs.getString(1), sokCategory, rs.getDouble(3));
                return entry;
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
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
