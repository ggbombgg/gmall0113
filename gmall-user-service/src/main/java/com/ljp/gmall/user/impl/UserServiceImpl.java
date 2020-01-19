package com.ljp.gmall.user.impl;



import bean.UmsMember;
import bean.UmsMemberReceiveAddress;
import com.alibaba.dubbo.config.annotation.Service;
import com.ljp.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.ljp.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;
import tk.mybatis.mapper.entity.Example;

;import java.util.List;

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
        e.createCriteria().andEqualTo("memberId",memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = umsMemberReceiveAddressMapper.selectByExample(e);
        return umsMemberReceiveAddressList;
    }
}
