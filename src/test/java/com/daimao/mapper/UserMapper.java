package com.daimao.mapper;

import com.daimao.base.BaseMapper;
import com.daimao.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}