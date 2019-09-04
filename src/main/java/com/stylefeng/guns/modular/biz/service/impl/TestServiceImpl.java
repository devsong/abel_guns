package com.stylefeng.guns.modular.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.common.annotion.DataSource;
import com.stylefeng.guns.common.constant.DSEnum;
import com.stylefeng.guns.common.persistence.dao.logs.TestMapper;
import com.stylefeng.guns.common.persistence.model.logs.Test;
import com.stylefeng.guns.modular.biz.service.ITestService;

/**
 * 测试服务
 */
@Component
@DataSource(DSEnum.DATA_SOURCE_LOGS)
public class TestServiceImpl implements ITestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public void testBiz() {
        Test test = testMapper.selectByPrimaryKey(1);
        test.setId(22);
        testMapper.insert(test);
    }

    @Override
    public void testGuns() {
        Test test = testMapper.selectByPrimaryKey(1);
        test.setId(33);
        testMapper.insert(test);
    }

    @Override
    @Transactional
    public void testAll() {
        testBiz();
        testGuns();
    }

}
