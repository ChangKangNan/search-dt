package com.gk.ims.user.plan.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
* 搜索配置设置表
*/
@Data
@Accessors(chain=true)
@Table(name = "se_search_config_info")
public class SearchConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    @Id
    @Column(name = "id")
    private Long id;

    /**
    *搜索配置主键
    */
    @Column(name = "search_config_id")
    private Long searchConfigId;

    /**
    *页面标签
    */
    @Column(name = "page_tag")
    private String pageTag;

    /**
    *搜索显示名
    */
    @Column(name = "search_name")
    private String searchName;

    /**
    *搜索字段名
    */
    @Column(name = "search_key")
    private String searchKey;

    /**
    *限定搜索条件
    Equal("等于", " = "),
    NotEqual("不等于", " != "),
    Like("包含", " LIKE "),
    NotLike("不包含", " NOT LIKE "),
    In("介于", " IN "),
    NotIn("不介于", " NOT IN "),
    Greater("大于", " > "),
    GreaterOrEqual("大于等于", " >= "),
    Less("小于", " < "),
    LessOrEqual("小于等于", " <= ");
    */
    @Column(name = "limit_search_conditions")
    private String limitSearchConditions;

    /**
    *限定搜索值
    */
    @Column(name = "limit_search_values")
    private String limitSearchValues;

    /**
    *搜索值数据来源（in：输入，dictionary：数据字典，values：限制输入值，url：链接）
    */
    @Column(name = "search_value_source")
    private String searchValueSource;

    /**
    *搜索条件类型
输入框(input)
下拉框(select)
数值输入框(number)
日期框(date)
日期框特别标识(今天:#{day},本周:#{week},上周:#{last_week},本月:#{month},上月:#{last_month})
    */
    @Column(name = "search_type")
    private String searchType;

    /**
    *条件排序
    */
    @Column(name = "sort_num")
    private Integer sortNum;

    /**
    *是否隐藏
    */
    @Column(name = "hidden")
    private Boolean hidden;

    /**
     *是否为排序字段
     */
    @Column(name = "default_order_by")
    private Boolean defaultOrderBy;

    /**
     *是否倒序
     */
    @Column(name = "default_order_by_desc")
    private Boolean defaultOrderByDesc;

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
