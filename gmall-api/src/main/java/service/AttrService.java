package service;

import bean.PmsBaseAttrInfo;
import bean.PmsBaseAttrValue;
import bean.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {

    public List<PmsBaseAttrInfo>  attrInfoList(String catalog3Id);


    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueListByAttrId(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
