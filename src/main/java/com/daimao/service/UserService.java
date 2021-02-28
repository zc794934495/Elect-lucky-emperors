package com.daimao.service;


import com.daimao.exception.AppException;
import com.daimao.mapper.SettingMapper;
import com.daimao.mapper.UserMapper;
import com.daimao.model.Setting;
import com.daimao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SettingMapper settingMapper;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    @Value("${user.head.local-path}")
    private String headLocalPath;

    @Value("${user.head.remote-path}")
    private String headRemotePath;

    /**
     * 保存头像
     */
    public String saveHead(MultipartFile headFile) {
        Date now = new Date();
        String dirUri = "/" + DATE_FORMAT.format(now);
        File dir = new File(headLocalPath + dirUri);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String headName = UUID.randomUUID().toString()
                + headFile.getOriginalFilename().substring(headFile.getOriginalFilename().lastIndexOf("."));
        String uri = dirUri + "/" + headName;
        try {
            headFile.transferTo(new File(headLocalPath + uri));
        } catch (IOException e) {
            throw new AppException("REG001","上传用户头像出错！");
        }
        return headLocalPath + uri;
    }

    /**
     * 注册用户
     * @param user
     */
    @Transactional
    public void register(User user) {
        int n = userMapper.insertSelective(user);
        if(n == 0) {
            throw new AppException("REG002","用户名重复！");
        }
        //插入setting
        Setting setting = new Setting();
        setting.setUserId(user.getId());
        setting.setBatchNumber(1);//每次抽奖数量
        int m = settingMapper.insertSelective(setting);
    }

    /**
     * 通过用户名查找用户
     */
    public User queryByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
