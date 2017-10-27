package xyz.hitpy.seproject.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.hitpy.seproject.mysqlcon.SqlCon;

/**
 * @author skill
 * Handle request about signin.
 */
@Controller
public class SigninController {
    @RequestMapping("/ifUserExist")
    @ResponseBody
    public Object ifUserExist( @RequestParam("username") String username)
    {
        SqlCon sqlCon = new SqlCon();
        String query = "select * from useraccount where username = " + username + " limit 1;";
        ResultSet rs = sqlCon.executeQuery(query);
        try {
            while(rs.next())
            {
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("error in methord 'ifUserExist' of class 'SigninController'");
            e.printStackTrace();
        }
        return 0;
    }
    
    @RequestMapping("/addUser")
    public String addUser(@RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("gender") int gender, @RequestParam("entryYear") int entryYear, @RequestParam("ps") String ps)
    {
        SqlCon sqlCon = new  SqlCon();
        String update = "insert into useraccount (username, password, gender, entryYear, ps) values (\""
        + username + "\", \"" + password + "\", " + gender + ", " + entryYear + "\"" + ps + "\");";
        sqlCon.executeUpdate(update);
        return "signin_success";
    }
}
