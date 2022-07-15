package com.gk.ims.user.plan.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.gk.ims.user.plan.dto.LeftJoinDeep;
import com.gk.ims.user.plan.pojo.*;
import com.gk.ims.user.plan.pojo.fast.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ckn
 * @date 2022/7/5
 */
public class SQLUtils {
    static String SELECT = "SELECT";
    static String BLANK = " ";
    static String TURN = System.lineSeparator();
    static String AS = "as";
    static String FROM = "from";
    static String LEFT_BRACKET = "(";
    static String RIGHT_BRACKET = ")";
    static String LEFT_JOIN = "LEFT JOIN";
    static String ON = "on";
    static String POINT = ".";
    static String EQUAL = "=";
    static String COMMA = ",";
    static String AND="AND";
    static String WHERE="where";

   public static String packageSql(String pagTage, String version, List<String> columnNames) {
        SearchTableMainFastDAO tableMainFastDAO = SearchTableMainFastDAO.create();
        tableMainFastDAO.pageTag(pagTage);
        tableMainFastDAO.version(version);
        SearchTableMain searchTableMain = tableMainFastDAO.dao().findOne();
        Boolean isExtend = searchTableMain.getIsExtend();
        Long tableMainId = searchTableMain.getId();
        Map<String,String> tableAliasMap=new HashMap<>();
        SearchTableMainRelationFastDAO mainRelationFastDAO = SearchTableMainRelationFastDAO.create();
        mainRelationFastDAO.searchTableMainId(tableMainId);
        List<SearchTableMainRelation> searchTableMainRelations = mainRelationFastDAO.dao().findAll();
        if(CollUtil.isNotEmpty(searchTableMainRelations)){
            for (SearchTableMainRelation tableMainRelation : searchTableMainRelations) {
                tableAliasMap.put(tableMainRelation.getTargetTableName(),tableMainRelation.getTargetTableAlias());
            }
        }
        String mainTableAlias = searchTableMain.getAlias();
        tableAliasMap.put(searchTableMain.getSearchTableName(),mainTableAlias);
        StringBuilder sqlBuilder = new StringBuilder(SELECT);
        sqlBuilder.append(TURN);
        SearchTableColumnFastDAO searchTableColumnFastDAO = SearchTableColumnFastDAO.create();
        searchTableColumnFastDAO.pageTag(pagTage);
        searchTableColumnFastDAO.version(version);
        List<SearchTableColumn> searchTableColumns = searchTableColumnFastDAO.dao().findAll();
        if (CollUtil.isEmpty(searchTableColumns)) {
            return "";
        }
        Map<String, String> columnToInfos = new HashMap<>();
        Set<Long> relationIds=new HashSet<>();
        for (SearchTableColumn searchTableColumn : searchTableColumns) {
            String searchTableColumnInfo = searchTableColumn.getSearchTableColumnInfo();
            columnToInfos.put(searchTableColumn.getSearchTableColumn(),
                    searchTableColumnInfo);
        }
        String[] columnAll = ArrayUtil.toArray(columnNames, String.class);

        for (int i = 0; i < columnAll.length; i++) {
            sqlBuilder.append(columnToInfos.get(columnAll[i]));
            sqlBuilder.append(BLANK);
            sqlBuilder.append(AS);
            sqlBuilder.append(BLANK);
            sqlBuilder.append(columnAll[i]);
            if (i != columnAll.length - 1) {
                sqlBuilder.append(COMMA);
            }
            sqlBuilder.append(TURN);
            SearchTableColumnFastDAO columnFastDAO = SearchTableColumnFastDAO.create();
            columnFastDAO.searchTableColumn(columnAll[i]);
            columnFastDAO.pageTag(pagTage);
            columnFastDAO.version(version);
            SearchTableColumn tableColumn = columnFastDAO.dao().findOne();
            Long searchTableColumnId = tableColumn.getId();
            List<SearchTableColumnTarget> tableColumnTargets = SearchTableColumnTargetFastDAO.create().searchTableColumnId(searchTableColumnId).dao().findAll();
            if(CollUtil.isNotEmpty(tableColumnTargets)){
                for (SearchTableColumnTarget columnTarget : tableColumnTargets) {
                    relationIds.add(columnTarget.getSearchTableMainRelationId());
                }
            }
        }
        String searchTableName = searchTableMain.getSearchTableName();
        addOperation(sqlBuilder, searchTableName, isExtend, FROM, searchTableName, BLANK,tableAliasMap.get(searchTableMain.getSearchTableName()),tableMainId);
        if (relationIds.size() > 0) {
            Set<Long> relations = new HashSet<>();
            for (Long relationId : relationIds) {
                SearchTableMainRelation tableMainRelation = SearchTableMainRelationFastDAO.create().id(relationId).dao().findOne();
                if (tableMainRelation != null) {
                    Long parentRelationId = tableMainRelation.getParentRelationId();
                    relations.add(relationId);
                    while (parentRelationId != null) {
                        SearchTableMainRelation mainRelation = SearchTableMainRelationFastDAO.create().id(parentRelationId).dao().findOne();
                        relations.add(mainRelation.getId());
                        parentRelationId = mainRelation.getParentRelationId();
                    }
                }
            }
            int j=0;
            Set<String> stringSet=new HashSet<>();
            //对left join 进行排序
            List<LeftJoinDeep> leftJoinDeeps=new ArrayList<>();
            for (Long id : relations) {
                LeftJoinDeep deep=new LeftJoinDeep();
                deep.setId(id);
                int dep=0;
                SearchTableMainRelation tableMainRelation = SearchTableMainRelationFastDAO.create().id(id).dao().findOne();
                if(tableMainRelation.getParentRelationId()!=null){
                    Long parentRelationId=tableMainRelation.getParentRelationId();
                    while (parentRelationId!=null){
                        SearchTableMainRelation tb = SearchTableMainRelationFastDAO.create().id(parentRelationId).dao().findOne();
                        dep++;
                        parentRelationId = tb.getParentRelationId();
                    }
                }
                deep.setDeep(dep);
                leftJoinDeeps.add(deep);
            }
            List<LeftJoinDeep> joinDeeps = leftJoinDeeps.stream().sorted(Comparator.comparing(LeftJoinDeep::getDeep)).collect(Collectors.toList());
            for (LeftJoinDeep d : joinDeeps) {
                Long id = d.getId();
                j++;
                SearchTableMainRelation tableMainRelation = SearchTableMainRelationFastDAO.create().id(id).dao().findOne();
                Boolean is = tableMainRelation.getIsExtend();
                String targetTableAlias = tableMainRelation.getTargetTableAlias();
                if(StrUtil.isNotBlank(targetTableAlias) && stringSet.contains(targetTableAlias)){
                    continue;
                }
                if(StrUtil.isBlank(targetTableAlias) && stringSet.contains(tableMainRelation.getTargetTableName())){
                    continue;
                }
                if(j==1){
                    sqlBuilder.append(TURN);
                }
                sqlBuilder.append(LEFT_JOIN);
                sqlBuilder.append(BLANK);
                if (is) {
                    SearchExtendTableFastDAO searchExtendTableFastDAO = SearchExtendTableFastDAO.create();
                    searchExtendTableFastDAO.extendTableName(tableMainRelation.getTargetTableName());
                    searchExtendTableFastDAO.searchTableMainId(tableMainId);
                    SearchExtendTable extendTable =searchExtendTableFastDAO.dao().findOne();
                    String extendSql = extendTable.getExtendSql();
                    sqlBuilder.append(LEFT_BRACKET);
                    sqlBuilder.append(extendSql);
                    sqlBuilder.append(RIGHT_BRACKET);
                }
                sqlBuilder.append(BLANK);
                sqlBuilder.append(tableMainRelation.getTargetTableName());
                if(StrUtil.isNotBlank(targetTableAlias)){
                    sqlBuilder.append(BLANK);
                    sqlBuilder.append(targetTableAlias);
                    stringSet.add(targetTableAlias);
                }else {
                    stringSet.add(tableMainRelation.getTargetTableName());
                }
                sqlBuilder.append(BLANK);
                sqlBuilder.append(ON);
                sqlBuilder.append(BLANK);
                if(StrUtil.isBlank(tableAliasMap.get(tableMainRelation.getSourceTableName()))){
                    sqlBuilder.append(tableMainRelation.getSourceTableName());
                }else {
                    sqlBuilder.append(tableAliasMap.get(tableMainRelation.getSourceTableName()));
                }
                sqlBuilder.append(POINT);
                sqlBuilder.append(tableMainRelation.getSourceTableColumnName());
                sqlBuilder.append(EQUAL);
                if(StrUtil.isNotBlank(targetTableAlias)){
                    sqlBuilder.append(targetTableAlias);
                }else {
                    sqlBuilder.append(tableMainRelation.getTargetTableName());
                }
                sqlBuilder.append(POINT);
                sqlBuilder.append(tableMainRelation.getTargetTableColumnName());
                if(j !=relations.size()){
                    sqlBuilder.append(TURN);
                }
            }
        }
        /**
         * 筛选合适条件
         */
        SearchTableWhereFastDAO searchTableWhereFastDAO = SearchTableWhereFastDAO.create();
        searchTableWhereFastDAO.pageTag(pagTage);
        List<SearchTableWhere> tableWheres = searchTableWhereFastDAO.dao().findAll();
        if(CollUtil.isNotEmpty(tableWheres)){
            Set<Long> checkWhereId=new HashSet<>();
            for (SearchTableWhere where : tableWheres) {
                Long whereId = where.getId();
                List<SearchTableWhereRelation> tableWhereRelations = SearchTableWhereRelationFastDAO.create().searchTableWhereId(whereId).dao().findAll();
                if(CollUtil.isEmpty(tableWhereRelations)){
                    checkWhereId.add(whereId);
                }else {
                    Set<Long> relations=new HashSet<>();
                    for (SearchTableWhereRelation whereRelation : tableWhereRelations) {
                        relations.add(whereRelation.getSearchTableMainRelationId());
                        SearchTableMainRelation tableMainRelation = SearchTableMainRelationFastDAO.create().id(whereRelation.getSearchTableMainRelationId()).dao().findOne();
                        Long parentRelationId = tableMainRelation.getParentRelationId();
                        while (parentRelationId != null) {
                            SearchTableMainRelation mainRelation = SearchTableMainRelationFastDAO.create().id(parentRelationId).dao().findOne();
                            relations.add(mainRelation.getId());
                            parentRelationId = mainRelation.getParentRelationId();
                        }
                    }
                    boolean containsAll = CollUtil.containsAll(relationIds, relations);
                    if(containsAll){
                        checkWhereId.add(whereId);
                    }
                }
            }
            sqlBuilder.append(TURN);
            sqlBuilder.append(WHERE);
            sqlBuilder.append(BLANK);
            int i=0;
            for (SearchTableWhere where : tableWheres) {
                if(checkWhereId.contains(where.getId())){
                    if(i !=0){
                        sqlBuilder.append(TURN);
                        sqlBuilder.append(where.getRelation());
                    }
                    i++;
                    sqlBuilder.append(BLANK);
                    sqlBuilder.append(where.getTableWhere());
                }
            }
        }
        return sqlBuilder.toString();
    }

    private static void addOperation(StringBuilder sqlBuilder, String searchTableName, Boolean isExtend, String operatorHead, String on, String operatorTail, String alias, Long mainId) {
        sqlBuilder.append(operatorHead);
        if (isExtend) {
            SearchExtendTableFastDAO searchExtendTableFastDAO = SearchExtendTableFastDAO.create();
            searchExtendTableFastDAO.extendTableName(searchTableName);
            searchExtendTableFastDAO.searchTableMainId(mainId);
            SearchExtendTable extendTable =searchExtendTableFastDAO.dao().findOne();
            String extendSql = extendTable.getExtendSql();
            sqlBuilder.append(LEFT_BRACKET);
            sqlBuilder.append(extendSql);
            sqlBuilder.append(RIGHT_BRACKET);
        }
        sqlBuilder.append(BLANK);
        sqlBuilder.append(on);
        if(StrUtil.isNotBlank(alias) && (! searchTableName.equals(alias))){
            sqlBuilder.append(BLANK);
            sqlBuilder.append(alias);
        }
        sqlBuilder.append(operatorTail);
    }
}
