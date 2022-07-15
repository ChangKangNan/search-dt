package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SearchTableColumnTarget;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 指向表
*/
public class SearchTableColumnTargetFastDAO extends BaseFastDAO<SearchTableColumnTarget> {

    public static SearchTableColumnTargetFastDAO create(){return new SearchTableColumnTargetFastDAO();}
    public static SearchTableColumnTargetFastDAO create(Object object) {
        SearchTableColumnTargetFastDAO fastDao = new SearchTableColumnTargetFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchTableColumnTarget> searchTableColumnId(Long... searchTableColumnIds){return fastExample.field("searchTableColumnId").valEqual(searchTableColumnIds);}
    public FastExample.Criteria<SearchTableColumnTarget> createBy(String... createBys){return fastExample.field("createBy").valEqual(createBys);}
    public FastExample.Criteria<SearchTableColumnTarget> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchTableColumnTarget> updateBy(String... updateBys){return fastExample.field("updateBy").valEqual(updateBys);}
    public FastExample.Criteria<SearchTableColumnTarget> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchTableColumnTarget> remark(String... remarks){return fastExample.field("remark").valEqual(remarks);}
    public FastExample.Criteria<SearchTableColumnTarget> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SearchTableColumnTarget> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SearchTableColumnTarget> searchTableMainRelationId(Long... searchTableMainRelationIds){return fastExample.field("searchTableMainRelationId").valEqual(searchTableMainRelationIds);}

}