package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SearchTableColumn;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 列名表
*/
public class SearchTableColumnFastDAO extends BaseFastDAO<SearchTableColumn> {

    public static SearchTableColumnFastDAO create(){return new SearchTableColumnFastDAO();}
    public static SearchTableColumnFastDAO create(Object object) {
        SearchTableColumnFastDAO fastDao = new SearchTableColumnFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchTableColumn> pageTag(String... pageTags){return fastExample.field("pageTag").valEqual(pageTags);}
    public FastExample.Criteria<SearchTableColumn> createBy(String... createBys){return fastExample.field("createBy").valEqual(createBys);}
    public FastExample.Criteria<SearchTableColumn> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchTableColumn> updateBy(String... updateBys){return fastExample.field("updateBy").valEqual(updateBys);}
    public FastExample.Criteria<SearchTableColumn> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchTableColumn> searchTableColumnInfo(String... searchTableColumnInfos){return fastExample.field("searchTableColumnInfo").valEqual(searchTableColumnInfos);}
    public FastExample.Criteria<SearchTableColumn> remark(String... remarks){return fastExample.field("remark").valEqual(remarks);}
    public FastExample.Criteria<SearchTableColumn> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SearchTableColumn> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SearchTableColumn> version(String... versions){return fastExample.field("version").valEqual(versions);}
    public FastExample.Criteria<SearchTableColumn> searchTableColumn(String... searchTableColumns){return fastExample.field("searchTableColumn").valEqual(searchTableColumns);}

}