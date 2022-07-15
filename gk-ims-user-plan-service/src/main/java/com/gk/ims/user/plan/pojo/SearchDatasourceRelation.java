package com.gk.ims.user.plan.pojo;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

/**
* 查询方案数据源关系表
*/
@Data
@Accessors(chain=true)
@Table(name = "search_datasource_relation")
public class SearchDatasourceRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    @Id
    @Column(name = "id")
    private Long id;

    /**
    *数据源ID
    */
    @Column(name = "search_datasource_id")
    private Long searchDatasourceId;

    /**
    *查询方案ID
    */
    @Column(name = "se_search_config_id")
    private Long seSearchConfigId;

    /**
    *创建时间
    */
    @Column(name = "create_time")
    private Date createTime;

    /**
    *更新时间
    */
    @Column(name = "update_time")
    private Date updateTime;

    /**
    *是否删除
    */
    @Column(name = "deleted")
    private Boolean deleted;


}
