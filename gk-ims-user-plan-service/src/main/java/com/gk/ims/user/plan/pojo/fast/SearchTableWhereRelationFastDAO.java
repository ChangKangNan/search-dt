package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SearchTableWhereRelation;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 初始条件表-关联表
*/
public class SearchTableWhereRelationFastDAO extends BaseFastDAO<SearchTableWhereRelation> {

    public static SearchTableWhereRelationFastDAO create(){return new SearchTableWhereRelationFastDAO();}
    public static SearchTableWhereRelationFastDAO create(Object object) {
        SearchTableWhereRelationFastDAO fastDao = new SearchTableWhereRelationFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchTableWhereRelation> createBy(String... createBys){return fastExample.field("createBy").valEqual(createBys);}
    public FastExample.Criteria<SearchTableWhereRelation> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchTableWhereRelation> updateBy(String... updateBys){return fastExample.field("updateBy").valEqual(updateBys);}
    public FastExample.Criteria<SearchTableWhereRelation> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchTableWhereRelation> searchTableWhereId(Long... searchTableWhereIds){return fastExample.field("searchTableWhereId").valEqual(searchTableWhereIds);}
    public FastExample.Criteria<SearchTableWhereRelation> remark(String... remarks){return fastExample.field("remark").valEqual(remarks);}
    public FastExample.Criteria<SearchTableWhereRelation> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SearchTableWhereRelation> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SearchTableWhereRelation> searchTableMainRelationId(Long... searchTableMainRelationIds){return fastExample.field("searchTableMainRelationId").valEqual(searchTableMainRelationIds);}

}