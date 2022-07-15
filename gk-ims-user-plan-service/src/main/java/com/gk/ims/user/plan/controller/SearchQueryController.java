package com.gk.ims.user.plan.controller;

import com.gk.ims.config.login.WebUtil;
import com.gk.ims.user.plan.api.dto.SearchQueryDTO;
import com.gk.ims.user.plan.api.dto.SearchResult;
import com.gk.ims.user.plan.api.service.IApiSearchQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 搜索查询控制器
 *
 * @author 张亚伟
 * @date 2020/07/14
 */
@Api(tags = "搜索查询")
@RestController
@RequestMapping("/search")
public class SearchQueryController {

    @Resource
    private IApiSearchQueryService queryService;

    @ApiOperation(value = "查询入口")
    @PostMapping("/query")
    public SearchResult query(@RequestBody SearchQueryDTO queryDTO) {
        queryDTO.setOrgId(WebUtil.getUser().getOrganizationId());
        queryDTO.setUserAccount(WebUtil.getUser().getUsername());
        return queryService.query(queryDTO);
    }

}
