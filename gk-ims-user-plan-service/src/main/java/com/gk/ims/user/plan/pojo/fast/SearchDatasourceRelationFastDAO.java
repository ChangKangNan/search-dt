package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SearchDatasourceRelation;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 查询方案数据源关系表
*/
public class SearchDatasourceRelationFastDAO extends BaseFastDAO<SearchDatasourceRelation> {

    public static SearchDatasourceRelationFastDAO create(){return new SearchDatasourceRelationFastDAO();}
    public static SearchDatasourceRelationFastDAO create(Object object) {
        SearchDatasourceRelationFastDAO fastDao = new SearchDatasourceRelationFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchDatasourceRelation> searchDatasourceId(Long... searchDatasourceIds){return fastExample.field("searchDatasourceId").valEqual(searchDatasourceIds);}
    public FastExample.Criteria<SearchDatasourceRelation> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchDatasourceRelation> seSearchConfigId(Long... seSearchConfigIds){return fastExample.field("seSearchConfigId").valEqual(seSearchConfigIds);}
    public FastExample.Criteria<SearchDatasourceRelation> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchDatasourceRelation> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SearchDatasourceRelation> id(Long... ids){return fastExample.field("id").valEqual(ids);}

}