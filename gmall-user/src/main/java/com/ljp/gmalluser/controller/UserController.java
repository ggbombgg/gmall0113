package com.ljp.gmalluser.controller;


import bean.UmsMember;
import bean.UmsMemberReceiveAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/index")
    public String index(){
        return "hello index";
    }

    @ResponseBody
    @GetMapping("getAllUser")
    public List<UmsMember> getAllUser(){
        List<UmsMember> umsMemberList = userService.getAllUser();
        return umsMemberList;
    }

    @ResponseBody
    @GetMapping("getReceiveAddressByMemberId")
    public List<UmsMemberReceiveAddress> getReceiveAddressById(String memberId){
        List<UmsMemberReceiveAddress> UmsMemberReceiveAddressList = userService.getReceiveAddressByMemberId(memberId);
        return UmsMemberReceiveAddressList;
    }
}
