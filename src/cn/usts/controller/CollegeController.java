package cn.usts.controller;

import cn.usts.pojo.College;
import cn.usts.service.CollegeService;
import cn.usts.util.JSONBean;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @ResponseBody
    @RequestMapping("/add")
    public JSONBean addCollege(@RequestBody College college) {
        List<College> fromDataBase = collegeService.queryByBean(college);
        if (CollectionUtils.isEmpty(fromDataBase)) {
            collegeService.addCollege(college);
        } else {
            return new JSONBean("0", "该学院已存在库中");
        }
        return new JSONBean("0", "success");
    }

    @ResponseBody
    @RequestMapping("/query")
    public JSONBean queryAll() {
        return new JSONBean("0", collegeService.queryAll());
    }

    @ResponseBody
    @RequestMapping("/delete")
    public JSONBean deleteCollege(@RequestParam Integer id) {
        // CHECK
        if (CollectionUtils.isEmpty(collegeService.queryById(id))) {
            return new JSONBean("0", "error");
        }
        College college = College.builder()
                .id(id)
                .build();
        collegeService.delete(college);
        return new JSONBean("0", "success");
    }

}
