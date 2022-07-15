package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SearchTableMainRelation;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 关联关系表-表体
*/
public class SearchTableMainRelationFastDAO extends BaseFastDAO<SearchTableMainRelation> {

    public static SearchTableMainRelationFastDAO create(){return new SearchTableMainRelationFastDAO();}
    public static SearchTableMainRelationFastDAO create(Object object) {
        SearchTableMainRelationFastDAO fastDao = new SearchTableMainRelationFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchTableMainRelation> targetTableColumnName(String... targetTableColumnNames){return fastExample.field("targetTableColumnName").valEqual(targetTableColumnNames);}
    public FastExample.Criteria<SearchTableMainRelation> remark(String... remarks){return fastExample.field("remark").valEqual(remarks);}
    public FastExample.Criteria<SearchTableMainRelation> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SearchTableMainRelation> parentRelationId(Long... parentRelationIds){return fastExample.field("parentRelationId").valEqual(parentRelationIds);}
    public FastExample.Criteria<SearchTableMainRelation> searchTableMainId(Long... searchTableMainIds){return fastExample.field("searchTableMainId").valEqual(searchTableMainIds);}
    public FastExample.Criteria<SearchTableMainRelation> sourceTableName(String... sourceTableNames){return fastExample.field("sourceTableName").valEqual(sourceTableNames);}
    public FastExample.Criteria<SearchTableMainRelation> createBy(String... createBys){return fastExample.field("createBy").valEqual(createBys);}
    public FastExample.Criteria<SearchTableMainRelation> isExtend(Boolean... isExtends){return fastExample.field("isExtend").valEqual(isExtends);}
    public FastExample.Criteria<SearchTableMainRelation> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchTableMainRelation> sourceTableAlias(String... sourceTableAliass){return fastExample.field("sourceTableAlias").valEqual(sourceTableAliass);}
    public FastExample.Criteria<SearchTableMainRelation> updateBy(String... updateBys){return fastExample.field("updateBy").valEqual(updateBys);}
    public FastExample.Criteria<SearchTableMainRelation> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchTableMainRelation> sourceTableColumnName(String... sourceTableColumnNames){return fastExample.field("sourceTableColumnName").valEqual(sourceTableColumnNames);}
    public FastExample.Criteria<SearchTableMainRelation> targetTableName(String... targetTableNames){return fastExample.field("targetTableName").valEqual(targetTableNames);}
    public FastExample.Criteria<SearchTableMainRelation> targetTableAlias(String... targetTableAliass){return fastExample.field("targetTableAlias").valEqual(targetTableAliass);}
    public FastExample.Criteria<SearchTableMainRelation> id(Long... ids){return fastExample.field("id").valEqual(ids);}

}