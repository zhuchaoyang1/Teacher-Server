package cn.usts.controller;

import cn.usts.pojo.Instructs;
import cn.usts.pojo.page.PageOrSize;
import cn.usts.service.InstructService;
import cn.usts.util.JSONBean;
import cn.usts.util.session.SessionContext;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/instruct")
public class InstructController {

    @Resource
    private InstructService instructService;

    @RequestMapping("/add")
    @ResponseBody
    public JSONBean save(@RequestBody Instructs instructs) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = instructs.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        instructs.setSenderId(String.valueOf(uId));

        DateTime dateTime = new DateTime();
        instructs.setNowDate(dateTime.toString("yyyy-MM-dd HH:mm:ss"));

        instructService.save(instructs);
        return new JSONBean("success", null);
    }

    @RequestMapping("/query/me")
    @ResponseBody
    public JSONBean queryAllAboutMe(PageOrSize pageOrSize) {
        // 获取对应用户Session中保存的用户ID
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(pageOrSize.getUserToken());
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        Instructs instructs = Instructs.builder()
                .uId(uId).pageNo(pageOrSize.getStart()).pageSize(pageOrSize.getSize())
                .build();
        List<Instructs> instructsList = instructService.queryAboutMe(instructs).stream()
                .map(item -> {
                    item.setRealName(item.getSysUser().getRealName());
                    item.setRole(item.getSysUser().getRole());
                    return item;
                }).collect(Collectors.toList());

        int count = instructService.getAboutMeSize(instructs);
        Map<String, Object> result = new HashMap<>(2);
        result.put("size", count);
        result.put("instructList", instructsList);
        return new JSONBean("success", result);
    }

    @RequestMapping("/findBy")
    @ResponseBody
    public JSONBean findById(Integer id) {
        Optional<Instructs> optionalInstructs = instructService.findById(id);
        return new JSONBean("success", optionalInstructs.isPresent() ?
                optionalInstructs.get() : new Instructs());
    }

    @RequestMapping("/sign")
    @ResponseBody
    public JSONBean signInstruction(Integer id) {
        Instructs instructs = Instructs.builder()
                .id(Long.valueOf(id)).build();
        instructService.update(instructs);
        return new JSONBean("success", null);
    }
}
