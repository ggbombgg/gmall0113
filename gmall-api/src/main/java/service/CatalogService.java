package service;

import bean.PmsBaseCatalog1;
import bean.PmsBaseCatalog2;
import bean.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {

        public List<PmsBaseCatalog1> getCatalog1();

        public List<PmsBaseCatalog2> getCatalog2(String catalog1ID);

        public List<PmsBaseCatalog3> getCatalog3(String catalog2ID);

}
