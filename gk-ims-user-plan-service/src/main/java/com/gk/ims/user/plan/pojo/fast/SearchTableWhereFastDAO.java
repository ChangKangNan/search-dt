package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SearchTableWhere;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 初始条件表
*/
public class SearchTableWhereFastDAO extends BaseFastDAO<SearchTableWhere> {

    public static SearchTableWhereFastDAO create(){return new SearchTableWhereFastDAO();}
    public static SearchTableWhereFastDAO create(Object object) {
        SearchTableWhereFastDAO fastDao = new SearchTableWhereFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchTableWhere> pageTag(String... pageTags){return fastExample.field("pageTag").valEqual(pageTags);}
    public FastExample.Criteria<SearchTableWhere> createBy(String... createBys){return fastExample.field("createBy").valEqual(createBys);}
    public FastExample.Criteria<SearchTableWhere> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchTableWhere> tableWhere(String... tableWheres){return fastExample.field("tableWhere").valEqual(tableWheres);}
    public FastExample.Criteria<SearchTableWhere> updateBy(String... updateBys){return fastExample.field("updateBy").valEqual(updateBys);}
    public FastExample.Criteria<SearchTableWhere> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchTableWhere> remark(String... remarks){return fastExample.field("remark").valEqual(remarks);}
    public FastExample.Criteria<SearchTableWhere> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SearchTableWhere> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SearchTableWhere> version(String... versions){return fastExample.field("version").valEqual(versions);}
    public FastExample.Criteria<SearchTableWhere> relation(String... relations){return fastExample.field("relation").valEqual(relations);}

}