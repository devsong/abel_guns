package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.persistence.dao.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blackboard")
public class BlackboardController extends BaseController {
    @Autowired
    private NoticeMapper noticeMapper;

    @RequestMapping("")
    public String blackboard(Model model) {
        List<Map<String, Object>> notices = noticeMapper.list(null);
        model.addAttribute("noticeList", notices);
        return "/blackboard.html";
    }
}
