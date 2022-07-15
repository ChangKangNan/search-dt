package com.gk.ims.user.plan.pojo;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

/**
* 搜索定义表
*/
@Data
@Accessors(chain=true)
@Table(name = "se_search_config")
public class SeSearchConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    @Id
    @Column(name = "id")
    private Long id;

    /**
    *全局ID
    */
    @Column(name = "global_id")
    private Long globalId;

    /**
    *配置名称
    */
    @Column(name = "config_name")
    private String configName;

    /**
    *页面标签
    */
    @Column(name = "page_tag")
    private String pageTag;

    /**
    *版本
    */
    @Column(name = "version")
    private String version;

    /**
    *自定义搜索SQL
    */
    @Column(name = "search_sql")
    private String searchSql;

    /**
    *组织权限限制
    */
    @Column(name = "use_org_limit_table_alias")
    private String useOrgLimitTableAlias;

    /**
    *组织权限限制
    */
    @Column(name = "use_orgs_limit_table_alias")
    private String useOrgsLimitTableAlias;

    /**
    *备注
    */
    @Column(name = "remark")
    private String remark;

    /**
    *创建时间
    */
    @Column(name = "create_time")
    private Date createTime;

    /**
    *更新时间
    */
    @Column(name = "updata_time")
    private Date updataTime;

    /**
    *是否删除
    */
    @Column(name = "deleted")
    private Boolean deleted;


}
