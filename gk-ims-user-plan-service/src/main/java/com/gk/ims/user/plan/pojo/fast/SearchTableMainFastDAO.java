package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SearchTableMain;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 关联关系表-表头
*/
public class SearchTableMainFastDAO extends BaseFastDAO<SearchTableMain> {

    public static SearchTableMainFastDAO create(){return new SearchTableMainFastDAO();}
    public static SearchTableMainFastDAO create(Object object) {
        SearchTableMainFastDAO fastDao = new SearchTableMainFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchTableMain> pageTag(String... pageTags){return fastExample.field("pageTag").valEqual(pageTags);}
    public FastExample.Criteria<SearchTableMain> createBy(String... createBys){return fastExample.field("createBy").valEqual(createBys);}
    public FastExample.Criteria<SearchTableMain> searchTableName(String... searchTableNames){return fastExample.field("searchTableName").valEqual(searchTableNames);}
    public FastExample.Criteria<SearchTableMain> isExtend(Boolean... isExtends){return fastExample.field("isExtend").valEqual(isExtends);}
    public FastExample.Criteria<SearchTableMain> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchTableMain> updateBy(String... updateBys){return fastExample.field("updateBy").valEqual(updateBys);}
    public FastExample.Criteria<SearchTableMain> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchTableMain> alias(String... aliass){return fastExample.field("alias").valEqual(aliass);}
    public FastExample.Criteria<SearchTableMain> remark(String... remarks){return fastExample.field("remark").valEqual(remarks);}
    public FastExample.Criteria<SearchTableMain> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SearchTableMain> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SearchTableMain> version(String... versions){return fastExample.field("version").valEqual(versions);}

}