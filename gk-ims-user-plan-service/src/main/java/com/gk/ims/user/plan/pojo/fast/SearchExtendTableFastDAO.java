package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SearchExtendTable;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 扩展表
*/
public class SearchExtendTableFastDAO extends BaseFastDAO<SearchExtendTable> {

    public static SearchExtendTableFastDAO create(){return new SearchExtendTableFastDAO();}
    public static SearchExtendTableFastDAO create(Object object) {
        SearchExtendTableFastDAO fastDao = new SearchExtendTableFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchExtendTable> searchTableMainId(Long... searchTableMainIds){return fastExample.field("searchTableMainId").valEqual(searchTableMainIds);}
    public FastExample.Criteria<SearchExtendTable> extendSql(String... extendSqls){return fastExample.field("extendSql").valEqual(extendSqls);}
    public FastExample.Criteria<SearchExtendTable> createBy(String... createBys){return fastExample.field("createBy").valEqual(createBys);}
    public FastExample.Criteria<SearchExtendTable> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchExtendTable> extendTableName(String... extendTableNames){return fastExample.field("extendTableName").valEqual(extendTableNames);}
    public FastExample.Criteria<SearchExtendTable> updateBy(String... updateBys){return fastExample.field("updateBy").valEqual(updateBys);}
    public FastExample.Criteria<SearchExtendTable> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchExtendTable> extendTableId(String... extendTableIds){return fastExample.field("extendTableId").valEqual(extendTableIds);}
    public FastExample.Criteria<SearchExtendTable> remark(String... remarks){return fastExample.field("remark").valEqual(remarks);}
    public FastExample.Criteria<SearchExtendTable> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SearchExtendTable> id(Long... ids){return fastExample.field("id").valEqual(ids);}

}