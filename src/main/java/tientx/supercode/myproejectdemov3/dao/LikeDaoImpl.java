/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tientx.supercode.myproejectdemov3.model.Entry;
import tientx.supercode.myproejectdemov3.model.Like;
import tientx.supercode.myproejectdemov3.model.SetsOfEntries;

/**
 *
 * @author zOzDarKzOz
 */
public class LikeDaoImpl
        extends BaseDao
        implements LikeDao
{

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public Like getByUserId(Long id)
    {
        String sqlSelect = "SELECT "
                           + "idOriginEntry, "
                           + "category, "
                           + "sentiment "
                           + "FROM tblOriginEntry "
                           + "WHERE idUser = ? AND type=2;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setString(1, id + "");
            rs = getData(ps);
            if (rs != null) {
                List<Entry> list = new ArrayList<>();
                while (rs.next()) {
                    List<String> sokCategory = new ArrayList<>();
                    sokCategory.add(rs.getString(2));
                    double dSentiment = 0;
                    switch (rs.getString(3)) {
                        case "NEGATIVE":
                            dSentiment = 0;
                            break;
                        case "NEUTRAL":
                            dSentiment = 0.5;
                            break;
                        case "POSITIVE":
                            dSentiment = 1;
                            break;
                    }
                    Entry e = new Entry(rs.getString(1), sokCategory, dSentiment);
                    list.add(e);
                }
                if (list != null) {
                    SetsOfEntries soe = new SetsOfEntries(null, list);
                    return new Like(null, soe);
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

}
