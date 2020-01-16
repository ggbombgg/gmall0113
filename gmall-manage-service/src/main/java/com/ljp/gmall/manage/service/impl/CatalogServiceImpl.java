package com.ljp.gmall.manage.service.impl;

import bean.PmsBaseCatalog1;
import bean.PmsBaseCatalog2;
import bean.PmsBaseCatalog3;
import com.alibaba.dubbo.config.annotation.Service;
import com.ljp.gmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.ljp.gmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.ljp.gmall.manage.mapper.PmsBaseCatalog3Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.CatalogService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;

    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;

    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {

        return pmsBaseCatalog1Mapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        Example e = new Example(PmsBaseCatalog2.class);
        e.createCriteria().andEqualTo("catalog1Id",catalog1Id);

        return pmsBaseCatalog2Mapper.selectByExample(e);
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        Example e = new Example(PmsBaseCatalog3.class);
        e.createCriteria().andEqualTo("catalog2Id",catalog2Id);
        List<PmsBaseCatalog3> pmsBaseCatalog3List = pmsBaseCatalog3Mapper.selectByExample(e);
        return pmsBaseCatalog3List;
    }
}
