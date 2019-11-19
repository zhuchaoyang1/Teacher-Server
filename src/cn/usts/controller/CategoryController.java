package cn.usts.controller;

import cn.usts.pojo.vo.CategoryVO;
import cn.usts.service.CategoryService;
import cn.usts.util.JSONBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cate")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/addCate")
    public JSONBean add(@RequestBody CategoryVO categoryVO) {
        categoryService.addCategory(categoryVO);
        return new JSONBean("1", "类别保存成功");
    }

}
