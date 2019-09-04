package com.stylefeng.guns.modular.system.factory;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.modular.system.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户创建工厂
 *
 * @author fengshuonan
 * @date 2017-05-05 22:43
 */
@Slf4j
public class UserFactory {

    public static User convertUserFromUserDto(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        try {
            BeanUtils.copyProperties(user, userDto);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("transfer userDto to user error [{}]", userDto.getId(), e);
        }
        return user;
    }

    public static UserDto convertUserDtoFromUser(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        try {
            BeanUtils.copyProperties(userDto, user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("transfer user to userDto error [{}]", userDto.getId(), e);
        }
        return userDto;
    }
}
