package xyz.hitpy.seproject.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.hitpy.seproject.mysqlcon.SqlCon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class SignupController {
    @RequestMapping("/ifUserExist")
    public void ifUserExist(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        SqlCon sqlCon = new SqlCon();
        String query = "select * from user where username = \"" + username + "\" limit 1;";
        JSONObject json=new JSONObject();
        ResultSet rs = sqlCon.executeQuery(query);
        json.put("result", "0");
        try {
            while(rs.next())
            {
                json.put("result", "1");
                break;
            }
        } catch (SQLException e) {
            System.out.println("error in methord 'ifUserExist' of class 'SigninController'");
            e.printStackTrace();
        }
        out.print(json);
    }
    
    @RequestMapping("/addUser")
    public String addUser(@RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("gender") int gender, @RequestParam("entryYear") int entryYear, @RequestParam("ps") String ps)
    {
        SqlCon sqlCon = new  SqlCon();
        try {
			ps = new String(ps.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String update = "insert into user (username, password, gender, entryYear, ps) values (\""
        + username + "\", \"" + password + "\", " + gender + ", " + entryYear + ", \"" + ps + "\");";
        sqlCon.executeUpdate(update);
        update = "insert into sedb.interest (username) values (\"" + username + "\");";
        sqlCon.executeUpdate(update);
        update = "insert into sedb.relationship (username, following, follower) values (\"" + username + "\", '', '');";
        sqlCon.executeUpdate(update);
        return "signup_success";
    }
    
    @RequestMapping("/signup")
    public String result() { return "signup"; }
}
