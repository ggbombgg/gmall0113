package service;

import bean.PmsProductImage;
import bean.PmsProductInfo;
import bean.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {

    public List<PmsProductInfo> spuListByCatalog3Id(String catalog3Id);

    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);
}
