package com.daimao.service;


import com.daimao.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 添加获奖记录
     * @param awardId 奖项id
     * @param memberIdList 获奖的抽奖人员id
     * @return
     */
    public int add(Integer awardId, List<Integer> memberIdList) {
        return recordMapper.batchInsert(awardId,memberIdList);
    }

    /**
     * 通过抽奖人员id删除获奖记录
     */
    public int deleteByMemberId(Integer id) {
        return recordMapper.deleteByMemberId(id);
    }

    /**
     * 通过奖项id删除获奖记录
     */
    public int deleteByAwardId(Integer id) {
        return recordMapper.deleteByAwardId(id);
    }

    /**
     * 通过用户id删除获奖记录
     */
    public int deleteByUserId(Integer id) {
        return recordMapper.deleteByUserId(id);
    }
}
