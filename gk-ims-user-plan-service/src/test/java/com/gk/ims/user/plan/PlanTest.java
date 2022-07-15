package com.gk.ims.user.plan;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.fast.fast.FastCustomSqlDao;
import com.gk.ims.config.login.WebUtil;
import com.gk.ims.user.plan.api.dto.SearchQueryDTO;
import com.gk.ims.user.plan.api.dto.SearchResult;
import com.gk.ims.user.plan.api.dto.SearchSavePlanDTO;
import com.gk.ims.user.plan.pojo.SeSearchConfig;
import com.gk.ims.user.plan.pojo.SearchDatasourceRelation;
import com.gk.ims.user.plan.pojo.fast.SeSearchConfigFastDAO;
import com.gk.ims.user.plan.pojo.fast.SearchDatasourceRelationFastDAO;
import com.gk.ims.user.plan.service.SearchService;
import com.gk.ims.user.plan.service.impl.SearchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class PlanTest {
    @Autowired
    SearchService searchService;

//    @Test
//    public void testEasy() {
//        SearchSavePlanDTO SearchSavePlanDTO = new SearchSavePlanDTO();
//        SearchSavePlanDTO.setId(4L);
//        SearchSavePlanDTO.setOrgId(new Long(8));
//        SearchSavePlanDTO.setPageTag("wo_fa_qi_de_shen_pi");
//        List<SearchSavePlanDTO.SearchColumn> searchColumns = new ArrayList<>();
//        SearchSavePlanDTO.SearchColumn searchColumn = new SearchSavePlanDTO.SearchColumn();
//        searchColumn.setColumnName("id");
//        searchColumn.setStatus("on");
//        SearchSavePlanDTO.SearchColumn searchColumn1 = new SearchSavePlanDTO.SearchColumn();
//        searchColumn1.setColumnName("title");
//        searchColumn1.setStatus("on");
//        SearchSavePlanDTO.SearchColumn searchColumn2 = new SearchSavePlanDTO.SearchColumn();
//        searchColumn2.setColumnName("relation_id");
//        searchColumn2.setStatus("on");
//        SearchSavePlanDTO.SearchColumn searchColumn3 = new SearchSavePlanDTO.SearchColumn();
//        searchColumn3.setColumnName("workflow_model_id");
//        searchColumn3.setStatus("hidden");
//        SearchSavePlanDTO.SearchColumn searchColumn4 = new SearchSavePlanDTO.SearchColumn();
//        searchColumn4.setColumnName("workflow_model_name");
//        searchColumn4.setStatus("on");
//        SearchSavePlanDTO.SearchColumn searchColumn5 = new SearchSavePlanDTO.SearchColumn();
//        searchColumn5.setColumnName("next_node_name");
//        searchColumn5.setStatus("on");
//        SearchSavePlanDTO.SearchColumn searchColumn6 = new SearchSavePlanDTO.SearchColumn();
//        searchColumn6.setColumnName("approval_status");
//        searchColumn6.setStatus("on");
//        SearchSavePlanDTO.SearchColumn searchColumn7 = new SearchSavePlanDTO.SearchColumn();
//        searchColumn7.setColumnName("create_time");
//        searchColumn7.setStatus("close");
//        SearchSavePlanDTO.SearchColumn searchColumn8 = new SearchSavePlanDTO.SearchColumn();
//        searchColumn8.setColumnName("submit_user");
//        searchColumn8.setStatus("close");
//        searchColumns.add(searchColumn);
//        searchColumns.add(searchColumn1);
//        searchColumns.add(searchColumn2);
//        searchColumns.add(searchColumn3);
//        searchColumns.add(searchColumn4);
//        searchColumns.add(searchColumn5);
//        searchColumns.add(searchColumn6);
//        searchColumns.add(searchColumn7);
//        searchColumns.add(searchColumn8);
//        SearchSavePlanDTO.setSearchColumns(searchColumns);
//        searchService.savePlan(SearchSavePlanDTO);
//    }
//
    @Test
    public void testFz() {
        SearchSavePlanDTO SearchSavePlanDTO = new SearchSavePlanDTO();
        SearchSavePlanDTO.setOrgId(new Long(8));
       // SearchSavePlanDTO.setId(4L);
        SearchSavePlanDTO.setPageTag("pai_huo_jie_suan_qing_kuang_biao");
        SearchSavePlanDTO.setDesc(true);
        SearchSavePlanDTO.setOrderBy("ph.id");
        SearchSavePlanDTO.setUserAccount("ckn");
        SearchSavePlanDTO.setPlanName("all_date");
        SearchSavePlanDTO.setPageNum(1);
        SearchSavePlanDTO.setPageSize(10);
        SearchSavePlanDTO.setRefreshCache(true);
        SearchSavePlanDTO.setReadCache(true);
        List<SearchSavePlanDTO.SearchColumn> searchColumns = new ArrayList<>();
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("id", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("kyc_id", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("company_name", "close"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("no", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("pn_no", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("source_no", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("use_org", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("department_id", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("salesman", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("direction", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("match_date", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("product_name", "close"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("brand", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("standard", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("document_type","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("number","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("numbers", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("total_money", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("match_amount","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("no_match_amount","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("currency","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("rp_status", "close"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("status", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("price_confirmed","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("delivery_completed","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("settled", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("is_settle","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("settle_amount","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("settle_sf_amount", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("warehouse","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("create_user_account","on"));
        SearchSavePlanDTO.setSearchColumns(searchColumns);
        List<SearchSavePlanDTO.QueryInfo> queryInfoList=new ArrayList<>();
        queryInfoList.add(new SearchSavePlanDTO.QueryInfo("cam.use_org",71405L,"Equal","8","a","and",null,null));
        queryInfoList.add(new SearchSavePlanDTO.QueryInfo("ph.brand",71411L,"Equal","ABRA","a","and","a","and"));
        SearchSavePlanDTO.setQueryInfoList(queryInfoList);
        WebUtil.setOrganization(8L);
        searchService.savePlan(SearchSavePlanDTO);
    }
    @Test
    public  void testSelect(){
        WebUtil.setOrganization(8L);
        SearchQueryDTO searchQueryDTO =new SearchQueryDTO();
        searchQueryDTO.setSearchPlanId(2L);
        SearchResult query = searchService.query(searchQueryDTO);
        System.out.println(JSONUtil.toJsonStr(query));
        SearchQueryDTO searchQueryDTO1 =new SearchQueryDTO();
        searchQueryDTO1.setSearchPlanId(4L);
        SearchResult query1 = searchService.query(searchQueryDTO1);
        System.out.println(JSONUtil.toJsonStr(query1));
    }
    @Test
    public void generateSearch() {
        String[] extendTables = {"funding_sf_amount", "ph_settle", "settle1", "pay_rec", "ph_rp", "stock_money_price", "fei_yong"};
        searchService.splitSearch("pai_huo_jie_suan_qing_kuang_biao", null,extendTables);
    }
    @Test
    public void generateBaozhengjin() {
        String[] extendTables = {"ph_settle","phs","funding_sf_amount"};
        searchService.splitSearch("bao_zheng_jin_zhuan_huo_kuan_huo_wu_ming_xi", null,extendTables);
    }
    @Test
    public void Plan() {
        SearchSavePlanDTO SearchSavePlanDTO = new SearchSavePlanDTO();
        SearchSavePlanDTO.setOrgId(new Long(8));
         SearchSavePlanDTO.setId(5L);
        SearchSavePlanDTO.setPageTag("bao_zheng_jin_zhuan_huo_kuan_huo_wu_ming_xi");
        SearchSavePlanDTO.setDesc(true);
        SearchSavePlanDTO.setOrderBy("ph.id");
        SearchSavePlanDTO.setUserAccount("ckn");
        SearchSavePlanDTO.setPlanName("all_date1");
        SearchSavePlanDTO.setPageNum(1);
        SearchSavePlanDTO.setPageSize(10);
        SearchSavePlanDTO.setRefreshCache(true);
        SearchSavePlanDTO.setReadCache(true);
        List<SearchSavePlanDTO.SearchColumn> searchColumns = new ArrayList<>();
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("id", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("no", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("contract_no", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("lot_no", "close"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("kyc_id", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("warehouse", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("product_type_id", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("brand", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("standard", "close"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("number", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("temporary_price", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("final_price", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("rel_money", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("no_match_amount", "close"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("department", "on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("salesman","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("use_orgs","on"));
        searchColumns.add(new SearchSavePlanDTO.SearchColumn("direction", "on"));
        SearchSavePlanDTO.setSearchColumns(searchColumns);
        List<SearchSavePlanDTO.QueryInfo> queryInfoList=new ArrayList<>();
        queryInfoList.add(new SearchSavePlanDTO.QueryInfo("ph.brand",75023L,"Equal","ABRA","a","and",null,null));
        SearchSavePlanDTO.setQueryInfoList(queryInfoList);
        WebUtil.setOrganization(8L);
        searchService.savePlan(SearchSavePlanDTO);
    }


    @Test
    public void generateRelation() {
        List<SeSearchConfig> searchConfigs = SeSearchConfigFastDAO.create().dao().findAll();
        if(CollUtil.isNotEmpty(searchConfigs)){
            for (SeSearchConfig config : searchConfigs) {
                SearchDatasourceRelation searchDatasourceRelation=new SearchDatasourceRelation();
                searchDatasourceRelation.setSearchDatasourceId(1L);
                searchDatasourceRelation.setSeSearchConfigId(config.getId());
                SearchDatasourceRelationFastDAO.create().dao().insert(searchDatasourceRelation);
            }
        }
    }

    @Test
    public void testSwitchDataSource() {
/*        SeSearchConfig searchConfig=new SeSearchConfig();
        searchConfig.setId(999L);
        SearchServiceImpl.dataSourceChange(searchConfig);
        List<Map> maps = FastCustomSqlDao.create(Map.class, "select * from stock", new HashMap<>()).findAll();
        log.info(JSONUtil.toJsonStr(maps));*/
    }
}
