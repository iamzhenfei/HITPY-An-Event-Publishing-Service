package xyz.hitpy.seproject.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.hitpy.seproject.model.ActivityPreview;
import xyz.hitpy.seproject.service.SearchService;

@Controller
public class SearchController {
	@RequestMapping(value = "/search",method = RequestMethod.GET)
	public String searchByName(HttpServletRequest request, HttpServletResponse
			response,ModelMap model,@RequestParam("name") String names){
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
