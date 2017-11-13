package xyz.hitpy.seproject.service;

import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import xyz.hitpy.seproject.mysqlcon.SqlCon;

@Service
public class SearchService {
	public static ResultSet searchByName(String names) {
		SqlCon con = new SqlCon();
		String sql = "select * from sedb.activity where name like'%";
		if (names!=null) {
			String n[] = names.split(" ");
			int len = n.length;
			if (len >= 1) {
				sql = sql + n[0] + "%';";
			}
			else
				return null;
			return con.executeQuery(sql);
		}
		else {
			return null;
		}	
	}
}
