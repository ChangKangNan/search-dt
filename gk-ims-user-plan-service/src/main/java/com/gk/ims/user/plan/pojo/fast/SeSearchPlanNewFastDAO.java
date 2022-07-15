package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SeSearchPlanNew;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 用户搜索方案表
*/
public class SeSearchPlanNewFastDAO extends BaseFastDAO<SeSearchPlanNew> {

    public static SeSearchPlanNewFastDAO create(){return new SeSearchPlanNewFastDAO();}
    public static SeSearchPlanNewFastDAO create(Object object) {
        SeSearchPlanNewFastDAO fastDao = new SeSearchPlanNewFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SeSearchPlanNew> createOrg(Long... createOrgs){return fastExample.field("createOrg").valEqual(createOrgs);}
    public FastExample.Criteria<SeSearchPlanNew> def(Boolean... defs){return fastExample.field("def").valEqual(defs);}
    public FastExample.Criteria<SeSearchPlanNew> globalId(Long... globalIds){return fastExample.field("globalId").valEqual(globalIds);}
    public FastExample.Criteria<SeSearchPlanNew> planName(String... planNames){return fastExample.field("planName").valEqual(planNames);}
    public FastExample.Criteria<SeSearchPlanNew> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SeSearchPlanNew> version(String... versions){return fastExample.field("version").valEqual(versions);}
    public FastExample.Criteria<SeSearchPlanNew> pageTag(String... pageTags){return fastExample.field("pageTag").valEqual(pageTags);}
    public FastExample.Criteria<SeSearchPlanNew> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SeSearchPlanNew> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SeSearchPlanNew> userAccount(String... userAccounts){return fastExample.field("userAccount").valEqual(userAccounts);}
    public FastExample.Criteria<SeSearchPlanNew> customMarker(String... customMarkers){return fastExample.field("customMarker").valEqual(customMarkers);}
    public FastExample.Criteria<SeSearchPlanNew> sortNum(Integer... sortNums){return fastExample.field("sortNum").valEqual(sortNums);}
    public FastExample.Criteria<SeSearchPlanNew> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SeSearchPlanNew> searchSql(String... searchSqls){return fastExample.field("searchSql").valEqual(searchSqls);}

}