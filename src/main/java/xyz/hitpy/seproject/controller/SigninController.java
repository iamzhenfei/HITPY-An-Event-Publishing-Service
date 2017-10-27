package xyz.hitpy.seproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
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
    
    @RequestMapping("/checks")//这里就是checks.do
    public void logindeal(HttpServletRequest request, HttpServletResponse response)throws IOException{
        PrintWriter out=response.getWriter();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //注意这里的request.getParameter("username")取的是data里面的那个json对象的username,而非<input>里面那个，同理password也一样。
        JSONObject json=new JSONObject();
        if(username.equals("admin")&&password.equals("admin")){//这里没有用数据库验证
            json.put("result","success");
        }else{
            json.put("result","error");
        }
        out.print(json);
        }
    
    @RequestMapping("/result")
    public String result()
    {
        return "result";
    }
}
