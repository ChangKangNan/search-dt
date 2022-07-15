package com.gk.ims.user.plan.pojo;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

/**
* 用户搜索方案表
*/
@Data
@Accessors(chain=true)
@Table(name = "se_search_plan_new")
public class SeSearchPlanNew implements Serializable {

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
    *用户账号
    */
    @Column(name = "user_account")
    private String userAccount;

    /**
    *方案名称
    */
    @Column(name = "plan_name")
    private String planName;

    /**
    *页面标签
    */
    @Column(name = "page_tag")
    private String pageTag;

    /**
    *版本号
    */
    @Column(name = "version")
    private String version;

    /**
    *自定义标记
    */
    @Column(name = "custom_marker")
    private String customMarker;

    /**
    *自定义搜索SQL
    */
    @Column(name = "search_sql")
    private String searchSql;

    /**
    *条件排序
    */
    @Column(name = "sort_num")
    private Integer sortNum;

    /**
    *是否为默认方案
    */
    @Column(name = "def")
    private Boolean def;

    /**
    *创建者所在组织
    */
    @Column(name = "create_org")
    private Long createOrg;

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
