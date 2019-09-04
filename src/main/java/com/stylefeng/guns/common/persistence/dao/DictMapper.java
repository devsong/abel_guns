package com.stylefeng.guns.common.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.Dict;

import tk.mybatis.mapper.common.Mapper;

public interface DictMapper extends Mapper<Dict> {
    List<Dict> list(@Param("condition") String conditiion);
}