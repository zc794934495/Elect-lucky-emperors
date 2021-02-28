package com.daimao.service;


import com.daimao.mapper.MemberMapper;
import com.daimao.mapper.SettingMapper;
import com.daimao.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private SettingMapper settingMapper;

    /**
     * 根据settingid查询抽奖人员
     */
    public List<Member> queryBySettingId(Integer id) {
        return memberMapper.selectBySettingId(id);
    }

    /**
     * 通过userid获得settingid，再添加抽奖人员
     */
    public int add(Member member, Integer userId) {
        Integer settingId = settingMapper.selectIdByUserId(userId);
        member.setSettingId(settingId);
        return memberMapper.insertSelective(member);
    }

    /**
     * 通过主键修改抽奖人员信息
     */
    public int update(Member member) {
        return memberMapper.updateByPrimaryKeySelective(member);
    }

    /**
     * 通过主键删除抽奖人员
     */
    public int delete(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }
}
