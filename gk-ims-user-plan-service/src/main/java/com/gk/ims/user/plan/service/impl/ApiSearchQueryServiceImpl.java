package com.gk.ims.user.plan.service.impl;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.fast.config.FastDaoAttributes;
import com.fast.fast.FastCustomSqlDao;
import com.fast.utils.page.PageInfo;
import com.gk.ims.user.plan.api.dto.SearchQueryDTO;
import com.gk.ims.user.plan.api.dto.SearchResult;
import com.gk.ims.user.plan.api.service.IApiSearchQueryService;
import com.gk.ims.user.plan.pojo.SeSearchConfig;
import com.gk.ims.user.plan.pojo.fast.SeSearchConfigFastDAO;
import com.gk.ims.user.plan.util.SearchQuerySqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询服务
 *
 * @author 张亚伟
 * @date 2020/07/15
 */
@Slf4j
@Service
@DubboService
public class ApiSearchQueryServiceImpl implements IApiSearchQueryService {
    /**
     * 查询
     *
     * @param queryDTO 查询dto
     * @return {@link SearchResult}
     */
    @Override
    public SearchResult query(SearchQueryDTO queryDTO) {
        if (queryDTO.getSearchPlanId() == null) {
            queryDTO.setSearchPlanId(0L);
        }
        SeSearchConfigFastDAO searchConfigFastDAO = SeSearchConfigFastDAO.create();
        searchConfigFastDAO.pageTag(queryDTO.getPageTag());
        if (StrUtil.isBlank(queryDTO.getVersion())) {
            searchConfigFastDAO.version().isNull();
        } else {
            searchConfigFastDAO.version(queryDTO.getVersion());
        }
        SeSearchConfig searchConfig = searchConfigFastDAO.dao().findOne();
        if (StrUtil.isBlank(searchConfig.getSearchSql())) {
            return null;
        }
        //解析查询SQL
        Map<String, Object> paramMap = new HashMap<>();
        StrBuilder sql = new StrBuilder();
        sql.append(searchConfig.getSearchSql());
        SearchQuerySqlUtil.where(queryDTO.getQueryInfoList(), sql, paramMap);
        SearchQuerySqlUtil.dataPermission(searchConfig, sql);
        SearchServiceImpl.dataSourceChange(searchConfig);
        SearchResult searchResult = getSearchResult(queryDTO, paramMap, sql);
        FastDaoAttributes.setDataSource(null);
        return searchResult;
    }

    public SearchResult getSearchResult(SearchQueryDTO queryDTO, Map<String, Object> paramMap, StrBuilder sql) {
        //创建查询
        PageInfo<Map> page;
        FastCustomSqlDao<Map> customSqlDao = FastCustomSqlDao.create(Map.class, sql.toString(), paramMap);
        if (queryDTO.getPageNum() != null && queryDTO.getPageSize() != null) {
            page = customSqlDao.findPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        } else {
            List<Map> searchData = customSqlDao.findAll();
            page = new PageInfo<>(searchData);
        }
        return new SearchResult(page, queryDTO);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public SearchResult transactionalQuery(SearchQueryDTO queryDTO) {
        return query(queryDTO);
    }

}
