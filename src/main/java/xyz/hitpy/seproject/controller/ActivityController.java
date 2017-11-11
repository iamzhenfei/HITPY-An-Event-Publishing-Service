package xyz.hitpy.seproject.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.hitpy.seproject.mysqlcon.SqlCon;

@Controller
public class ActivityController {
    @RequestMapping("create_activity")
    public String createActivityPage() { return "create_activity"; }
    
    @RequestMapping("postnow")
    public String addActivity(@RequestParam("eventName") String eventName, @RequestParam("eventLocation") String eventLocation,
            @RequestParam("eventTime") String eventTime, @RequestParam("content") String content, ModelMap model,
            HttpServletResponse response,HttpServletRequest request)
    {
        String username = (String) request.getSession().getAttribute("username");  
        String password = (String) request.getSession().getAttribute("password");
        SqlCon c = new SqlCon();
        String pass = null;
        int uid = 0;
        ResultSet res = c.executeQuery("SELECT * FROM sedb.user WHERE USERNAME = " + "\"" + username + "\"");
        try {
            if (res != null && res.first()) {
                pass = res.getString("password");
                uid = res.getInt("uid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error at addActivity in ActivityController.java");
        }
        if (!password.equals(pass))
        {
            System.out.println("attack by changing user in cookie");
            return "you are dead";
        }
        // 通过cookie验证了
        uid++;  // 用户发帖数加1
        String upd = "insert into sedb.activity(name, time, location, content, username, uid) values(\"" + eventName +
                "\",\"" + eventTime + "\",\"" + eventLocation + "\", \"" + content + "\", \"" + username +
                "\"," + uid + ");";
        c.executeUpdate(upd);
        upd = "update sedb.user set uid = " + uid + " where username = \"" + username + "\";";
        c.executeUpdate(upd);
        // 已添加入数据库,查询aid
        int aid = -1;
        res = c.executeQuery("SELECT aid FROM sedb.activity WHERE USERNAME = " + "\"" + username + "\" and uid=" + uid);
        try {
            if (res != null && res.first()) {
                aid = res.getInt("aid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error at 62 of addActivity in ActivityController.java");
        }
        model.addAttribute("aid", aid);
        return "create_a_success";
    }
    @RequestMapping("query_activity")
    public void queryActivity(HttpServletRequest request, HttpServletResponse response) {
    		
    }
}
