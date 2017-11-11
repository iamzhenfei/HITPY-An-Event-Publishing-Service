package xyz.hitpy.seproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActivityController {
    @RequestMapping("/createActivity")
    public String createActivity() { return "create_activity"; }
    
    @RequestMapping("/addActivity")
    public void addActivity(@RequestParam("content") String content)
    {
        System.out.println(content);
        System.out.println("完毕");
    }
}
