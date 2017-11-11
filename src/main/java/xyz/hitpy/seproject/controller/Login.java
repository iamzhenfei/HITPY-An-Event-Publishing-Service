package xyz.hitpy.seproject.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.hitpy.seproject.mysqlcon.SqlCon;

@Controller
public class Login {
	@RequestMapping(value = "")
	public String indexPage(ModelMap model) {
		model.addAttribute("username", "");
		return "index";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		model.addAttribute("error", "");
		return "login";
	}
	@RequestMapping(value = "/create_activity", method = RequestMethod.GET)
	public String postPage(ModelMap model) {
		return "create_activity";

	}
	
	/**
     * @RequestParam 
     * @param username
     * @param password
     * @param model
     * @return
     */
	@RequestMapping("/loginnow")
	public String login(@RequestParam("username") String username, 
			@RequestParam("password") String password, ModelMap model,
			HttpServletResponse response,HttpServletRequest request) {
		boolean vali = false;
		int autoLoginTimeout = -1;
		SqlCon c = new SqlCon();
		String pass = null;
		ResultSet res = c.executeQuery("SELECT * FROM sedb.user WHERE USERNAME = " + "\"" + username + "\"");
		try {
			if (res != null && res.first()) {
				pass = res.getString("password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (password != null && username != null && pass != null && pass.equals(password)) {
			autoLoginTimeout = Integer.MAX_VALUE;
			vali = true;
		}
		if (vali) {
			model.addAttribute("username", username);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("password", password);
			return "index";
		}
		if(autoLoginTimeout > 0){  
		    //自动登录cookie  
		    Cookie userNameCookie = new Cookie("loginUserName", username);  
		    Cookie passwordCookie = new Cookie("loginPassword", password);  
		    userNameCookie.setMaxAge(autoLoginTimeout);  
		    userNameCookie.setPath("/");  
		    passwordCookie.setMaxAge(autoLoginTimeout);  
		    passwordCookie.setPath("/");  
		    response.addCookie(userNameCookie);  
		    response.addCookie(passwordCookie);  
		}  
		else
			model.addAttribute("error", "用户名错误或密码不存在");
			return "login";
		
	}
	
	@RequestMapping("/logout")  
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model){  
	    String username = (String) request.getSession().getAttribute("username");  
	    String password = (String) request.getSession().getAttribute("password");   
	    //删除登录cookie  
	    Cookie userNameCookie = new Cookie("loginUserName", username );  
	    Cookie passwordCookie = new Cookie("loginPassword", password);  
	    userNameCookie.setMaxAge(0);  
	    userNameCookie.setPath("/");  
	    passwordCookie.setMaxAge(0);  
	    passwordCookie.setPath("/");  
	    response.addCookie(userNameCookie);  
	    response.addCookie(passwordCookie);    
	    request.getSession().removeAttribute("username");  
	    request.getSession().removeAttribute("password");  
	    return "redirect:xxx";  
	}  
}
