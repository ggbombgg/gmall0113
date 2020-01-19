package com.ljp.gmall.manage.service.impl;

import bean.PmsProductImage;
import bean.PmsProductInfo;
import bean.PmsProductSaleAttr;
import bean.PmsProductSaleAttrValue;
import com.alibaba.dubbo.config.annotation.Service;
import com.ljp.gmall.manage.mapper.PmsProductImageMapper;
import com.ljp.gmall.manage.mapper.PmsProductInfoMapper;
import com.ljp.gmall.manage.mapper.PmsProductSaleAttrMapper;
import com.ljp.gmall.manage.mapper.PmsProductSaleAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.SpuService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Override
    public List<PmsProductInfo> spuListByCatalog3Id(String catalog3Id) {
        Example e = new Example(PmsProductInfo.class);
        e.createCriteria().andEqualTo("catalog3Id",catalog3Id);
        return pmsProductInfoMapper.selectByExample(e);
    }

    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {

        //插入产品信息
        pmsProductInfoMapper.insertSelective(pmsProductInfo);

        pmsProductInfo.getSpuSaleAttrList().parallelStream().forEach(pmsProductSaleAttr -> {
            //插入销售属性
            pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);

            //插入销售属性值
            pmsProductSaleAttr.getSpuSaleAttrValueList().stream().forEach(pmsProductSaleAttrValue -> {
                pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            });


        });

        //插入图片信息
        pmsProductInfo.getSpuImageList().stream().forEach(pmsProductImage -> {
            pmsProductImage.setProductId(pmsProductInfo.getId());
            pmsProductImageMapper.insertSelective(pmsProductImage);
        });

        return "success";
    }

    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        return pmsProductImageMapper.select(pmsProductImage);
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {

        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);

        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrList) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            productSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }

        return pmsProductSaleAttrList;
    }

}
