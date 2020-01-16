package com.ljp.gmalluser.service.impl;


import bean.UmsMember;
import bean.UmsMemberReceiveAddress;
import com.ljp.gmalluser.mapper.UmsMemberReceiveAddressMapper;
import com.ljp.gmalluser.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public List<UmsMember> getAllUser() {
        List<UmsMember> UmsMemberlist = userMapper.selectAll();
        return UmsMemberlist;
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        Example e = new Example(UmsMemberReceiveAddress.class);

        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = umsMemberReceiveAddressMapper.selectByExample(e);
        return umsMemberReceiveAddressList;
    }
}
