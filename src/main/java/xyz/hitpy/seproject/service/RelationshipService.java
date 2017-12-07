package xyz.hitpy.seproject.service;


import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.hitpy.seproject.mysqlcon.SqlCon;

public class RelationshipService {
    public static int [] getFollowNum(String username)
    {
        int[] fnums = new int[]{0, 0};
        SqlCon c = new SqlCon();
        ResultSet res = c.executeQuery("select followingnum, followernum from sedb.relationship where username=\"" +
        username + "\" limit 1;");
        try {
            if(res != null && res.first())
            {
                fnums[0] = res.getInt("followingnum");
                fnums[1] = res.getInt("followernum");
            }
        } catch (SQLException e) {
            System.out.println("error at 23 of getFollowNum in RelationshipService.java");
            e.printStackTrace();
        }
        return fnums;
    }
}
