package com.ljp.gmall.manage.service.impl;

import bean.PmsBaseCatalog3;
import com.ljp.gmall.manage.mapper.PmsBaseCatalog3Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogServiceImplTest {

    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Test
    public void getCatalog3() {
        Example e = new Example(PmsBaseCatalog3.class);
        e.createCriteria().andEqualTo("catalog2Id",15);
        List<PmsBaseCatalog3> pmsBaseCatalog3List = pmsBaseCatalog3Mapper.selectByExample(e);
        System.out.println("ss");
    }
}