package cn.usts.controller;

import cn.usts.pojo.SysUser;
import cn.usts.pojo.TeacherTemplete;
import cn.usts.service.TeacherTempleteService;
import cn.usts.service.UserService;
import cn.usts.util.JSONBean;
import cn.usts.util.session.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.swing.StringUIClientPropertyKey;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher/templete")
public class TeacherTempleteController {

    @Autowired
    private TeacherTempleteService teacherTempleteService;
    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    @ResponseBody
    public JSONBean save(@RequestBody TeacherTemplete teacherTemplete) {
        // 获取对应用户Session中保存的用户ID
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(teacherTemplete.getUserToken());
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        teacherTemplete.setUId(uId);

        List<SysUser> sysUserList = userService.queryById(uId);

        teacherTemplete.setCollege(sysUserList.get(0).getCollege());
        teacherTemplete.setUName(sysUserList.get(0).getRealName());

        return new JSONBean("0", teacherTempleteService.save(teacherTemplete));
    }

    /**
     * 查询本学院的所有  教学模板 [院长发布]
     *
     * @param teacherTemplete
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public JSONBean queryAll(@RequestBody TeacherTemplete teacherTemplete) {
        // 获取对应用户Session中保存的用户ID
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(teacherTemplete.getUserToken());
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));
        List<SysUser> sysUserList = userService.queryById(uId);

        String collegeName = sysUserList.get(0).getCollege();

        teacherTemplete.setCollege(collegeName);

        Map<String, Object> map = new HashMap<>();

        map.put("count", teacherTempleteService.queryCount(teacherTemplete));
        map.put("list", teacherTempleteService.queryAll(teacherTemplete));

        return new JSONBean("0", map);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONBean delete(@RequestBody TeacherTemplete teacherTemplete, HttpSession session) {
        // TODO 查看是否有权限
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(teacherTemplete.getUserToken());
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));
        teacherTemplete.setUId(uId);

        List<TeacherTemplete> list = teacherTempleteService.queryByBean(teacherTemplete);
        if (list.size() == 0) {
            return new JSONBean("err", "NoAuth");
        }

        if (!StringUtils.isEmpty(teacherTemplete.getFilepath())) {
            // 删除文件
            String logoRealPath = session.getServletContext().getRealPath(teacherTemplete.getFilepath());
            File file = new File(logoRealPath);
            if (file.exists()) {
                file.delete();
            }
        }
        teacherTempleteService.delete(teacherTemplete);
        return new JSONBean("0", "success");
    }


}
