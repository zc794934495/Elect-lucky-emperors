package com.daimao.service;


import com.daimao.mapper.AwardMapper;
import com.daimao.mapper.SettingMapper;
import com.daimao.model.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardService {
    @Autowired
    private AwardMapper awardMapper;
    @Autowired
    private SettingMapper settingMapper;

    /**
     * 根据setting_id查询奖项
     */
    public List<Award> queryBySettingId(Integer id) {
        return awardMapper.selectBySettingId(id);
    }

    /**
     * 通过userid查询settingid，给奖项添加settingid，最后添加奖项
     * @param award 奖项
     * @param userId 当前用户id
     */
    public int add(Award award, Integer userId) {
        Integer settingId = settingMapper.selectIdByUserId(userId);
        award.setSettingId(settingId);
        return awardMapper.insertSelective(award);
    }

    /**
     * 根据奖项主键修改奖项参数
     */
    public int update(Award award) {
        return awardMapper.updateByPrimaryKeySelective(award);
    }

    /**
     * 根据奖项主键删除奖项
     */
    public int delete(Integer id) {
        return awardMapper.deleteByPrimaryKey(id);
    }
}
