package com.gk.ims.user.plan.pojo;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

/**
* 查询方案数据源表
*/
@Data
@Accessors(chain=true)
@Table(name = "search_datasource")
public class SearchDatasource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    @Id
    @Column(name = "id")
    private Long id;

    /**
    *数据源名称
    */
    @Column(name = "datasource_name")
    private String datasourceName;

    /**
    *数据源驱动
    */
    @Column(name = "datasource_driver")
    private String datasourceDriver;

    /**
    *数据源url
    */
    @Column(name = "datasource_url")
    private String datasourceUrl;

    /**
    *用户名
    */
    @Column(name = "datasource_username")
    private String datasourceUsername;

    /**
    *密码
    */
    @Column(name = "datasource_password")
    private String datasourcePassword;

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
