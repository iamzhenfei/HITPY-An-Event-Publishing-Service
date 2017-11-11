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
import xyz.hitpy.seproject.service.LoginService;

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
		int autoLoginTimeout = -1;
		
		if (LoginService.validating(username, password)) {
			autoLoginTimeout = Integer.MAX_VALUE;
			model.addAttribute("username", username);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("password", password);
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
			return "index";
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
	    model.addAttribute("username","");
	    return "index";
	}  
}
