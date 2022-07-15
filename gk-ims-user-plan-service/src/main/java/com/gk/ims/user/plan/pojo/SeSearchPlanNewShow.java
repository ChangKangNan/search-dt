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
@Table(name = "se_search_plan_new_show")
public class SeSearchPlanNewShow implements Serializable {

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
    *{on:显示列,close:不显示的列,hidden:需要却不显示的列}
    */
    @Column(name = "show_status")
    private String showStatus;

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
