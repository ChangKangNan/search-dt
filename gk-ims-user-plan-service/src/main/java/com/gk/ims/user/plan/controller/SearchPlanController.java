package com.gk.ims.user.plan.controller;

import com.fast.utils.FastResult;
import com.gk.ims.user.plan.api.dto.SearchQueryDTO;
import com.gk.ims.user.plan.api.dto.SearchResult;
import com.gk.ims.user.plan.api.dto.SearchSavePlanDTO;
import com.gk.ims.user.plan.dto.UserPlanDTO;
import com.gk.ims.user.plan.dto.UserSearchPageConfigQueryDTO;
import com.gk.ims.user.plan.dto.vo.SearchPageConfigVO;
import com.gk.ims.user.plan.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索配置控制器
 * 获取用户页面配置信息
 * @author ckn
 * @date 2022/07/6
 */
@Api(tags = "搜索配置")
@RestController
@RequestMapping("/searchPlan")
public class SearchPlanController {

    @Autowired
    SearchService searchService;

    @ApiOperation(value = "查询用户保存的用户方案")
    @GetMapping("/queryPlans")
    FastResult<List<UserPlanDTO>> queryPlan(UserPlanDTO userPlanDTO){
        return FastResult.success(searchService.queryPlan(userPlanDTO));
    }


    @ApiOperation(value = "根据用户方案ID查询入口")
    @PostMapping("/query")
    public SearchResult query(@RequestBody SearchQueryDTO queryDTO) {
       return searchService.query(queryDTO);
    }

    /**
     * 保存用户页面搜索配置
     *
     * @param saveDTO 保存dto
     * @return {@link FastResult <SearchSavePlanDTO>}
     */
    @ApiOperation(value = "保存用户页面搜索配置", produces = "application/json")
    @PostMapping
    public FastResult<SearchSavePlanDTO> save(@Validated @RequestBody SearchSavePlanDTO saveDTO) {
        return FastResult.success(searchService.savePlan(saveDTO));
    }


    /**
     * 删除搜索配置
     * @return
     */
    @ApiOperation(value = "删除用户页面搜索配置")
    @DeleteMapping
    public FastResult<Boolean> del(Long planId){
        searchService.deleteUserPlan(planId);
        return FastResult.success(true);
    }

    /**
     * 获取用户页面搜索配置
     *
     * @param queryDTO 查询dto
     * @return {@link FastResult<SearchPageConfigVO>}
     */
    @ApiOperation(value = "获取用户页面搜索配置")
    @GetMapping("/config")
    public FastResult<SearchPageConfigVO> get(@Validated UserSearchPageConfigQueryDTO queryDTO) {
        SearchPageConfigVO vo = searchService.getUserPageSearchConfig(queryDTO);
        return FastResult.success(vo);
    }
}
