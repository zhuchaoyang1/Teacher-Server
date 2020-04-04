package cn.usts.controller;

import cn.usts.pojo.Leavel;
import cn.usts.service.LeavelService;
import cn.usts.util.JSONBean;
import com.alibaba.druid.stat.JdbcDataSourceStatMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/leavel")
public class LeavelController {

    @Autowired
    private LeavelService leavelService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public JSONBean queryAll() {
        return new JSONBean("0", leavelService.queryAll());
    }


    @RequestMapping("/save")
    @ResponseBody
    public JSONBean save(@RequestBody List<Map<String, Object>> lists) {
        // 进行写库操作
        lists.forEach(var -> {
            String father = String.valueOf(var.get("key"));
            List<Map<String, Object>> leavels = (List<Map<String, Object>>) var.get("value");
            for (Map<String, Object> map : leavels) {
                if (map.containsKey("id") && map.get("id") == null) {
                    // 避免重复添加
                    Leavel leavel = Leavel.builder().faleavel(father).sonleavel(String.valueOf(map.get("sonleavel"))).build();
                    leavelService.save(leavel);
                }
            }
        });
        return new JSONBean("0", leavelService.queryAll());
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONBean deleteTag(@RequestBody Leavel leavel) {
        leavelService.delete(leavel);
        return new JSONBean("0", null);
    }
}
