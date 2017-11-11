package xyz.hitpy.seproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActivityController {
    @RequestMapping("createActivity")
    public String createActivityPage() { return "create_activity"; }
    
    public String addActivity(@RequestParam("title") String title, @RequestParam("content") String content,
            HttpServletResponse response,HttpServletRequest request)
    {
        
        return "show_activity";
    }
}
