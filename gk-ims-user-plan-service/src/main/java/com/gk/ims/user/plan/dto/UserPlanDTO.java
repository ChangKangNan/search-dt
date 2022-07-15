package com.gk.ims.user.plan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ckn
 * @date 2022/7/6
 */
@ApiModel("用户方案头部封装")
@Data
public class UserPlanDTO implements Serializable {

    private static final long serialVersionUID = -505258129712L;
    @ApiModelProperty(value = "方案Id")
    private Long id;
    @ApiModelProperty(value = "方案名称")
    private String planName;

    @ApiModelProperty(value = "版本号")
    private String version;

    @NotNull(message = "页面标签不能为空")
    @ApiModelProperty(value = "页面标签", required = true)
    private String pageTag;

    /**
     *自定义标记
     */
    @ApiModelProperty("自定义标记")
    private String customMarker;
}
