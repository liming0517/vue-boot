package com.temp.permission.mapper;

import com.temp.common.mapper.BaseMapper;
import com.temp.permission.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    User queryOneByPhone(String phone);
    User queryOneByToken(String token);
    List<User> queryListByRoleId(Integer roleId);
    List<User> queryListByRole(String role_name);
    User queryOne1(String token);
    User queryOne2(String user_phone);
}
