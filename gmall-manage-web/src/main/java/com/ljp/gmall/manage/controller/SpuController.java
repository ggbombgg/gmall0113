package com.ljp.gmall.manage.controller;

import bean.PmsProductImage;
import bean.PmsProductInfo;
import bean.PmsProductSaleAttr;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ljp.gmall.manage.util.PmsUpLoadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.SpuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo> pmsProductInfoList = spuService.spuListByCatalog3Id(catalog3Id);
        return pmsProductInfoList;
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        String result = spuService.saveSpuInfo(pmsProductInfo);
        return result;
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public  String fileUpload(@RequestParam("file") MultipartFile multipartFile){

        return PmsUpLoadUtil.uploadImage(multipartFile);
    }

    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){
        List<PmsProductSaleAttr> pmsProductInfoList = spuService.spuSaleAttrList(spuId);
        return pmsProductInfoList;
    }

    @RequestMapping("spuImageList")
    @ResponseBody
    public  List<PmsProductImage> spuImageList(String spuId){

        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }


}

