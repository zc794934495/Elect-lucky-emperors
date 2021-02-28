package com.daimao.controller;


import com.daimao.model.Award;
import com.daimao.model.Member;
import com.daimao.model.Setting;
import com.daimao.model.User;
import com.daimao.service.AwardService;
import com.daimao.service.MemberService;
import com.daimao.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    private SettingService settingService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private MemberService memberService;

    /**
     * 进入抽奖设置页面，初始化接口，返回页面所有需要的属性
     * setting对象中的属性
     * setting对象目前没有的属性：user（用户信息）。
     *                          awards（奖项列表：根据setting_id查）
     *                          members（抽奖人员列表：根据setting_id查）
     * @param session
     * @return
     */
    @GetMapping("/query")
    public Object query(HttpSession session) { //已经登录，可以使用session
        //获取session中的user
        User user = (User) session.getAttribute("user");
        //根据userid查询setting信息
        Setting setting = settingService.queryByUserId(user.getId());
        setting.setUser(user);
        //根据setting_id查询award列表
        List<Award> awards = awardService.queryBySettingId(setting.getId());
        setting.setAwards(awards);
        //根据setting_id查询member列表
        List<Member> members = memberService.queryBySettingId(setting.getId());
        setting.setMembers(members);
        return setting;
    }

    @GetMapping("/update")
    public Object update(Integer batchNumber,HttpSession session) {
        User user = (User) session.getAttribute("user");
        int n = settingService.update(batchNumber,user.getId());
        return null;
    }
}
