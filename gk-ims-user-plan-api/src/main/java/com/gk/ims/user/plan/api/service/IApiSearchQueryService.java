package com.gk.ims.user.plan.api.service;

import com.fast.utils.FastResult;
import com.gk.ims.user.plan.api.dto.SearchQueryDTO;
import com.gk.ims.user.plan.api.dto.SearchResult;


/**
 * 查询服务
 *
 * @author 张亚伟
 * @date 2020/07/15
 */
public interface IApiSearchQueryService {

    /**
     *查询
     *
     * @param queryDTO 查询条件封装
     * @return {@link FastResult}
     */
    SearchResult query(SearchQueryDTO queryDTO);

    /**
     *查询
     *
     * @param queryDTO 查询条件封装
     * @return {@link FastResult}
     */
    SearchResult transactionalQuery(SearchQueryDTO queryDTO);

}
