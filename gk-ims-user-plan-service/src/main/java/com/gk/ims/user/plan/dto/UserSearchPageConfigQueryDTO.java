package com.gk.ims.user.plan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户页面搜索配置查询对象
 *
 * @author 张亚伟
 * @date 2020/07/10
 */
@ApiModel("用户页面搜索配置查询对象")
@Data
public class UserSearchPageConfigQueryDTO implements Serializable {

    private static final long serialVersionUID = -6648341316819932259L;


    @ApiModelProperty(value = "页面标签",required=true)
    @NotBlank(message = "页面标签不能为空")
    private String pageTag;

    /**
     *自定义标记
     */
    @ApiModelProperty("自定义标记")
    private String customMarker;


    @ApiModelProperty(value = "用户账号",required=true)
    @NotBlank(message = "用户账号不能为空")
    private String userAccount;

    @ApiModelProperty(value = "租户ID,通过不同的租户来切换数据源", required = true)
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

    @ApiModelProperty(value = "版本号,需要版本则传版本号,无需则默认当前版本对应的版本")
    private String version;
}
