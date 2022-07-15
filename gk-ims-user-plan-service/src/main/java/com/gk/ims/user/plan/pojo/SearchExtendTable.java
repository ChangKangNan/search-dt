package com.gk.ims.user.plan.pojo;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

/**
* 扩展表
*/
@Data
@Accessors(chain=true)
@Table(name = "search_extend_table")
public class SearchExtendTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    @Id
    @Column(name = "id")
    private Long id;

    /**
    *关联关系表头ID
    */
    @Column(name = "search_table_main_id")
    private Long searchTableMainId;

    /**
    *扩展表主键
    */
    @Column(name = "extend_table_id")
    private String extendTableId;

    /**
    *扩展表名称
    */
    @Column(name = "extend_table_name")
    private String extendTableName;

    /**
    *表sql
    */
    @Column(name = "extend_sql")
    private String extendSql;

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
