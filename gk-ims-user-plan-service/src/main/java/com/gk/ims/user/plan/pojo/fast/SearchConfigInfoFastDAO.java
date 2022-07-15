package com.gk.ims.user.plan.pojo.fast;

import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import com.gk.ims.user.plan.pojo.SearchConfigInfo;

import java.util.Date;

/**
* 搜索配置设置表
*/
public class SearchConfigInfoFastDAO extends BaseFastDAO<SearchConfigInfo> {

    public static SearchConfigInfoFastDAO create(){return new SearchConfigInfoFastDAO();}
    public static SearchConfigInfoFastDAO create(Object object) {
        SearchConfigInfoFastDAO fastDao = new SearchConfigInfoFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchConfigInfo> limitSearchValues(String... limitSearchValuess){return fastExample.field("limitSearchValues").valEqual(limitSearchValuess);}
    public FastExample.Criteria<SearchConfigInfo> hidden(Boolean... hiddens){return fastExample.field("hidden").valEqual(hiddens);}
    public FastExample.Criteria<SearchConfigInfo> searchType(String... searchTypes){return fastExample.field("searchType").valEqual(searchTypes);}
    public FastExample.Criteria<SearchConfigInfo> limitSearchConditions(String... limitSearchConditionss){return fastExample.field("limitSearchConditions").valEqual(limitSearchConditionss);}
    public FastExample.Criteria<SearchConfigInfo> searchKey(String... searchKeys){return fastExample.field("searchKey").valEqual(searchKeys);}
    public FastExample.Criteria<SearchConfigInfo> searchConfigId(Long... searchConfigIds){return fastExample.field("searchConfigId").valEqual(searchConfigIds);}
    public FastExample.Criteria<SearchConfigInfo> pageTag(String... pageTags){return fastExample.field("pageTag").valEqual(pageTags);}
    public FastExample.Criteria<SearchConfigInfo> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchConfigInfo> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchConfigInfo> updataTime(Date... updataTimes){return fastExample.field("updataTime").valEqual(updataTimes);}
    public FastExample.Criteria<SearchConfigInfo> searchName(String... searchNames){return fastExample.field("searchName").valEqual(searchNames);}
    public FastExample.Criteria<SearchConfigInfo> searchValueSource(String... searchValueSources){return fastExample.field("searchValueSource").valEqual(searchValueSources);}
    public FastExample.Criteria<SearchConfigInfo> sortNum(Integer... sortNums){return fastExample.field("sortNum").valEqual(sortNums);}
    public FastExample.Criteria<SearchConfigInfo> id(Long... ids){return fastExample.field("id").valEqual(ids);}

}