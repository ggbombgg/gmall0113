package com.ljp.gmall.manage.service.impl;

import bean.PmsBaseAttrInfo;
import bean.PmsBaseAttrValue;
import bean.PmsBaseSaleAttr;
import com.alibaba.dubbo.config.annotation.Service;
import com.ljp.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.ljp.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.ljp.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import service.AttrService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;


import java.util.List;
import java.util.function.Consumer;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        Example e = new Example(PmsBaseAttrInfo.class);
        e.createCriteria().andEqualTo("catalog3Id",catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.selectByExample(e);
        //TODO 批量查询更好
        //查询属性值
        pmsBaseAttrInfos.stream().forEach(pmsBaseAttrInfo -> {
            Example e1 = new Example(PmsBaseAttrValue.class);
            e1.createCriteria().andEqualTo("attrId",pmsBaseAttrInfo.getId());
            List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsBaseAttrValueMapper.selectByExample(e1);
            pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValueList);
        });
        return pmsBaseAttrInfos;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {

        //TODO 事务呢
        String id = pmsBaseAttrInfo.getId();

        if(StringUtils.isBlank(id)){
            // id为空，保存
            // 保存属性
            //insert insertSelective 是否将null插入数据库
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);
            // 保存属性值
            pmsBaseAttrInfo
                    .getAttrValueList()
                    .parallelStream()
                    .forEach(pmsBaseAttrValue -> {
                        pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                        pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
                    });

        }else{
            // id不空，修改
            // 属性修改
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);
            // 属性值修改
            // 按照属性id删除所有属性值
            PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
            pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueMapper.delete(pmsBaseAttrValueDel);

            // 删除后，将新的属性值插入
            pmsBaseAttrInfo
                    .getAttrValueList()
                    .parallelStream()
                    .forEach(pmsBaseAttrValue -> pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue));
        }
        return "success";
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueListByAttrId(String attrId) {
        Example e =new Example(PmsBaseAttrValue.class);
        e.createCriteria().andEqualTo("attrId",attrId);

        return pmsBaseAttrValueMapper.selectByExample(e);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }
}
