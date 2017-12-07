package xyz.hitpy.seproject.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.hitpy.seproject.model.ActivityPreview;
import xyz.hitpy.seproject.mysqlcon.SqlCon;

@Controller
public class RecommendController {
    @RequestMapping("recommend")
    public String recommend(HttpServletRequest request, HttpServletResponse response, ModelMap model)
    {
        String username = (String)request.getSession().getAttribute("username");
        if (username == null) { return "404"; }
        ArrayList<ActivityPreview> activities = new ArrayList<ActivityPreview>();
        int allnum = 8; // 推荐的总数
        Set<Integer> aSet = new HashSet<Integer>();
        
        // 根据username的interest类型
        SqlCon c = new SqlCon();
        ResultSet res = c.executeQuery("select * from sedb.interest where username=\"" + username + "\" limit 1;");
        String[] tagStrArr = new String[]{"xuexi", "qinggan", "youxi", "yundong", "dianying", "chifan", 
                "lvyou", "maimaimai", "shuma", "xiju", "yinyue", "qita"};
        List<Integer> ilist = new ArrayList<Integer>();
        try {
            if (res != null && res.first())
            {
                for (int i = 0; i < tagStrArr.length; i++)
                {
                    ilist.add(res.getInt(tagStrArr[i]));
                }
            }
        } catch (SQLException e) {
            System.out.println("error at sedb.interest in recommend of RecommendController");
            e.printStackTrace();
        }
        int index = ilist.indexOf(Collections.max(ilist));  // 取得拥有最大权重的tag所在的索引
        String mtag = tagStrArr[index]; // 取得最大权重的tag名
        res = c.executeQuery("select * from sedb.activity where tag like '%" + mtag +
                "' and username!='" + username +
                "' and not party like '%" + username + 
                "' and not checku like '%" + username + "' order by aid desc limit 0, " + allnum / 2);
        try {
            while(res != null && res.next())
            {
                aSet.add(res.getInt("aid"));
                activities.add(new ActivityPreview(res.getInt("aid"), res.getString("name"), 
                        res.getString("time"), res.getString("location"), res.getString("username")));
            }
        } catch (SQLException e) {
            System.out.println("error at 根据tag取活动 in recommend of RecommendController");
            e.printStackTrace();
        }
        
        // 根据username的following发布了什么活动
        res = c.executeQuery("select following from sedb.relationship where username=\"" + username + "\" limit 1;");
        String followingStr = null;
        try {
            if (res != null && res.first())
            {
                followingStr = res.getString("following");
            }
        } catch (SQLException e) {
            System.out.println("error at sedb.relationship in recommend of RecommendController");
            e.printStackTrace();
        }
        if (followingStr != null)
        {
            List<String> followingLst = Arrays.asList(followingStr.split(" "));
            followingLst = followingLst.subList(1, followingLst.size());
            if (followingLst.size() > 0)
            {
                Set followingSet = new HashSet();
                Random random = new Random();
                int r = 0;
                for (int i = allnum / 2; i > 0; i--)
                {
                    // 生成随机的索引
                    r = random.nextInt(followingLst.size() - 1);
                    // 加入随机的人名
                    followingSet.add(followingLst.get(r));
                }
                if (followingSet.size() > 0)
                {
                    List<String> sfLst = new ArrayList<String>(followingSet);
                    StringBuilder sb = new StringBuilder();
                    sb.append("select * from sedb.activity where ");
                    for (int i = 0; i < sfLst.size(); i++)
                    {
                        sb.append(" username='");
                        sb.append(sfLst.get(i));
                        sb.append("' ");
                        if (i != sfLst.size() - 1)
                        {
                            sb.append(" or ");
                        }
                    }
                    sb.append(" order by aid desc limit 0," + allnum / 2);
                    res = c.executeQuery(sb.toString());
                    try {
                        while(res != null && res.next())
                        {
                            if (!aSet.contains(res.getInt("aid")))
                            {
                                aSet.add(res.getInt("aid"));
                                activities.add(new ActivityPreview(res.getInt("aid"), res.getString("name"), 
                                        res.getString("time"), res.getString("location"), res.getString("username")));
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("error at 取关注者的推荐  in recommend of RecommendController");
                        e.printStackTrace();
                    }
                }
            }
        }
        model.addAttribute("activitylist", activities);
        return "recommend";
    }
}
