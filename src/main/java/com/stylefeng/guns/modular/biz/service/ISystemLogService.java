package com.stylefeng.guns.modular.biz.service;

import java.util.List;

import com.stylefeng.guns.common.persistence.model.logs.LoginLog;
import com.stylefeng.guns.common.persistence.model.logs.OperationLog;
import com.stylefeng.guns.modular.biz.bo.QueryLogBo;

public interface ISystemLogService {
    /**
     * 获取登录日志
     * @param queryLogBo
     * @return
     */
    List<LoginLog> getLoginLogs(QueryLogBo queryLogBo);

    /**
     * 获取登陆日志详情
     * @param id
     * @return
     */
    LoginLog getLoginLogDetail(Long id);

    /**
     * 保存登陆日志
     * @param loginLog
     * @return
     */
    boolean saveLoginLog(LoginLog loginLog);

    /**
     * 获取操作日志
     * @param queryLogBo
     * @return
     */
    List<OperationLog> getOperationLogs(QueryLogBo queryLogBo);

    /**
     * 查询详情
     * @param id
     * @return
     */
    OperationLog getOperationLogDetail(Long id);

    /**
     * 保存操作日志
     * @param operationLog
     * @return
     */
    boolean saveOperationLog(OperationLog operationLog);

    /**
     * 清空业务日志
     */
    void truncateBizLog();

    /**
     * 清空登陆日志
     */
    void truncateLoginLog();
}
