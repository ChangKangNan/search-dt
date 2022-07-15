package com.gk.ims.user.plan.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* 搜索配置设置表
*/
@Data
@Accessors(chain=true)
public class SearchConfigInfoDTO implements Serializable {


    private static final long serialVersionUID = -455460055633372991L;
    /**
    *主键
    */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
    *搜索配置主键
    */
    @ApiModelProperty(value = "搜索配置主键")
    private Long searchConfigId;

    /**
    *页面标签
    */
    @ApiModelProperty(value = "页面标签")
    private String pageTag;

    /**
    *搜索显示名
    */
    @ApiModelProperty(value = "搜索显示名")
    private String searchName;

    /**
    *搜索字段名
    */
    @ApiModelProperty(value = "搜索字段名")
    private String searchKey;

    /**
    *限定搜索条件
    */
    @ApiModelProperty(value = "限定搜索条件")
    private String limitSearchConditions;

    /**
    *限定搜索值
    */
    @ApiModelProperty(value = "限定搜索值")
    private String limitSearchValues;

    /**
    *搜索值数据来源（in：输入，dictionary：数据字典，values：限制输入值，url：链接）
    */
    @ApiModelProperty(value = "in：输入，dictionary：数据字典，values：限制输入值，url：链接")
    private String searchValueSource;

    /**
    *搜索条件类型
    */
    @ApiModelProperty(value = "搜索条件类型")
    private String searchType;

    /**
    *条件排序
    */
    @ApiModelProperty(value = "条件排序")
    private Integer sortNum;

    /**
     *是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

    /**
     *是否为排序字段
     */
    @ApiModelProperty(value = "是否为排序字段")
    private Boolean defaultOrderBy;

    /**
     *是否倒序
     */
    @ApiModelProperty(value = "是否倒序")
    private Boolean defaultOrderByDesc;

}
