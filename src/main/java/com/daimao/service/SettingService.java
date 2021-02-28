package com.daimao.service;


import com.daimao.mapper.SettingMapper;
import com.daimao.model.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    private SettingMapper settingMapper;

    /**
     * 根据用户id查询抽奖设置
     */
    public Setting queryByUserId(Integer id) {
        return settingMapper.selectByUserId(id);
    }

    /**
     * 通过用户id修改每次抽奖人数
     * @param batchNumber 每次抽奖人数
     * @param userId 用户id
     */
    public int update(Integer batchNumber, Integer userId) {
        return settingMapper.update(batchNumber,userId);
    }
}
