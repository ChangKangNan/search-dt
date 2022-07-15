package com.gk.ims.user.plan.pojo.fast;

import com.gk.ims.user.plan.pojo.SearchDatasource;
import com.fast.base.BaseFastDAO;
import com.fast.condition.FastExample;
import java.util.Date;

/**
* 查询方案数据源表
*/
public class SearchDatasourceFastDAO extends BaseFastDAO<SearchDatasource> {

    public static SearchDatasourceFastDAO create(){return new SearchDatasourceFastDAO();}
    public static SearchDatasourceFastDAO create(Object object) {
        SearchDatasourceFastDAO fastDao = new SearchDatasourceFastDAO();
        fastDao.equalObject(object);
        return fastDao;
    }

    public FastExample.Criteria<SearchDatasource> datasourceUsername(String... datasourceUsernames){return fastExample.field("datasourceUsername").valEqual(datasourceUsernames);}
    public FastExample.Criteria<SearchDatasource> datasourceDriver(String... datasourceDrivers){return fastExample.field("datasourceDriver").valEqual(datasourceDrivers);}
    public FastExample.Criteria<SearchDatasource> deleted(Boolean... deleteds){return fastExample.field("deleted").valEqual(deleteds);}
    public FastExample.Criteria<SearchDatasource> createTime(Date... createTimes){return fastExample.field("createTime").valEqual(createTimes);}
    public FastExample.Criteria<SearchDatasource> datasourcePassword(String... datasourcePasswords){return fastExample.field("datasourcePassword").valEqual(datasourcePasswords);}
    public FastExample.Criteria<SearchDatasource> updateTime(Date... updateTimes){return fastExample.field("updateTime").valEqual(updateTimes);}
    public FastExample.Criteria<SearchDatasource> id(Long... ids){return fastExample.field("id").valEqual(ids);}
    public FastExample.Criteria<SearchDatasource> datasourceName(String... datasourceNames){return fastExample.field("datasourceName").valEqual(datasourceNames);}
    public FastExample.Criteria<SearchDatasource> datasourceUrl(String... datasourceUrls){return fastExample.field("datasourceUrl").valEqual(datasourceUrls);}

}