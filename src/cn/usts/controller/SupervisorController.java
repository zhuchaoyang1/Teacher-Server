package cn.usts.controller;

import cn.usts.pojo.Supervisor;
import cn.usts.pojo.SupervisorTemp;
import cn.usts.pojo.SysUser;
import cn.usts.pojo.TeachingTask;
import cn.usts.service.SupervisorService;
import cn.usts.service.SupervisorTempService;
import cn.usts.service.TeachingTaskService;
import cn.usts.service.UserService;
import cn.usts.util.JSONBean;
import cn.usts.util.session.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;
    @Autowired
    private UserService userService;
    @Autowired
    private SupervisorTempService supervisorTempService;

    @RequestMapping("/save")
    @ResponseBody
    public JSONBean save(@RequestBody Supervisor supervisor) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = supervisor.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        SysUser user = userService.queryById(uId).get(0);

        Integer[] canIdsTemp = new Integer[0];

        if (user != null) {
            supervisor.setUId(uId);
            supervisor.setCollege(user.getCollege());
            supervisor.setMarjor(user.getMajor());

            if (!supervisor.getIsAllCanLook()) {
                // 给特定人看
                canIdsTemp = supervisor.getCanLookPersonIdsArray();
                // 构造String
                supervisor.setCanLookPersonIds(Arrays.toString(canIdsTemp));
                // 获取特定人名字数组
                List<String> users = new ArrayList<>();
                for (Integer usCurrId : supervisor.getCanLookPersonIdsArray()) {
                    SysUser sysUser = userService.queryRealNameById(new SysUser(usCurrId));
                    if (sysUser != null) {
                        users.add(sysUser.getRealName());
                    }
                }
                supervisor.setCanLookPersonNames(
                        users.stream().collect(Collectors.joining(","))
                );
            }
            supervisorService.save(supervisor);

            if (!supervisor.getIsAllCanLook()) {
                // 维护中间表
                Arrays.stream(canIdsTemp).forEach(var -> {
                    SupervisorTemp supervisorTemp =
                            SupervisorTemp.builder().uId(var)
                                    .sId(supervisor.getId()).build();
                    supervisorTempService.save(supervisorTemp);
                });
            }

            return new JSONBean("0", "success");
        } else {
            return new JSONBean("-1", "NoAuth");
        }

    }

    @RequestMapping("/query")
    @ResponseBody
    public JSONBean query(@RequestBody Supervisor supervisor) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = supervisor.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        supervisor.setUId(uId);
        // 对PageOrSize构造学院信息和专业信息
        SysUser user = userService.queryById(uId).get(0);

        supervisor.setCollege(user.getCollege());
        supervisor.setMarjor(user.getMajor());
        supervisor.setRole(user.getRole());

        Map<String, Object> map = new HashMap<>();

        map.put("count", supervisorService.getCount(supervisor));
        map.put("list", supervisorService.queryAll(supervisor));

        return new JSONBean("0", map);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONBean delete(@RequestBody Supervisor supervisor, HttpSession session) {
        String msg = "", data = "";

        // 获取对应用户Session中保存的用户ID
        String accessToken = supervisor.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uSubmitId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        if (uSubmitId != supervisor.getUId()) {
            // 没有权限删除
            return new JSONBean("error", "NoAUth");
        }

        // 删除文件
        String logoRealPath = session.getServletContext().getRealPath(supervisor.getFilepath());
        File file = new File(logoRealPath);
        if (file.exists()) {
            file.delete();
        }

        // 删除数据库记录
        supervisorService.delete(supervisor);
        // 删除中间表记录
        SupervisorTemp sTemp = new SupervisorTemp();
        sTemp.setSId(supervisor.getId());
        supervisorTempService.delete(sTemp);
        return new JSONBean("0", "success");
    }

    @RequestMapping("/queryBy")
    @ResponseBody
    public JSONBean queryById(@RequestBody Supervisor supervisor) {
        List<Supervisor> lists = supervisorService.queryByBean(supervisor);
        List<Supervisor> lists2 = new ArrayList<>();
        Supervisor supervisor1 = null;
        if (lists.size() == 1) {
            supervisor1 = lists.get(0);
            if (!supervisor1.getIsAllCanLook()) {
                lists2 = supervisorService.queryByBean2(supervisor);
            } else {
                lists2.add(supervisor1);
            }
        }
        return new JSONBean("0", lists2);
    }

    @RequestMapping("/pd")
    @ResponseBody
    public JSONBean pd(@RequestBody Supervisor supervisor) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = supervisor.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        if (supervisor.getUId() == uId) {
            return new JSONBean("0", "success");
        }

        return new JSONBean("error", "NoAuth");
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONBean updateData(@RequestBody Supervisor supervisor) {
        // TODO 删除该ID中间关系表
        supervisorTempService.delete(new SupervisorTemp(supervisor.getId()));

        if (supervisor.getIsAllCanLook()) {
            // TODO 让原有IDSArray清空
            supervisor.setCanLookPersonIds("");
            supervisor.setCanLookPersonNames("");

        } else {
            // 给特定人看
            Integer[] canIdsTemp = supervisor.getCanLookPersonIdsArray();
            // 构造String
            supervisor.setCanLookPersonIds(Arrays.toString(canIdsTemp));

            // 获取特定人名字数组
            List<String> users = new ArrayList<>();
            for (Integer usCurrId : supervisor.getCanLookPersonIdsArray()) {
                SysUser sysUser = userService.queryRealNameById(new SysUser(usCurrId));
                if (sysUser != null) {
                    users.add(sysUser.getRealName());
                }
            }
            supervisor.setCanLookPersonNames(
                    users.stream().collect(Collectors.joining(","))
            );

            // TODO 重新维护中间表关系
            Arrays.stream(canIdsTemp).forEach(var -> {
                SupervisorTemp supervisorTemp =
                        SupervisorTemp.builder().uId(var)
                                .sId(supervisor.getId()).build();
                supervisorTempService.save(supervisorTemp);
            });

        }

        // TODO 更新主表
        supervisorService.update(supervisor);

        return new JSONBean("0", "success");
    }

    @RequestMapping("/file/delete/relation")
    @ResponseBody
    public JSONBean deleteFileAndFileData(@RequestBody Supervisor supervisor, HttpSession session) {
        if (!StringUtils.isEmpty(supervisor.getFilepath())) {
            // 删除文件
            String realPath = session.getServletContext().getRealPath(supervisor.getFilepath());
            File file = new File(realPath);
            if (file.exists()) {
                // 文件存在  删除文件
                file.delete();
            }
        }
        // 删除关系
        supervisorService.updateFile(supervisor);
        return new JSONBean("0", "success");
    }

}
