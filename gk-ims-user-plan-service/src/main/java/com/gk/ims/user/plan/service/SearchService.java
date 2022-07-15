package com.gk.ims.user.plan.service;

import com.gk.ims.user.plan.api.dto.SearchQueryDTO;
import com.gk.ims.user.plan.api.dto.SearchResult;
import com.gk.ims.user.plan.api.dto.SearchSavePlanDTO;
import com.gk.ims.user.plan.dto.UserPlanDTO;
import com.gk.ims.user.plan.dto.UserSearchPageConfigQueryDTO;
import com.gk.ims.user.plan.dto.vo.SearchPageConfigVO;

import java.util.List;


/**
 * @author ckn
 * @date 2022/7/1
 */
public interface SearchService {
        /**
         * 保存用户方案
         * @param searchSavePlanDTO
         * @return
         */
        SearchSavePlanDTO savePlan(SearchSavePlanDTO searchSavePlanDTO);

        /**
         * 删除用户方案
         */
        void deleteUserPlan(Long searchPlanId);
        /**
         * 根据用户方案进行搜索
         */
        SearchResult query(SearchQueryDTO searchQueryDTO);
        /**
         * 分割查询方案配置
         * column列单独一行不能换行 同时如有别名必须添加 as关键字
         * 虚拟表表名不能与内部表名重复
         * where条件自成一行,一行代表一个条件
         * extendTables 为虚拟表名集合,必须严格按照sql书写的先后顺序,若顺序错误则配置生成错误
         * @param pageTage
         * @param version
         */
        void splitSearch(String pageTage,String version,String[] extendTables);

        /**
         * 查询所有用户查询方案
         * @param userPlanDTO
         * @return
         */
        List<UserPlanDTO> queryPlan(UserPlanDTO userPlanDTO);

        /**
         * 获取用户页面搜索配置
         *
         * @param queryDTO 查询dto
         * @return {@link SearchPageConfigVO}
         */
        SearchPageConfigVO getUserPageSearchConfig(UserSearchPageConfigQueryDTO queryDTO);
}
