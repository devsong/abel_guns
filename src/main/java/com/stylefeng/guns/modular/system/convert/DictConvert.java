package com.stylefeng.guns.modular.system.convert;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.persistence.model.Dict;
import com.stylefeng.guns.modular.system.dto.DictDto;
import lombok.extern.slf4j.Slf4j;

/**
 * 字典列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
@Slf4j
public class DictConvert {

    public static DictDto convertDictDto(Dict dict) {
        DictDto dictDto = new DictDto();
        try {
            BeanUtils.copyProperties(dictDto, dict);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("convert dict error", e);
        }
        List<Dict> subDicts = ConstantFactory.me().findSubDict(dict.getId());
        String detail = subDicts.stream().map(s -> s.getNum() + ":" + s.getName()).collect(Collectors.joining(","));
        dictDto.setDetail(detail);
        return dictDto;
    }

    public static List<DictDto> convertDictDto(List<Dict> dicts) {
        if (CollectionUtils.isEmpty(dicts)) {
            return Lists.newArrayList();
        }
        List<DictDto> results = Lists.newArrayListWithCapacity(dicts.size());
        for (Dict dict : dicts) {
            results.add(convertDictDto(dict));
        }
        return results;
    }
}
