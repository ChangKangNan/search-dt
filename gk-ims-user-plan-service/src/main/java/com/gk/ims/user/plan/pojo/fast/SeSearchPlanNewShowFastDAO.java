package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SeSearchPlanNewShow;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 用户搜索方案配置表
*/
public class SeSearchPlanNewShowFastDAO extends BaseFastDAO<SeSearchPlanNewShow> {

    public static SeSearchPlanNewShowFastDAO create(){return new SeSearchPlanNewShowFastDAO();}
    public static SeSearchPlanNewShowFastDAO create(Object object) {
        SeSearchPlanNewShowFastDAO fastDao = new SeSearchPlanNewShowFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SeSearchPlanNewShow> pageTag(String... pageTags){return fastExample.field("pageTag").valEqual(pageTags);}
    public FastExample.Criteria<SeSearchPlanNewShow> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SeSearchPlanNewShow> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SeSearchPlanNewShow> userAccount(String... userAccounts){return fastExample.field("userAccount").valEqual(userAccounts);}
    public FastExample.Criteria<SeSearchPlanNewShow> globalId(Long... globalIds){return fastExample.field("globalId").valEqual(globalIds);}
    public FastExample.Criteria<SeSearchPlanNewShow> searchPlanId(Long... searchPlanIds){return fastExample.field("searchPlanId").valEqual(searchPlanIds);}
    public FastExample.Criteria<SeSearchPlanNewShow> showStatus(String... showStatuss){return fastExample.field("showStatus").valEqual(showStatuss);}
    public FastExample.Criteria<SeSearchPlanNewShow> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SeSearchPlanNewShow> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SeSearchPlanNewShow> searchColumn(String... searchColumns){return fastExample.field("searchColumn").valEqual(searchColumns);}

}