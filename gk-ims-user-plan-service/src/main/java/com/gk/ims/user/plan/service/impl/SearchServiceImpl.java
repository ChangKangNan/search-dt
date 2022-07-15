package com.gk.ims.user.plan.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.fast.config.FastDaoAttributes;
import com.fast.fast.FastCustomSqlDao;
import com.fast.utils.BeanCopyUtil;
import com.fast.utils.page.PageInfo;
import com.gk.ims.config.ResultException;
import com.gk.ims.config.login.WebUtil;
import com.gk.ims.user.plan.api.dto.SearchQueryDTO;
import com.gk.ims.user.plan.api.dto.SearchResult;
import com.gk.ims.user.plan.api.dto.SearchSavePlanDTO;
import com.gk.ims.user.plan.dto.AliasDTO;
import com.gk.ims.user.plan.dto.SearchConfigInfoDTO;
import com.gk.ims.user.plan.dto.UserPlanDTO;
import com.gk.ims.user.plan.dto.UserSearchPageConfigQueryDTO;
import com.gk.ims.user.plan.dto.vo.SearchPageConfigVO;
import com.gk.ims.user.plan.pojo.*;
import com.gk.ims.user.plan.pojo.fast.*;
import com.gk.ims.user.plan.service.SearchService;
import com.gk.ims.user.plan.util.SQLUtils;
import com.gk.ims.user.plan.util.SearchQuerySqlUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ckn
 * @date 2022/7/1
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Override
    @Transactional
    public SearchSavePlanDTO savePlan(SearchSavePlanDTO searchSavePlanDTO) {
        Long searchSavePlanDTOId = searchSavePlanDTO.getId();
        checkDef(searchSavePlanDTOId);
        deleteUserPlan(searchSavePlanDTOId);
        List<SearchSavePlanDTO.SearchColumn> searchColumns = searchSavePlanDTO.getSearchColumns();
        String pageTag = searchSavePlanDTO.getPageTag();
        String version = searchSavePlanDTO.getVersion();
        List<String> columnNames = new ArrayList<>();
        if (CollUtil.isNotEmpty(searchColumns)) {
            for (SearchSavePlanDTO.SearchColumn searchColumn : searchColumns) {
                if (StrUtil.equalsAny(searchColumn.getStatus(), "on", "hidden")) {
                    columnNames.add(searchColumn.getColumnName());
                }
            }
        }
        String packageSql = SQLUtils.packageSql(pageTag, version, columnNames);
        SeSearchPlanNew seSearchPlanNew = BeanCopyUtil.copy(searchSavePlanDTO, SeSearchPlanNew.class);
        seSearchPlanNew.setSearchSql(packageSql);
        if(searchSavePlanDTOId==null){
            seSearchPlanNew.setId(null);
            SeSearchPlanNewFastDAO.create().dao().insert(seSearchPlanNew);
        }else {
            SeSearchPlanNewFastDAO.create().id(seSearchPlanNew.getId()).dao().updateOverwrite(seSearchPlanNew);
        }
        Long searchPlanNewId = seSearchPlanNew.getId();
        if (CollUtil.isNotEmpty(searchColumns)) {
            for (SearchSavePlanDTO.SearchColumn searchColumn : searchColumns) {
                SeSearchPlanNewShow seSearchPlanNewShow = new SeSearchPlanNewShow();
                seSearchPlanNewShow.setSearchPlanId(searchPlanNewId);
                seSearchPlanNewShow.setUserAccount(seSearchPlanNew.getUserAccount());
                seSearchPlanNewShow.setSearchColumn(searchColumn.getColumnName());
                seSearchPlanNewShow.setShowStatus(searchColumn.getStatus());
                seSearchPlanNewShow.setPageTag(searchSavePlanDTO.getPageTag());
                SeSearchPlanNewShowFastDAO.create().dao().insert(seSearchPlanNewShow);
            }
        }
        List<SearchSavePlanDTO.QueryInfo> queryInfoList = searchSavePlanDTO.getQueryInfoList();
        if (CollUtil.isNotEmpty(queryInfoList)) {
            for (SearchSavePlanDTO.QueryInfo queryInfo : queryInfoList) {
                SeSearchPlanNewCondition seSearchPlanNewCondition = new SeSearchPlanNewCondition();
                seSearchPlanNewCondition.setSearchColumn(queryInfo.getSearchKey());
                seSearchPlanNewCondition.setSearchConfigInfoId(queryInfo.getSearchConfigInfoId());
                seSearchPlanNewCondition.setUserAccount(seSearchPlanNew.getUserAccount());
                seSearchPlanNewCondition.setSearchPlanId(searchPlanNewId);
                seSearchPlanNewCondition.setPageTag(searchSavePlanDTO.getPageTag());
                seSearchPlanNewCondition.setSearchValue(queryInfo.getSearchValue());
                seSearchPlanNewCondition.setSearchCondition(queryInfo.getSearchCondition());
                seSearchPlanNewCondition.setSearchGroup(queryInfo.getSearchGroup());
                seSearchPlanNewCondition.setSearchGroupRelation(queryInfo.getSearchGroupRelation());
                seSearchPlanNewCondition.setParentGroup(queryInfo.getParentGroup());
                seSearchPlanNewCondition.setParentGroupRelation(queryInfo.getParentGroupRelation());
                SeSearchPlanNewConditionFastDAO.create().dao().insert(seSearchPlanNewCondition);
            }
        }
        searchSavePlanDTO.setId(searchPlanNewId);
        return searchSavePlanDTO;
    }

    public void checkDef(Long id){
        SeSearchPlanNew searchPlanNew = SeSearchPlanNewFastDAO.create().id(id).dao().findOne();
        if(id !=null && searchPlanNew !=null){
            Boolean def = searchPlanNew.getDef();
            if(def){
                throw new ResultException("默认方案不允许更新!");
            }
        }
    }

    @Override
    public void deleteUserPlan(Long searchPlanId) {
        if (searchPlanId != null) {
            SeSearchPlanNewConditionFastDAO searchPlanNewConditionFastDAO = SeSearchPlanNewConditionFastDAO.create();
            searchPlanNewConditionFastDAO.closeLogicDeleteProtect();
            searchPlanNewConditionFastDAO.searchPlanId(searchPlanId).dao().delete();
            SeSearchPlanNewShowFastDAO seSearchPlanNewShowFastDAO = SeSearchPlanNewShowFastDAO.create();
            seSearchPlanNewShowFastDAO.closeLogicDeleteProtect();
            seSearchPlanNewShowFastDAO.searchPlanId(searchPlanId).dao().delete();
        }
    }

    @Override
    public SearchResult query(SearchQueryDTO searchQueryDTO) {
        Long searchPlanId = searchQueryDTO.getSearchPlanId();
        SeSearchPlanNew plan = SeSearchPlanNewFastDAO.create().id(searchPlanId).dao().findOne();
        String pageTag = plan.getPageTag();
        String version = plan.getVersion();
        String packageSql = plan.getSearchSql();
        StrBuilder stringBuilder = new StrBuilder(packageSql);
        Map<String, Object> paramMap = new HashMap<>();
        List<SeSearchPlanNewCondition> seSearchPlanNewConditions = SeSearchPlanNewConditionFastDAO.create().searchPlanId(searchPlanId).dao().findAll();
        List<SearchQueryDTO.QueryInfo> infoList = new ArrayList<>();
        for (SeSearchPlanNewCondition searchPlanNewCondition : seSearchPlanNewConditions) {
            infoList.add(new SearchQueryDTO.QueryInfo(searchPlanNewCondition.getSearchColumn(),
                    searchPlanNewCondition.getSearchConfigInfoId(),
                    searchPlanNewCondition.getSearchCondition(), searchPlanNewCondition.getSearchValue(),
                    searchPlanNewCondition.getSearchGroup(), searchPlanNewCondition.getSearchGroupRelation(),
                    searchPlanNewCondition.getParentGroup(), searchPlanNewCondition.getParentGroupRelation()));
        }
        SearchQuerySqlUtil.where(infoList, stringBuilder, paramMap);
        SeSearchConfigFastDAO seSearchConfigFastDAO = SeSearchConfigFastDAO.create();
        seSearchConfigFastDAO.pageTag(pageTag);
        seSearchConfigFastDAO.version(version);
        SeSearchConfig searchConfig = seSearchConfigFastDAO.dao().findOne();
        List<SeSearchPlanNewShow> searchPlanNewShows = SeSearchPlanNewShowFastDAO.create().searchPlanId(plan.getId()).dao().findAll();
        SearchQuerySqlUtil.dataPermission(searchConfig, stringBuilder);
        switchDataSource(searchConfig);
        SearchResult searchResult = getSearchResult(searchQueryDTO, paramMap, stringBuilder);
        searchResult.setConditions(infoList);
        List<SearchQueryDTO.SearchColumn> columnList=new ArrayList<>();
        if(CollUtil.isNotEmpty(columnList)){
            for (SeSearchPlanNewShow show : searchPlanNewShows) {
                SearchQueryDTO.SearchColumn s=new SearchQueryDTO.SearchColumn();
                s.setColumnName(show.getSearchColumn());
                s.setStatus(show.getShowStatus());
                columnList.add(s);
            }
        }
        searchResult.setColumns(columnList);
        FastDaoAttributes.setDataSource(null);
        return searchResult;
    }
    //切换数据源
    void switchDataSource(SeSearchConfig searchConfig){
        dataSourceChange(searchConfig);
    }

   public static void dataSourceChange(SeSearchConfig searchConfig) {
        SearchDatasourceRelation datasourceRelation = SearchDatasourceRelationFastDAO.create().seSearchConfigId(searchConfig.getId()).dao().findOne();
        SearchDatasource data = SearchDatasourceFastDAO.create().id(datasourceRelation.getSearchDatasourceId()).dao().findOne();
        DruidDataSource dataSource=new DruidDataSource();
        try {
            dataSource.setDriverClassName(data.getDatasourceDriver());
            dataSource.setUrl(data.getDatasourceUrl());
            dataSource.setUsername(data.getDatasourceUsername());
            dataSource.setPassword(data.getDatasourcePassword());
            FastDaoAttributes.setDataSource(dataSource);
        }catch (Exception e){}
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
    public void splitSearch(String pageTage, String version,String[] extendTables) {
        SeSearchConfigFastDAO searchConfigFastDAO = SeSearchConfigFastDAO.create();
        searchConfigFastDAO.pageTag(pageTage);
        searchConfigFastDAO.version(version);
        SeSearchConfig searchConfig = searchConfigFastDAO.dao().findOne();
        if (searchConfig != null) {
            String searchSql = searchConfig.getSearchSql();
            searchSql = searchSql.substring(0, searchSql.indexOf("from"));
            searchSql = StrUtil.replace(searchSql, "select", "");
            searchSql = StrUtil.replace(searchSql, "SELECT", "");
            System.out.println(searchSql);
            searchSql = searchSql.replaceAll("#(.*?)(\r\n)", "\r\n");
            System.out.println(searchSql);
            System.out.println("searchSql:" + searchSql);
            String[] columns = searchSql.split(",\r\n");
            List<SearchTableColumn> columnList = new ArrayList<>();
            for (String column : columns) {
                column=column.replaceAll("(\r\n)", "");
                Object[] array = Arrays.stream(column.split("as")).filter(StrUtil::isNotBlank).toArray();
                String otherName;
                SearchTableColumn searchTableColumn = new SearchTableColumn();
                searchTableColumn.setPageTag(pageTage);
                searchTableColumn.setVersion(version);
                searchTableColumn.setSearchTableColumnInfo(array[0].toString().trim());
                boolean isAlias=false;
                if (array.length > 1) {
                    otherName = array[1].toString().trim();
                    isAlias=true;
                } else {
                    otherName = array[0].toString().trim();
                }
                if(isAlias){
                    searchTableColumn.setSearchTableColumn(otherName);
                }else {
                    searchTableColumn.setSearchTableColumn(otherName.split("\\.")[1].trim());
                }
                columnList.add(searchTableColumn);
            }
            System.out.println(JSONUtil.toJsonStr(columnList));
            String formSQL = searchConfig.getSearchSql().substring(searchConfig.getSearchSql().indexOf("from"), searchConfig.getSearchSql().lastIndexOf("where"));
            System.out.println("formSQL:" + formSQL);
            formSQL=StrUtil.trimStart(formSQL);
            if(StrUtil.startWithAny(formSQL,"from","FROM")){
                formSQL = formSQL.substring(4);
            }
            System.out.println("formSQL:" + formSQL);
            AliasDTO aliasDTO = generateMainRelation(formSQL, extendTables, pageTage, version);
            SearchTableMain aliasDTOTableMain = aliasDTO.getTableMain();
            for (SearchTableColumn tableColumn : columnList) {
                String searchTableColumnInfo = tableColumn.getSearchTableColumnInfo();
                SearchTableColumnFastDAO.create().dao().insert(tableColumn);
                Set<String> tbSet = getMatchTable(searchTableColumnInfo);
                if (tbSet.size() > 0) {
                    for (String aliasName : tbSet) {
                        SearchTableMainRelationFastDAO relationFastDAO = SearchTableMainRelationFastDAO.create();
                        relationFastDAO.targetTableAlias(aliasName);
                        relationFastDAO.searchTableMainId(aliasDTOTableMain.getId());
                        SearchTableMainRelation tRelation = relationFastDAO.dao().findOne();
                        SearchTableColumnTarget searchTableColumnTarget = new SearchTableColumnTarget();
                        searchTableColumnTarget.setSearchTableColumnId(tableColumn.getId());
                        if(tRelation !=null){
                            searchTableColumnTarget.setSearchTableMainRelationId(tRelation.getId());
                        }else {
                            SearchTableMainRelationFastDAO rFastDAO = SearchTableMainRelationFastDAO.create();
                            rFastDAO.targetTableName(aliasName);
                            rFastDAO.searchTableMainId(aliasDTOTableMain.getId());
                            List<SearchTableMainRelation> relations = rFastDAO.dao().findAll();
                            if(relations.size()==1){
                                searchTableColumnTarget.setSearchTableMainRelationId(relations.get(0).getId());
                            }
                        }
                        if(searchTableColumnTarget.getSearchTableMainRelationId()!=null){
                            SearchTableColumnTargetFastDAO.create().dao().insert(searchTableColumnTarget);
                        }
                    }
                }
            }
            String whereSQL = searchConfig.getSearchSql().substring(searchConfig.getSearchSql().lastIndexOf("where"));
            whereSQL = StrUtil.replace(whereSQL, "where", "");
            whereSQL = StrUtil.replace(whereSQL, "WHERE", "");
            String regex="(\r\n)";
            String[] whereSplit = whereSQL.split(regex);
            for (String where : whereSplit) {
                where = StrUtil.trimStart(where);
                String relation = null;
                if (StrUtil.startWithAny(where, "and","AND","OR","or")) {
                    if(StrUtil.startWithAny(where, "OR","or")){relation="OR";where = where.substring(2);}
                    if(StrUtil.startWithAny(where, "and","AND")){relation="AND";where = where.substring(3);}
                }
                    SearchTableWhere searchTableWhere=new SearchTableWhere();
                    searchTableWhere.setTableWhere(where);
                    searchTableWhere.setPageTag(pageTage);
                    searchTableWhere.setVersion(version);
                    searchTableWhere.setRelation(relation);
                    SearchTableWhereFastDAO.create().dao().insert(searchTableWhere);
                    Set<String> tbSet = getMatchTable(where);
                    if (tbSet.size() > 0) {
                        for (String aliasName : tbSet) {
                            SearchTableMainRelationFastDAO relationFastDAO = SearchTableMainRelationFastDAO.create();
                            relationFastDAO.targetTableAlias(aliasName);
                            relationFastDAO.searchTableMainId(aliasDTOTableMain.getId());
                            SearchTableMainRelation tRelation = relationFastDAO.dao().findOne();
                            SearchTableWhereRelation searchTableWhereRelation = new SearchTableWhereRelation();
                            searchTableWhereRelation.setSearchTableWhereId(searchTableWhere.getId());
                            if(tRelation !=null){
                                searchTableWhereRelation.setSearchTableMainRelationId(tRelation.getId());
                            }else {
                                SearchTableMainRelationFastDAO rFastDAO = SearchTableMainRelationFastDAO.create();
                                rFastDAO.targetTableName(aliasName);
                                rFastDAO.searchTableMainId(aliasDTOTableMain.getId());
                                List<SearchTableMainRelation> relations = rFastDAO.dao().findAll();
                                if(relations.size()==1){
                                    searchTableWhereRelation.setSearchTableMainRelationId(relations.get(0).getId());
                                }
                            }
                            if(searchTableWhereRelation.getSearchTableMainRelationId()!=null){
                                SearchTableWhereRelationFastDAO.create().dao().insert(searchTableWhereRelation);
                            }
                        }
                    }
            }

        }
    }

   Set<String> getMatchTable(String sql){
       Pattern pattern = Pattern.compile("([\\w]+?\\.)|([\\+\\-\\*\\/(][\\w]+?\\.)");
       Matcher matcher = pattern.matcher(sql);
       Set<String> tbSet = new HashSet<>();
       while (matcher.find()) {
           String group = matcher.group();
           if (StrUtil.startWithAny(group, "+", "-", "*", "/", "(")) {
               group = group.substring(1);
           }
           if (StrUtil.endWithAny(group, ".")) {
               group = group.substring(0, group.length() - 1);
           }
           tbSet.add(group);
       }
       return tbSet;
   }

    AliasDTO generateMainRelation(String sql, String[] extendTables, String pageTage, String version) {
        Map<String, String> tableExtendMap = new HashMap<>();
        Map<String, String> aliasToTable = new HashMap<>();
        List<String> relationKey=new ArrayList<>();
        for (String extendTable : extendTables) {
            String substring = sql.substring(sql.indexOf("(")+1,sql.indexOf(extendTable));
            substring=StrUtil.trimEnd(substring);
            substring= substring.substring(0,substring.length()-1);
            System.out.println(substring);
            sql = sql.substring(0, sql.indexOf("(")) + " " + sql.substring(sql.indexOf(extendTable));
            tableExtendMap.put(extendTable, substring);
        }
        String[] split = sql.split("left join | LEFT JOIN");
        Map<String, String> aliasTooN = new HashMap<>();
        //主表信息建立
        String mainTableInfo = split[0];
        Object[] objects = Arrays.stream(mainTableInfo.split(" ")).filter(StrUtil::isNotBlank).toArray();
        String aliasMainName;
        boolean isMainAlias = false;
        String mainTable = objects[0].toString().trim();
        if (objects.length > 1) {
            aliasMainName = objects[1].toString().trim();
        } else {
            aliasMainName = mainTable.trim();
        }
        SearchTableMain searchTableMain = new SearchTableMain();
        searchTableMain.setSearchTableName(mainTable);
        if (!mainTable.equals(aliasMainName)) {
            isMainAlias = true;
            searchTableMain.setAlias(aliasMainName);
        }
        searchTableMain.setPageTag(pageTage);
        searchTableMain.setVersion(version);
        SearchTableMainFastDAO.create().dao().insert(searchTableMain);
        //扩展表关系建立
        for (String extend : tableExtendMap.keySet()) {
            SearchExtendTable searchExtendTable = new SearchExtendTable();
            searchExtendTable.setSearchTableMainId(searchTableMain.getId());
            searchExtendTable.setExtendSql(tableExtendMap.get(extend));
            searchExtendTable.setExtendTableName(extend);
            SearchExtendTableFastDAO.create().dao().insert(searchExtendTable);
        }
        //关联表信息建立
        int i = 0;
        for (String s : split) {
            i++;
            if (i > 1) {
                String[] strings = s.split(" on ");
                String table = StrUtil.trimStart(strings[0]);
                String[] tableSplit = table.split(" ");
                String on = strings[1];
                if (tableSplit.length > 1) {
                    aliasToTable.put(tableSplit[1].trim(), tableSplit[0].trim());
                    aliasTooN.put(tableSplit[1].trim(), on);
                    relationKey.add(tableSplit[1].trim());
                } else {
                    aliasToTable.put(tableSplit[0].trim(), tableSplit[0].trim());
                    aliasTooN.put(tableSplit[0].trim(), on);
                    relationKey.add(tableSplit[0].trim());
                }
            }
        }

        for (String targetTableAliasName : relationKey) {
            boolean isTargetAlias = false;
            String tableName = aliasToTable.get(targetTableAliasName);
            if (!StrUtil.equals(tableName, targetTableAliasName)) {
                isTargetAlias = true;
            }
            String on = aliasTooN.get(targetTableAliasName);
            String[] equals = on.split("=");
            SearchTableMainRelation searchTableMainRelation = new SearchTableMainRelation();
            for (String equal : equals) {
                String[] strings = equal.split("\\.");
                String aliasName = strings[0].trim();
                String aliasKey = strings[1].trim();
                if (StrUtil.equalsAny(aliasName, mainTable, aliasMainName)) {
                    searchTableMainRelation.setSourceTableName(mainTable);
                    if (isMainAlias) {
                        searchTableMainRelation.setSourceTableAlias(aliasMainName);
                    }
                    searchTableMainRelation.setSourceTableColumnName(aliasKey);
                } else {
                    if (aliasName.equals(targetTableAliasName)) {
                        searchTableMainRelation.setTargetTableName(tableName);
                        if (isTargetAlias) {
                            searchTableMainRelation.setTargetTableAlias(targetTableAliasName);
                        }
                        searchTableMainRelation.setTargetTableColumnName(aliasKey);
                        if (StrUtil.isNotBlank(tableExtendMap.get(aliasName))) {
                            searchTableMainRelation.setIsExtend(true);
                        }
                    } else {
                        String sourceTable = aliasToTable.get(aliasName);
                        searchTableMainRelation.setSourceTableName(sourceTable);
                        if (!sourceTable.equals(aliasName)) {
                            searchTableMainRelation.setSourceTableAlias(aliasName);
                        }
                        searchTableMainRelation.setSourceTableColumnName(aliasKey);
                    }
                }
            }
            searchTableMainRelation.setSearchTableMainId(searchTableMain.getId());
            SearchTableMainRelationFastDAO.create().dao().insert(searchTableMainRelation);
        }
        //更新上级依赖关系
        SearchTableMainRelationFastDAO targetAliasRelationFast = SearchTableMainRelationFastDAO.create();
        targetAliasRelationFast.searchTableMainId(searchTableMain.getId());
        targetAliasRelationFast.targetTableAlias().notNull();
        List<SearchTableMainRelation> targetAliasRelations = targetAliasRelationFast.dao().findAll();
        if (CollUtil.isNotEmpty(targetAliasRelations)) {
            for (SearchTableMainRelation relation : targetAliasRelations) {
                String targetTableAlias = relation.getTargetTableAlias();
                SearchTableMainRelationFastDAO relationFast = SearchTableMainRelationFastDAO.create();
                relationFast.searchTableMainId(searchTableMain.getId());
                relationFast.sourceTableAlias(targetTableAlias);
                SearchTableMainRelation r = relationFast.dao().findOne();
                if (r != null) {
                    SearchTableMainRelation s = new SearchTableMainRelation();
                    s.setParentRelationId(relation.getId());
                    SearchTableMainRelationFastDAO.create().id(r.getId()).dao().update(s);
                }
            }
        }
        SearchTableMainRelationFastDAO targetRelationFast = SearchTableMainRelationFastDAO.create();
        targetRelationFast.searchTableMainId(searchTableMain.getId());
        List<SearchTableMainRelation> targetRelations = targetRelationFast.dao().findAll();
        if (CollUtil.isNotEmpty(targetRelations)) {
            for (SearchTableMainRelation relation : targetRelations) {
                String targetTableName = relation.getTargetTableName();
                SearchTableMainRelationFastDAO relationFast = SearchTableMainRelationFastDAO.create();
                relationFast.searchTableMainId(searchTableMain.getId());
                relationFast.sourceTableName(targetTableName);
                int count = relationFast.dao().findCount();
                if (count == 1) {
                    SearchTableMainRelationFastDAO p = SearchTableMainRelationFastDAO.create();
                    p.searchTableMainId(searchTableMain.getId());
                    p.sourceTableName(targetTableName);
                    SearchTableMainRelation parentRelation = p.dao().findOne();
                    SearchTableMainRelation s = new SearchTableMainRelation();
                    s.setParentRelationId(relation.getId());
                    SearchTableMainRelationFastDAO.create().id(parentRelation.getId()).dao().update(s);
                }
            }
        }
        AliasDTO aliasDTO = new AliasDTO();
        aliasDTO.setTableMain(searchTableMain);
        aliasDTO.setAliasTooN(aliasTooN);
        aliasDTO.setTableExtendMap(tableExtendMap);
        aliasDTO.setAliasToTable(aliasToTable);
        aliasDTO.setMainAliasName(aliasMainName);
        aliasDTO.setMainTable(mainTable);
        aliasDTO.setIsAlias(isMainAlias);
        return aliasDTO;
    }

    @Override
    public List<UserPlanDTO> queryPlan(UserPlanDTO userPlanDTO) {
        String pageTag = userPlanDTO.getPageTag();
        String version = userPlanDTO.getVersion();
        String customMarker = userPlanDTO.getCustomMarker();
        SeSearchPlanNewFastDAO searchPlanNewFastDAO = SeSearchPlanNewFastDAO.create();
        searchPlanNewFastDAO.pageTag(pageTag);
        searchPlanNewFastDAO.version(version);
        searchPlanNewFastDAO.customMarker(customMarker);
        List<SeSearchPlanNew> seSearchPlanNews = searchPlanNewFastDAO.dao().findAll();
        if (CollUtil.isEmpty(seSearchPlanNews)) {
            return new ArrayList<>();
        }
        List<UserPlanDTO> userPlanDTOS = new ArrayList<>();
        String userName = WebUtil.getUser().getUser().getUserName();
        for (SeSearchPlanNew searchPlanNew : seSearchPlanNews) {
            String userAccount = searchPlanNew.getUserAccount();
            if (StrUtil.contains(userAccount, userName)) {
                UserPlanDTO user = new UserPlanDTO();
                user.setPlanName(searchPlanNew.getPlanName());
                user.setId(searchPlanNew.getId());
                user.setPageTag(pageTag);
                user.setCustomMarker(customMarker);
                user.setVersion(version);
                userPlanDTOS.add(user);
            }
        }
        return userPlanDTOS;
    }

    @Override
    public SearchPageConfigVO getUserPageSearchConfig(UserSearchPageConfigQueryDTO queryDTO) {
        SearchPageConfigVO vo = new SearchPageConfigVO();
        //获取页面配置
        SearchConfigInfoFastDAO configInfoFastDAO = SearchConfigInfoFastDAO.create();
        configInfoFastDAO.pageTag(queryDTO.getPageTag());
        if(StrUtil.isBlank(queryDTO.getVersion())){
            SeSearchConfigFastDAO configFastDAO = SeSearchConfigFastDAO.create();
            configFastDAO.pageTag(queryDTO.getPageTag());
            configFastDAO.version().isNull();
            SeSearchConfig searchConfig = configFastDAO.dao().findOne();
            configInfoFastDAO.searchConfigId(searchConfig.getId());
        }else {
            SeSearchConfigFastDAO configFastDAO = SeSearchConfigFastDAO.create();
            configFastDAO.pageTag(queryDTO.getPageTag());
            configFastDAO.version(queryDTO.getVersion());
            SeSearchConfig searchConfig = configFastDAO.dao().findOne();
            configInfoFastDAO.searchConfigId(searchConfig.getId());
        }
        List<SearchConfigInfo> configInfos = configInfoFastDAO.dao().findAll();
        vo.setConfigInfoList(BeanCopyUtil.copy(configInfos, SearchConfigInfoDTO.class));
        return vo;
    }


}
