package com.ljp.gmall.manage.service.impl;

import bean.PmsBaseAttrInfo;
import com.alibaba.dubbo.config.annotation.Service;
import com.ljp.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.AttrService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        Example e = new Example(PmsBaseAttrInfo.class);
        e.createCriteria().andEqualTo("catalog3Id",catalog3Id);
        return pmsBaseAttrInfoMapper.selectByExample(e);
    }
}
