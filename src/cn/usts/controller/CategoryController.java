package cn.usts.controller;

import cn.usts.pojo.CategoryEntity;
import cn.usts.pojo.vo.CategoryVO;
import cn.usts.service.CategoryService;
import cn.usts.util.JSONBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/cate")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/addCate")
    @ResponseBody
    public JSONBean add(@RequestBody CategoryVO categoryVO) {
        categoryService.addCategory(categoryVO);
        return new JSONBean("1", "类别保存成功");
    }

    @RequestMapping("/delete/id")
    @ResponseBody
    public JSONBean delete(@RequestParam Integer id) {
        categoryService.delete(CategoryEntity.builder().id(id).build());
        return new JSONBean("0", "success");
    }

    @RequestMapping("/query")
    @ResponseBody
    public JSONBean queryAll() {
        return new JSONBean("success", categoryService.queryAll());
    }

}
