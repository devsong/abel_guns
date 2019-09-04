package com.stylefeng.guns.modular.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.stylefeng.guns.common.annotion.DataSource;
import com.stylefeng.guns.common.constant.DSEnum;
import com.stylefeng.guns.common.page.PageReq;
import com.stylefeng.guns.common.persistence.dao.logs.LoginLogMapper;
import com.stylefeng.guns.common.persistence.dao.logs.OperationLogMapper;
import com.stylefeng.guns.common.persistence.model.logs.LoginLog;
import com.stylefeng.guns.common.persistence.model.logs.OperationLog;
import com.stylefeng.guns.core.mutidatesource.DataSourceContextHolder;
import com.stylefeng.guns.modular.biz.bo.QueryLogBo;
import com.stylefeng.guns.modular.biz.service.ISystemLogService;

@Component
@DataSource(DSEnum.DATA_SOURCE_LOGS)
@Transactional
public class SystemLogServiceImpl implements ISystemLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public List<LoginLog> getLoginLogs(QueryLogBo queryLogBo) {
        PageReq pageReq = queryLogBo.getPageReq();
        PageHelper.offsetPage(pageReq.getOffset(), pageReq.getLimit());
        List<LoginLog> loginLogs = loginLogMapper.getLoginLogs(queryLogBo);
        return loginLogs;
    }

    @Override
    public LoginLog getLoginLogDetail(Long id) {
        return loginLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean saveLoginLog(LoginLog loginLog) {
        DataSourceContextHolder.setDataSourceType(DSEnum.DATA_SOURCE_LOGS);
        return loginLogMapper.insertSelective(loginLog) == 1;
    }

    @Override
    public void truncateLoginLog() {
        loginLogMapper.truncate();
    }

    @Override
    public List<OperationLog> getOperationLogs(QueryLogBo queryLogBo) {
        PageReq pageReq = queryLogBo.getPageReq();
        PageHelper.offsetPage(pageReq.getOffset(), pageReq.getLimit());
        List<OperationLog> operationLogs = operationLogMapper.getOperationLogs(queryLogBo);
        return operationLogs;
    }

    @Override
    public OperationLog getOperationLogDetail(Long id) {
        return operationLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean saveOperationLog(OperationLog operationLog) {
        return operationLogMapper.insertSelective(operationLog) == 1;
    }

    @Override
    public void truncateBizLog() {
        operationLogMapper.truncate();
    }
}
