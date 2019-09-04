package com.stylefeng.guns.modular.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.annotion.log.BussinessLog;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.page.PageReq;
import com.stylefeng.guns.common.persistence.model.logs.OperationLog;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.biz.bo.QueryLogBo;
import com.stylefeng.guns.modular.biz.service.ISystemLogService;
import com.stylefeng.guns.modular.system.convert.LogWarpper;

/**
 * 日志管理的控制器
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController {
    private static String PREFIX = "/system/log/";

    @Autowired
    private ISystemLogService systemLogService;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "log.html";
    }

    /**
     * 查询操作日志列表
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(QueryLogBo queryLogBo) {
        PageReq params = defaultPage();
        queryLogBo.setPageReq(params);
        List<OperationLog> result = systemLogService.getOperationLogs(queryLogBo);
        return packForBT(result);
    }

    /**
     * 查询操作日志详情
     */
    @RequestMapping("/detail/{id}")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object detail(@PathVariable Long id) {
        OperationLog operationLog = systemLogService.getOperationLogDetail(id);
        Map<String, Object> stringObjectMap = BeanKit.beanToMap(operationLog);
        return super.warpObject(new LogWarpper(stringObjectMap));
    }

    /**
     * 清空日志
     */
    @BussinessLog(value = "清空业务日志")
    @RequestMapping("/delLog")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object delLog() {
        systemLogService.truncateBizLog();
        return SUCCESS_TIP;
    }
}
