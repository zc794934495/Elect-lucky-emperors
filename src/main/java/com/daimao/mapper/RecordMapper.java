package com.daimao.mapper;

import com.daimao.base.BaseMapper;
import com.daimao.model.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    int batchInsert(@Param("awardId") Integer awardId, @Param("memberIdList") List<Integer> memberIdList);

    int deleteByMemberId(Integer id);

    int deleteByAwardId(Integer id);

    int deleteByUserId(Integer id);
}