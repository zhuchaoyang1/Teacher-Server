package cn.usts.controller;

import cn.usts.util.JSONBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录会话超期
 * @Author: ${朱朝阳}
 * @Date: 2019/7/18 21:19
 */

@Controller
@RequestMapping("/session")
public class SessionOutController {

    @RequestMapping("/out")
    @ResponseBody
    public JSONBean sessionOut(){
        return new JSONBean("session","SessionOut");
    }

}
