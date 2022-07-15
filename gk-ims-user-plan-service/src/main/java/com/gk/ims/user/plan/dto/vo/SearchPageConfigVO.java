package com.gk.ims.user.plan.dto.vo;

import com.gk.ims.user.plan.api.dto.SearchResult;
import com.gk.ims.user.plan.dto.SearchConfigInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 页面搜索配置
 *
 * @author 张亚伟
 * @date 2020/07/14
 */
@Data
@ApiModel("页面搜索配置")
public class SearchPageConfigVO implements Serializable {
    private static final long serialVersionUID = -2172473774130171809L;

    @ApiModelProperty("页面搜索配置")
    private List<SearchConfigInfoDTO> configInfoList;

    @ApiModelProperty("默认搜索结果,暂时废弃")
    private SearchResult defSearchResult;
}
