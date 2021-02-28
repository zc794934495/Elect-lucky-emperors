package com.daimao.mapper;

import com.daimao.base.BaseMapper;
import com.daimao.model.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AwardMapper extends BaseMapper<Award> {
    List<Award> selectBySettingId(Integer id);
}