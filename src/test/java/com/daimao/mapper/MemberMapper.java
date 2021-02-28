package com.daimao.mapper;

import com.daimao.base.BaseMapper;
import com.daimao.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}