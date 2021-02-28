package com.daimao.mapper;

import com.daimao.base.BaseMapper;
import com.daimao.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    List<Member> selectBySettingId(Integer id);
}