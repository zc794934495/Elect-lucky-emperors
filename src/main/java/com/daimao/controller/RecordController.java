package com.daimao.controller;


import com.daimao.model.User;
import com.daimao.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;


    @PostMapping("/add/{id}")
    private Object add(@PathVariable Integer id, @RequestBody List<Integer> memberIdList) {
        int n = recordService.add(id,memberIdList);
        return null;
    }

    @GetMapping("/delete/member")
    public Object deleteByMemberId(Integer id) {
        int n = recordService.deleteByMemberId(id);
        return null;
    }

    @GetMapping("/delete/award")
    public Object deleteByAwardId(Integer id) {
        int n = recordService.deleteByAwardId(id);
        return null;
    }

    @GetMapping("/delete/setting")
    public Object deleteBySetting(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int n = recordService.deleteByUserId(user.getId());
        return null;
    }
}
