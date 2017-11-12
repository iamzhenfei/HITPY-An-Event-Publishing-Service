package xyz.hitpy.seproject.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.hitpy.seproject.model.ActivityPreview;
import xyz.hitpy.seproject.mysqlcon.SqlCon;

@Controller
public class UserController {
    @RequestMapping("myspace")
    public String showProfile(HttpServletRequest request, HttpServletResponse response,ModelMap model)
    {
        String username = (String)request.getSession().getAttribute("username");
        if (username == null) { return "404"; }
        String entryYear = null, ps = null, activity = null;
        int gender = 0, uid = 0;
        SqlCon con = new SqlCon();
        String query = "SELECT * FROM sedb.user where username=\"" + username +"\" limit 1";
        ResultSet res = con.executeQuery(query);
        try {
            while (res.next()) {
                entryYear = res.getDate("entryYear").toString();
                ps = res.getString("ps");
                activity = res.getString("activity");
                gender = res.getInt("gender");
                uid = res.getInt("uid");
            }
        } catch (SQLException e) {
            System.out.println("error at 41 in showProfile of UserController");
            e.printStackTrace();
        }
        // 根据aid取活动名称
        List<String> aids =  Arrays.asList((activity.split(" ")));
        List<String> activities = new ArrayList<String>();
        Iterator<String> it = aids.iterator();
        if(it.hasNext())
        {
        	    it.next();
        }
        while(it.hasNext())
        {
            query = "SELECT name FROM sedb.activity where aid=" + it.next() +" limit 1";
            res = con.executeQuery(query);
            try {
                while(res.next())
                {
                    activities.add(res.getString("name"));
                }
            } catch (SQLException e) {
                System.out.println("error at 58 in showProfile of userController");
                e.printStackTrace();
            }
        }
        
        model.addAttribute("username", username);
        model.addAttribute("entryYear", entryYear);
        model.addAttribute("ps", ps);
        model.addAttribute("activitylist", activities);
        model.addAttribute("gender", gender);
        model.addAttribute("uid", uid);
        return "myspace";
    }
}
