package com.gk.ims.user.plan.pojo;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

/**
* 初始条件表
*/
@Data
@Accessors(chain=true)
@Table(name = "search_table_where")
public class SearchTableWhere implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    @Id
    @Column(name = "id")
    private Long id;

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
    *(AND,OR)
    */
    @Column(name = "relation")
    private String relation;

    /**
    *条件
    */
    @Column(name = "table_where")
    private String tableWhere;

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
