package com.gk.ims.user.plan.pojo;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

/**
* 关联关系表-表体
*/
@Data
@Accessors(chain=true)
@Table(name = "search_table_main_relation")
public class SearchTableMainRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    @Id
    @Column(name = "id")
    private Long id;

    /**
    *主表外键
    */
    @Column(name = "search_table_main_id")
    private Long searchTableMainId;

    /**
    *来源表名
    */
    @Column(name = "source_table_name")
    private String sourceTableName;

    /**
    *来源表别名
    */
    @Column(name = "source_table_alias")
    private String sourceTableAlias;

    /**
    *目标表名
    */
    @Column(name = "target_table_name")
    private String targetTableName;

    /**
    *目标表别名
    */
    @Column(name = "target_table_alias")
    private String targetTableAlias;

    /**
    *来源表名关联列名
    */
    @Column(name = "source_table_column_name")
    private String sourceTableColumnName;

    /**
    *目标表名关联列名
    */
    @Column(name = "target_table_column_name")
    private String targetTableColumnName;

    /**
    *目标表是否是扩展表
    */
    @Column(name = "is_extend")
    private Boolean isExtend;

    /**
    *上级关系Id
    */
    @Column(name = "parent_relation_id")
    private Long parentRelationId;

    /**
    *备注
    */
    @Column(name = "remark")
    private String remark;

    /**
    *创建人
    */
    @Column(name = "create_by")
    private String createBy;

    /**
    *更新人
    */
    @Column(name = "update_by")
    private String updateBy;

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
