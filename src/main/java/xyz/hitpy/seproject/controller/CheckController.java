package xyz.hitpy.seproject.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.hitpy.seproject.model.CheckPreview;
import xyz.hitpy.seproject.mysqlcon.SqlCon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class CheckController {
    @RequestMapping(value = "apply")
    public void joinActivity(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = (String) request.getSession().getAttribute("username");
        String aidStr = request.getParameter("aid");
        String reason = request.getParameter("reason");
        String contact = request.getParameter("contact");
        String checku = "";
        String tag = null;
        SqlCon c = new SqlCon();
        JSONObject json = new JSONObject();
        // 确认活动是否存在
        String party = null;
        Timestamp created_ts = null;
        ResultSet res = c.executeQuery("select * from sedb.activity where aid=" + aidStr + " limit 0, 1");
        try {
            if (res != null && res.first()) {
                created_ts = res.getTimestamp("created");
                party = res.getString("party");
                checku = res.getString("checku");
                tag = res.getString("tag");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error at 126 of addActivity in ActivityController.java");
        }

        if (created_ts == null) {
            json.put("feedback", "无法参加");
            out.print(json);
            return;
        }

        // 活动存在，确认当前用户是否参加活动
        int i = party.indexOf(username);
        if (i != -1) {
            json.put("feedback", "参加过这个活动了");
            out.print(json);
            return;
        }

        // 确认当前用户是否发出过申请并且未被审查
        i = checku.indexOf(username);
        if (i != -1) {
            json.put("feedback", "发出的申请还没被处理，请耐心等待");
            out.print(json);
            return;
        }

        // 用户没有参加过这个活动，也没有发出过申请
        String upd = "insert into sedb.tocheck(joiner, aid, reason, contact) values(\"" + username +
                "\",\"" + aidStr + "\",\"" + reason + "\", \"" + contact + "\");";
        c.executeUpdate(upd);
        int id = -1;
        res = c.executeQuery("SELECT id FROM sedb.tocheck WHERE joiner = " + "\"" + username +
                "\" and aid=" + aidStr + " limit 0, 1");
        try {
            if (res != null && res.first()) {
                id = res.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error at 193 of addActivity in ActivityController.java");
        }
        // 申请已加入数据库，现在把申请的id和申请者加入到activity表中
        upd = "update sedb.activity set checkid = CONCAT(checkid, ' ', \"" + id +
                "\")," + "checku = CONCAT(checku, ' ', \"" + username + "\") where aid = \"" + aidStr + "\";";
        c.executeUpdate(upd);
        json.put("feedback", "申请成功！");
        // 更新interest的权重
     // interest的权重变化
        String[] tagLst = tag.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("update sedb.interest set ");
        for (i = 0; i < tagLst.length; i++)
        {
            sb.append(tagLst[i]);
            sb.append("=");
            sb.append(tagLst[i]);
            sb.append("+5");
            if (i != tagLst.length - 1)
                sb.append(",");   
        }
        sb.append(" where username=\"" + username + "\";");
        c.executeUpdate(sb.toString());
        out.print(json);
    }

    @RequestMapping(value = "checkJoiner")
    public String checkJoiner(@RequestParam("aid") String aid, @RequestParam("aname") String aname,
                              ModelMap model, HttpServletResponse response, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
        String anameR = new String(aname.getBytes("ISO-8859-1"), "UTF-8");
        SqlCon c = new SqlCon();
        String query = "select  checkid from sedb.activity where aid = " + aid + " limit 1;";
        ResultSet res = c.executeQuery(query);
        String checkid = null;
        if (res != null && res.first()) {
            checkid = res.getString("checkid");
        }
        if (checkid == null) {
            return "404";
        }
        List<String> checkids = Arrays.asList((checkid.split(" ")));
        checkids = checkids.subList(1, checkids.size());
        Iterator<String> it = checkids.iterator();
        ArrayList<CheckPreview> checkList = new ArrayList<CheckPreview>();
        int i;
        while (it.hasNext()) {
            i = Integer.parseInt(it.next());
            query = "select * from sedb.tocheck where id = " + i + " limit 1;";
            res = c.executeQuery(query);
            while (res.next()) {
                checkList.add(new CheckPreview(res.getString("joiner"), res.getString("reason"),
                        res.getString("contact"), i));
            }
        }
        model.addAttribute("checklist", checkList);
        model.addAttribute("aname", anameR);
        return "check_joiner";
    }

    @RequestMapping("acceptjoin")
    public String acceptJoin(@RequestParam("id") int id, @RequestParam("aname") String aname,
                             RedirectAttributes attr) throws UnsupportedEncodingException, SQLException {
        SqlCon c = new SqlCon();
        String query = "select * from sedb.tocheck where id = " + id + " limit 1;";
        ResultSet res = c.executeQuery(query);
        int aid = -1;
        String joiner = null;
        if (res != null && res.first()) {
            aid = res.getInt("aid");
            joiner = res.getString("joiner");
        }
        if (joiner == null) {
            return "404";
        }
        // 准备从tocheck表删去这条请求
        String upd = "delete from sedb.tocheck where id = " + id + " limit 1;";
        c.executeUpdate(upd);
        // 从activity表中删去checkid中的id，删去checku中的joiner，在party添加joiner
        String checkid = null;
        String checku = null;
        String party = null;
        query = "select * from sedb.activity where aid = " + aid + " limit 1;";
        res = c.executeQuery(query);
        if (res != null && res.first()) {
            checkid = res.getString("checkid");
            checku = res.getString("checku");
            party = res.getString("party");
        }
        checkid = checkid.replaceFirst(" " + Integer.toString(id), "");
        checku = checku.replaceFirst(" " + joiner, "");
        party = party + " " + joiner;
        upd = "update sedb.activity set checkid=\"" + checkid + "\", checku=\"" + checku + "\", party=\""
                + party + "\" where aid = " + Integer.toString(aid) + " limit 1";
        c.executeUpdate(upd);
        return "redirect:checkJoiner?aname=" + aname + "&aid=" + aid;
    }

    @RequestMapping("declinejoin")
    public String declineJoin(@RequestParam("id") int id, @RequestParam("aname") String aname,
                              RedirectAttributes attr) throws UnsupportedEncodingException, SQLException {
        SqlCon c = new SqlCon();
        String query = "select * from sedb.tocheck where id = " + id + " limit 1;";
        ResultSet res = c.executeQuery(query);
        int aid = -1;
        String joiner = null;
        if (res != null && res.first()) {
            aid = res.getInt("aid");
            joiner = res.getString("joiner");
        }
        if (joiner == null) {
            return "404";
        }
        // 准备从tocheck表删去这条请求
        String upd = "delete from sedb.tocheck where id = " + id + " limit 1;";
        c.executeUpdate(upd);
        // 从activity表中删去checkid中的id，删去checku中的joiner
        String checkid = null;
        String checku = null;
        query = "select * from sedb.activity where aid = " + aid + " limit 1;";
        res = c.executeQuery(query);
        if (res != null && res.first()) {
            checkid = res.getString("checkid");
            checku = res.getString("checku");
        }
        checkid = checkid.replaceFirst(" " + Integer.toString(id), "");
        checku = checku.replaceFirst(" " + joiner, "");
        upd = "update sedb.activity set checkid=\"" + checkid + "\", checku=\"" + checku +
                "\" where aid = " + Integer.toString(aid) + " limit 1";
        c.executeUpdate(upd);
        return "redirect:checkJoiner?aname=" + aname + "&aid=" + aid;
    }
}
