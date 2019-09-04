package com.stylefeng.guns.modular.system.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.controller.BaseController;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
    @RequestMapping("/welcome")
    public String console() {
        return "/frame/welcome.html";
    }

    @RequestMapping("/theme")
    public String theme() {
        return "/frame/theme.html";
    }

    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return "/frame/password.html";
    }

    @RequestMapping("/msg")
    public String message() {
        return "/frame/msg.html";
    }

    /**
     * 通用的树列表选择器
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:59 PM
     */
    @RequestMapping("/commonTree")
    public String deptTreeList(@RequestParam("formName") String formName, @RequestParam("formId") String formId,
            @RequestParam("treeUrl") String treeUrl, Model model) {
        try {
            model.addAttribute("formName", URLDecoder.decode(formName, "UTF-8"));
            model.addAttribute("formId", URLDecoder.decode(formId, "UTF-8"));
            model.addAttribute("treeUrl", URLDecoder.decode(treeUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }

        return "/common/tree_dlg.html";
    }
}
