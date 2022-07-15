package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SeSearchPlanNewCondition;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 用户搜索方案配置表
*/
public class SeSearchPlanNewConditionFastDAO extends BaseFastDAO<SeSearchPlanNewCondition> {

    public static SeSearchPlanNewConditionFastDAO create(){return new SeSearchPlanNewConditionFastDAO();}
    public static SeSearchPlanNewConditionFastDAO create(Object object) {
        SeSearchPlanNewConditionFastDAO fastDao = new SeSearchPlanNewConditionFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SeSearchPlanNewCondition> parentGroupRelation(String... parentGroupRelations){return fastExample.field("parentGroupRelation").valEqual(parentGroupRelations);}
    public FastExample.Criteria<SeSearchPlanNewCondition> searchCondition(String... searchConditions){return fastExample.field("searchCondition").valEqual(searchConditions);}
    public FastExample.Criteria<SeSearchPlanNewCondition> globalId(Long... globalIds){return fastExample.field("globalId").valEqual(globalIds);}
    public FastExample.Criteria<SeSearchPlanNewCondition> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SeSearchPlanNewCondition> searchColumn(String... searchColumns){return fastExample.field("searchColumn").valEqual(searchColumns);}
    public FastExample.Criteria<SeSearchPlanNewCondition> pageTag(String... pageTags){return fastExample.field("pageTag").valEqual(pageTags);}
    public FastExample.Criteria<SeSearchPlanNewCondition> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SeSearchPlanNewCondition> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SeSearchPlanNewCondition> userAccount(String... userAccounts){return fastExample.field("userAccount").valEqual(userAccounts);}
    public FastExample.Criteria<SeSearchPlanNewCondition> searchPlanId(Long... searchPlanIds){return fastExample.field("searchPlanId").valEqual(searchPlanIds);}
    public FastExample.Criteria<SeSearchPlanNewCondition> searchGroupRelation(String... searchGroupRelations){return fastExample.field("searchGroupRelation").valEqual(searchGroupRelations);}
    public FastExample.Criteria<SeSearchPlanNewCondition> parentGroup(String... parentGroups){return fastExample.field("parentGroup").valEqual(parentGroups);}
    public FastExample.Criteria<SeSearchPlanNewCondition> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SeSearchPlanNewCondition> searchConfigInfoId(Long... searchConfigInfoIds){return fastExample.field("searchConfigInfoId").valEqual(searchConfigInfoIds);}
    public FastExample.Criteria<SeSearchPlanNewCondition> searchValue(String... searchValues){return fastExample.field("searchValue").valEqual(searchValues);}
    public FastExample.Criteria<SeSearchPlanNewCondition> searchGroup(String... searchGroups){return fastExample.field("searchGroup").valEqual(searchGroups);}

}