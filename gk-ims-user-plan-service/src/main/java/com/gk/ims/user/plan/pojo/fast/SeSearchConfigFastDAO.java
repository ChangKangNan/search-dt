package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SeSearchConfig;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 搜索定义表
*/
public class SeSearchConfigFastDAO extends BaseFastDAO<SeSearchConfig> {

    public static SeSearchConfigFastDAO create(){return new SeSearchConfigFastDAO();}
    public static SeSearchConfigFastDAO create(Object object) {
        SeSearchConfigFastDAO fastDao = new SeSearchConfigFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SeSearchConfig> configName(String... configNames){return fastExample.field("configName").valEqual(configNames);}
    public FastExample.Criteria<SeSearchConfig> pageTag(String... pageTags){return fastExample.field("pageTag").valEqual(pageTags);}
    public FastExample.Criteria<SeSearchConfig> useOrgsLimitTableAlias(String... useOrgsLimitTableAliass){return fastExample.field("useOrgsLimitTableAlias").valEqual(useOrgsLimitTableAliass);}
    public FastExample.Criteria<SeSearchConfig> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SeSearchConfig> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SeSearchConfig> updataTime(Date... updataTimes){return fastExample.field("updataTime").valEqual(updataTimes);}
    public FastExample.Criteria<SeSearchConfig> globalId(Long... globalIds){return fastExample.field("globalId").valEqual(globalIds);}
    public FastExample.Criteria<SeSearchConfig> remark(String... remarks){return fastExample.field("remark").valEqual(remarks);}
    public FastExample.Criteria<SeSearchConfig> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SeSearchConfig> searchSql(String... searchSqls){return fastExample.field("searchSql").valEqual(searchSqls);}
    public FastExample.Criteria<SeSearchConfig> useOrgLimitTableAlias(String... useOrgLimitTableAliass){return fastExample.field("useOrgLimitTableAlias").valEqual(useOrgLimitTableAliass);}
    public FastExample.Criteria<SeSearchConfig> version(String... versions){return fastExample.field("version").valEqual(versions);}

}