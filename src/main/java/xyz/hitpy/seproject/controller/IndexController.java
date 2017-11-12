package xyz.hitpy.seproject.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.hitpy.seproject.model.ActivityPreview;
import xyz.hitpy.seproject.mysqlcon.SqlCon;

@Controller
public class IndexController {
	@RequestMapping(value = "/index")
	public String indexPage(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username"); 
		model.addAttribute("username", username);
		ArrayList<ActivityPreview> activities = new ArrayList<ActivityPreview>();
		SqlCon con = new SqlCon();
		String query = "SELECT * FROM sedb.activity";
		ResultSet res = con.executeQuery(query);
		try {
			while (res.next()) {
				activities.add(new ActivityPreview(res.getInt("aid"), res.getString("name"), 
						res.getString("time"), res.getString("location"), res.getString("username")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("activitylist",activities);
		return "index";
	}
}

/*
 * class activityPreview {
 
	private int aid;
	private String name;
	private String time;
	private String location;
	private String username;
	public activityPreview() {
		aid = 0;
		name = null;
		time = null;
		location = null;
		username = null;
	}
	public activityPreview(int aid, String name, String time, String location, String username) {
		this.aid = aid;
		this.name = name;
		this.time = time;
		this.username = username;
		this.location = location;
	}
	public int getAid() {
		return aid;
	}
	public String getName() {
		return name;
	}
	public String getTime() {
		return time;
	}
	public String getLocation() {
		return location;
	}
	public String getUsername() {
		return username;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public void setName(String name) { 
		this.name = name;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
*/