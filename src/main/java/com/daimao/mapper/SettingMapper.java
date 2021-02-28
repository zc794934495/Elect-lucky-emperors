package com.daimao.mapper;

import com.daimao.base.BaseMapper;
import com.daimao.model.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SettingMapper extends BaseMapper<Setting> {
    Setting selectByUserId(Integer id);

    int update(@Param("batchNumber") Integer batchNumber, @Param("userId") Integer userId);

    Integer selectIdByUserId(Integer userId);
}