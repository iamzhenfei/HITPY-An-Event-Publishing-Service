package xyz.hitpy.seproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.hitpy.seproject.model.ActivityPreview;
import xyz.hitpy.seproject.model.MyActivityPreview;
import xyz.hitpy.seproject.mysqlcon.SqlCon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


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
        ArrayList<ActivityPreview> activities = new ArrayList<ActivityPreview>();
        if (!activity.equals(""))
        {
            List<String> aids =  Arrays.asList((activity.split(" ")));
            aids = aids.subList(1, aids.size());
            Iterator<String> it = aids.iterator();
            while(it.hasNext())
            {
                query = "SELECT * FROM sedb.activity where aid=" + it.next() +" limit 1";
                res = con.executeQuery(query);
                try {
                    while(res.next())
                    {
                        activities.add(new ActivityPreview(res.getInt("aid"), res.getString("name"), 
                                res.getString("time"), res.getString("location"), res.getString("username")));
                    }
                } catch (SQLException e) {
                    System.out.println("error at 62 in showProfile of userController");
                    e.printStackTrace();
                }
            }
        }
        
        ArrayList<MyActivityPreview> myActivities = new ArrayList<MyActivityPreview>();
        query = "select * from sedb.activity where username = \"" + username + "\";";
        res = con.executeQuery(query);
        String checkid = "";
        List<String> checkids;
        try {
            while(res.next())
            {
                checkid = res.getString("checkid");
                checkids =  Arrays.asList((checkid.split(" ")));
                checkids = checkids.subList(1, checkids.size());
                myActivities.add(new MyActivityPreview(res.getInt("aid"), res.getString("name"), 
                        res.getString("time"), res.getString("location"), res.getString("username"), checkids.size()));
            }
        } catch (SQLException e) {
            System.out.println("error at 80 in showProfile of userController");
            e.printStackTrace();
        }
        
        model.addAttribute("username", username);
        model.addAttribute("entryYear", entryYear);
        model.addAttribute("ps", ps);
        model.addAttribute("activitylist", activities);
        model.addAttribute("myActivitylist", myActivities);
        model.addAttribute("gender", gender);
        model.addAttribute("uid", uid);
        return "myspace";
    }

    @RequestMapping("space")
    public String showProfile(@RequestParam("username") String username, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        if (username == null) {
            return "404";
        }
        String entryYear = null, ps = null, activity = null;
        int gender = 0, uid = 0;
        SqlCon con = new SqlCon();
        String query = "SELECT * FROM sedb.user where username=\"" + username + "\" limit 1";
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
        ArrayList<ActivityPreview> activities = new ArrayList<ActivityPreview>();
        if (!activity.equals("")) {
            List<String> aids = Arrays.asList((activity.split(" ")));
            aids = aids.subList(1, aids.size());
            Iterator<String> it = aids.iterator();
            while (it.hasNext()) {
                query = "SELECT * FROM sedb.activity where aid=" + it.next() + " limit 1";
                res = con.executeQuery(query);
                try {
                    while (res.next()) {
                        activities.add(new ActivityPreview(res.getInt("aid"), res.getString("name"),
                                res.getString("time"), res.getString("location"), res.getString("username")));
                    }
                } catch (SQLException e) {
                    System.out.println("error at 62 in showProfile of userController");
                    e.printStackTrace();
                }
            }
        }

        ArrayList<MyActivityPreview> myActivities = new ArrayList<MyActivityPreview>();
        query = "select * from sedb.activity where username = \"" + username + "\";";
        res = con.executeQuery(query);
        String checkid = "";
        List<String> checkids;
        try {
            while (res.next()) {
                checkid = res.getString("checkid");
                checkids = Arrays.asList((checkid.split(" ")));
                checkids = checkids.subList(1, checkids.size());
                myActivities.add(new MyActivityPreview(res.getInt("aid"), res.getString("name"),
                        res.getString("time"), res.getString("location"), res.getString("username"), checkids.size()));
            }
        } catch (SQLException e) {
            System.out.println("error at 80 in showProfile of userController");
            e.printStackTrace();
        }

        model.addAttribute("username", username);
        model.addAttribute("entryYear", entryYear);
        model.addAttribute("ps", ps);
        model.addAttribute("activitylist", activities);
        model.addAttribute("myActivitylist", myActivities);
        model.addAttribute("gender", gender);
        model.addAttribute("uid", uid);
        return "space";
    }
}
