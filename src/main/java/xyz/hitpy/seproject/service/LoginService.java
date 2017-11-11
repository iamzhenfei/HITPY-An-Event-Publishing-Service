package xyz.hitpy.seproject.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.hitpy.seproject.mysqlcon.SqlCon;

public class LoginService {
	public static boolean validating(String username, String password) {
		boolean vali = false;
		SqlCon c = new SqlCon();
		String pass = null;
		ResultSet res = c.executeQuery("SELECT * FROM sedb.user WHERE USERNAME = " + "\"" + username + "\"");
		try {
			if (res != null && res.first()) {
				pass = res.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (password != null && username != null && pass != null && pass.equals(password)) {
			vali = true;
		}
		return vali;
	}
}
