package com.gk.ims.user.plan.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 查询数据封装
 *
 * @author ckn
 * @date 2022/7/5
 */
@Data
@ApiModel("查询数据封装")
public class SearchQueryDTO implements Serializable {

    private static final long serialVersionUID = -5052581297195319622L;
    @ApiModelProperty(value = "页数")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "版本号")
    private String version;

    @NotNull(message = "页面标签不能为空")
    @ApiModelProperty(value = "页面标签", required = true)
    private String pageTag;

    @ApiModelProperty(value = "排序字段")
    private String orderBy;
    @ApiModelProperty(value = "是否倒序")
    private Boolean desc = false;

    @ApiModelProperty(value = "租户ID,通过不同的租户来切换数据源")
    private Long orgId;

    @ApiModelProperty(value = "用户账号")
    private String userAccount;

    @ApiModelProperty(value = "搜索方案ID,如无方案传0", required = true)
    private Long searchPlanId;

    @ApiModelProperty(value = "缓存刷新", required = true)
    private Boolean refreshCache;

    @ApiModelProperty(value = "读取缓存", required = true)
    private Boolean readCache = true;

    @ApiModelProperty(value = "查询内容")
    private List<QueryInfo> queryInfoList;

    @ApiModelProperty(value = "列状态")
    private List<SearchColumn> searchColumns;


    @Data
    public static class SearchColumn implements Serializable{
        private static final long serialVersionUID = 769958333733301366L;
        public SearchColumn(){}
        public SearchColumn(String columnName, String status) {
            this.columnName = columnName;
            this.status = status;
        }

        @ApiModelProperty(value = "列名")
        private String columnName;

        @ApiModelProperty(value = "状态(on:显示列,close:不显示的列,hidden:需要却不显示的列)")
        private String status;
    }


    @Data
    public static class QueryInfo implements Serializable{
        private static final long serialVersionUID = 7699583337155501366L;
        public  QueryInfo(){}

        public QueryInfo(String searchKey, Long searchConfigInfoId, String searchCondition, String searchValue, String searchGroup, String searchGroupRelation, String parentGroup, String parentGroupRelation) {
            this.searchKey = searchKey;
            this.searchConfigInfoId = searchConfigInfoId;
            this.searchCondition = searchCondition;
            this.searchValue = searchValue;
            this.searchGroup = searchGroup;
            this.searchGroupRelation = searchGroupRelation;
            this.parentGroup = parentGroup;
            this.parentGroupRelation = parentGroupRelation;
        }

        /**
         * 搜索字段名
         */
        @ApiModelProperty(value = "搜索字段名")
        private String searchKey;

        /**
         * 搜索字段名ID
         */
        @ApiModelProperty(value = "搜索字段名ID")
        private Long searchConfigInfoId;

        /**
         * 搜索条件
         */
        @ApiModelProperty(value = "搜索条件")
        private String searchCondition;

        /**
         * 搜索参数
         */
        @ApiModelProperty(value = "搜索参数")
        private String searchValue;

        /**
         *查询条件分组
         */
        @ApiModelProperty(value = "查询条件分组")
        private String searchGroup;

        /**
         *查询条件分组方式(and or)
         */
        @ApiModelProperty(value = "查询条件分组方式(and or)")
        private String searchGroupRelation;

        /**
         *查询父条件分组
         */
        @ApiModelProperty(value = "searchGroup(前一个条件的分组Id)")
        private String parentGroup;

        /**
         *查询父条件分组方式(and or)
         */
        @ApiModelProperty(value = "分组方式(and or)")
        private String parentGroupRelation;
    }


}
