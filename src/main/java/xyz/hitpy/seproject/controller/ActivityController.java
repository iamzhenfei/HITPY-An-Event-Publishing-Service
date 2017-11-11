package xyz.hitpy.seproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.hitpy.seproject.mysqlcon.SqlCon;

@Controller
public class ActivityController {
    @RequestMapping("create_activity")
    public String createActivityPage() { return "create_activity"; }
    
    public String addActivity(@RequestParam("title") String title, @RequestParam("content") String content,
            HttpServletResponse response,HttpServletRequest request)
    {
        
        return "show_activity";
    }
}
