package com.gk.ims.user.plan.pojo;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

/**
* 用户搜索方案配置表
*/
@Data
@Accessors(chain=true)
@Table(name = "se_search_plan_new_condition")
public class SeSearchPlanNewCondition implements Serializable {

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
    *页面标签
    */
    @Column(name = "page_tag")
    private String pageTag;

    /**
    *查询列ID
    */
    @Column(name = "search_config_info_id")
    private Long searchConfigInfoId;

    /**
    *用户方案表主键
    */
    @Column(name = "search_plan_id")
    private Long searchPlanId;

    /**
    *搜索配置列名
    */
    @Column(name = "search_column")
    private String searchColumn;

    /**
    *搜索条件
    */
    @Column(name = "search_condition")
    private String searchCondition;

    /**
    *查询条件分组
    */
    @Column(name = "search_group")
    private String searchGroup;

    /**
    *查询条件分组方式(and or)
    */
    @Column(name = "search_group_relation")
    private String searchGroupRelation;

    /**
    *查询父条件分组
    */
    @Column(name = "parent_group")
    private String parentGroup;

    /**
    *查询父条件分组方式(and or)
    */
    @Column(name = "parent_group_relation")
    private String parentGroupRelation;

    /**
    *搜索参数
    */
    @Column(name = "search_value")
    private String searchValue;

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
