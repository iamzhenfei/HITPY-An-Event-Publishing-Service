package xyz.hitpy.seproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.hitpy.seproject.mysqlcon.SqlCon;

@Controller
public class RelationshipController {
    @RequestMapping("following")
    public String following(@RequestParam("fname") String fname, HttpServletRequest request, HttpServletResponse response,ModelMap model)
    {
        String username = (String)request.getSession().getAttribute("username");
        SqlCon c = new SqlCon();
        ResultSet res = c.executeQuery("select following from sedb.relationship where username=\"" +
        username + "\" limit 1;");
        String fstr = null;
        try {
            if (res != null && res.first())
                fstr = res.getString("following");
        } catch (SQLException e) {
            System.out.println("error at 30 of following in RelationshipController.java");
            e.printStackTrace();
        }
        if (fstr == null) return "404";
        model.addAttribute("following", Arrays.asList((fstr.split(" "))));
        return "following";
    }
    
    @RequestMapping("follower")
    public String follower(@RequestParam("fname") String fname, HttpServletRequest request, HttpServletResponse response,ModelMap model)
    {
        String username = (String)request.getSession().getAttribute("username");
        SqlCon c = new SqlCon();
        ResultSet res = c.executeQuery("select follower from sedb.relationship where username=\"" +
        username + "\" limit 1;");
        String fstr = null;
        try {
            if (res != null && res.first())
                fstr = res.getString("follower");
        } catch (SQLException e) {
            System.out.println("error at 51 of following in RelationshipController.java");
            e.printStackTrace();
        }
        if (fstr == null) return "404";
        model.addAttribute("follower", Arrays.asList((fstr.split(" "))));
        return "follower";
    }
    
    @RequestMapping("follow")
    public void follow(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        String fname = request.getParameter("fname");
        String username = (String)request.getSession().getAttribute("username");
        String following = null;
        SqlCon c = new SqlCon();
        int i = -1;
        // 取得username关注的人
        String query = "select following from sedb.relationship where username = \"" + username + "\" limit 1;";
        ResultSet rs = c.executeQuery(query);
        try {
            if(rs != null && rs.first())
                following = rs.getString("following");
        } catch (SQLException e) {
            System.out.println("error at 80 in methord 'follow' of class 'SigninController'");
            e.printStackTrace();
        }
        if (following == null)
        {
            // 没有取得关注的人，username不存在
            return;
        }
        // 判断username是否已经关注了fname
        i = following.indexOf(fname);
        StringBuilder sb = new StringBuilder();
        if (i == -1)
        {
            // 没有关注fname,在username的following里添加fname，followingnum加1
            sb.append("update sedb.relationship set following=\"");
            sb.append(following);
            sb.append(" ");
            sb.append(fname);
            sb.append("\", followingnum=followingnum + 1 where username=\"");
            sb.append(username);
            sb.append("\" limit 1;");
            c.executeUpdate(sb.toString());
            // 在fname的follower里添加username，follower加1
            sb = new StringBuilder();
            sb.append("update sedb.relationship set follower=CONCAT(follower,' ',\"");
            sb.append(username);
            sb.append("\"), followernum=followernum + 1 where username=\"");
            sb.append(fname);
            sb.append("\" limit 1;");
            c.executeUpdate(sb.toString());
            json.put("result", 1);
        }
        else
        {
            // 已经关注fname，在username的following里删除fname，followingnum减1
            following = following.replaceFirst(" " + fname, "");
            sb.append("update sedb.relationship set following=\"");
            sb.append(following);
            sb.append("\", followingnum=followingnum - 1 where username=\"");
            sb.append(username);
            sb.append("\" limit 1;");
            c.executeUpdate(sb.toString());
            // 在fname的follower里删除username， followernum减1
            String follower = null;
            query = "select follower from sedb.relationship where username = \"" + fname + "\" limit 1;";
            rs = c.executeQuery(query);
            try {
                if(rs != null && rs.first())
                    follower = rs.getString("follower");
            } catch (SQLException e) {
                System.out.println("error at 133 in methord 'follow' of class 'SigninController'");
                e.printStackTrace();
            }
            if (follower != null)
            {
                follower = follower.replaceFirst(" " + username, "");
                sb = new StringBuilder();
                sb.append("update sedb.relationship set follower=\"");
                sb.append(follower);
                sb.append("\", followernum=followernum - 1 where username=\"");
                sb.append(fname);
                sb.append("\" limit 1;");
                c.executeUpdate(sb.toString());
            }
            json.put("result", 0);
        }
        out.print(json);
    }
}
