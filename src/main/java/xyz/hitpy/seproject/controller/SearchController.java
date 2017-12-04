package xyz.hitpy.seproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.hitpy.seproject.model.ActivityPreview;
import xyz.hitpy.seproject.service.SearchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class SearchController {
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public String searchByName(HttpServletRequest request, HttpServletResponse
			response,ModelMap model,@RequestParam("name") String names){
		try {
			names = new String(names.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("error at getBytes in searchByName of SearchController");
		}
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username"); 
		model.addAttribute("username", username);
		ArrayList<ActivityPreview> activities = new ArrayList<ActivityPreview>();
		ResultSet res = SearchService.searchByName(names);
		try {
			while (res.next()) {
				activities.add(new ActivityPreview(res.getInt("aid"), res.getString("name"), 
						res.getString("time"), res.getString("location"), res.getString("username")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("activitylist",activities);
		return "search_result";
	}
}
