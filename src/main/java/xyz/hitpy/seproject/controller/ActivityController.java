package xyz.hitpy.seproject.controller;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.hitpy.seproject.mysqlcon.SqlCon;

@Controller
public class ActivityController {
    @RequestMapping("/create_activity")
    public String createActivityPage() { return "create_activity"; }
    
    @RequestMapping(value = "/postnow", method =  RequestMethod.POST)
    public String addActivity(@RequestParam("eventName") String eventName, @RequestParam("eventLocation") String eventLocation,
            @RequestParam("eventTime") String eventTime, @RequestParam("content") String content, ModelMap model,
            HttpServletResponse response,HttpServletRequest request)
    {
    		try {
				eventName = new String(eventName.getBytes("ISO-8859-1"),"UTF-8");
				eventLocation = new String(eventLocation.getBytes("ISO-8859-1"),"UTF-8");
				eventTime = new String(eventTime.getBytes("ISO-8859-1"),"UTF-8");
				content = new String(content.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		System.out.println(eventName);
        String username = (String) request.getSession().getAttribute("username");
        if (username == null || username.equals("")) { return "redirect:login"; }
        SqlCon c = new SqlCon();
        int uid = 0;
        ResultSet res = c.executeQuery("SELECT * FROM sedb.user WHERE USERNAME = " + "\"" + username + "\" limit 0, 1");
        try {
            if (res != null && res.first()) {
                uid = res.getInt("uid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error at addActivity in ActivityController.java");
        }
        // 通过cookie验证了
        uid++;  // 用户发帖数加1
        String upd = "insert into sedb.activity(name, time, location, content, username, uid) values(\"" + eventName +
                "\",\"" + eventTime + "\",\"" + eventLocation + "\", \"" + HtmlCoder.encode(content) + "\", \"" + username +
                "\"," + uid + ");";
        c.executeUpdate(upd);
        upd = "update sedb.user set uid = " + uid + " where username = \"" + username + "\";";
        c.executeUpdate(upd);
        // 已添加入数据库,查询aid
        int aid = -1;
        res = c.executeQuery("SELECT aid FROM sedb.activity WHERE USERNAME = " + "\"" + username +
                "\" and uid=" + uid + " limit 0, 1");
        try {
            if (res != null && res.first()) {
                aid = res.getInt("aid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error at 56 of addActivity in ActivityController.java");
        }
        model.addAttribute("aid", aid);
        return "create_a_success";
    }
    
    @RequestMapping("showActivity")
    public String showActivity(@RequestParam("aid") int aid, ModelMap model, HttpServletResponse response,HttpServletRequest request)
    {
        SqlCon c = new SqlCon();
        String eventName = null, eventTime = null, eventLocation = null, content = null, poster = null, created = null;
        Timestamp created_ts = null;
        ResultSet res = c.executeQuery("select * from sedb.activity where aid=" + aid + " limit 0, 1");
        try {
            if (res != null && res.first()) {
                eventName = res.getString("name");
                eventTime = res.getString("time");
                eventLocation = res.getString("location");
                content = res.getString("content");
                poster = res.getString("username");
                created_ts = res.getTimestamp("created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error at 73 of addActivity in ActivityController.java");
        }
        if (created_ts == null) { return "404"; }
        created = created_ts.toString();
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("eventName", eventName);
        model.addAttribute("eventTime", eventTime);
        model.addAttribute("eventLocation", eventLocation);
        model.addAttribute("content", HtmlCoder.decode(content));
        model.addAttribute("poster", poster);
        model.addAttribute("created", created);
        model.addAttribute("username", username);
        return "show_activity";
    }
}

class HtmlCoder
{
    public static String encode(String str)
    {
        str = str.replaceAll("'", "''");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\n", "<br>");
        str = str.replaceAll("“", "&ldquo;");
        str = str.replaceAll("”", "&rdquo;");
        return str;
    }

    public static String decode(String str)
    {
        str = str.replaceAll("&rdquo;", "”");
        str = str.replaceAll("&ldquo;", "“");
        str = str.replaceAll("<br>", "\n");
        str = str.replaceAll("&gt;", ">");
        str = str.replaceAll("&lt;", "<");
        str = str.replaceAll("&quot;", "\"");
        str = str.replaceAll("''", "'");
        return str;
    }
}
