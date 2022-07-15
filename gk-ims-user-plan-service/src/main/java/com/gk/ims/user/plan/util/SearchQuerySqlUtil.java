package com.gk.ims.user.plan.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.gk.ims.config.ResultException;
import com.gk.ims.config.login.WebUtil;
import com.gk.ims.user.plan.constant.SearchConditionsEnum;
import com.gk.ims.user.plan.api.dto.SearchQueryDTO;
import com.gk.ims.user.plan.pojo.SeSearchConfig;
import com.gk.ims.user.plan.pojo.SearchConfigInfo;
import com.gk.ims.user.plan.pojo.fast.SearchConfigInfoFastDAO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gk.ims.constants.FastDaoConstant.GROUP_ORG;

@Slf4j
public class SearchQuerySqlUtil {

    private static final String CRLF = System.lineSeparator();
    private static final String PARAM_PREFIX = "#{";
    private static final String PARAM_SUFFIX = "} ";
    private static final String COMMA = ", ";
    private static final String WHERE_PARAM_TYPE = "where_param_";
    private static final String LIMIT_PARAM_TYPE = "limit_param_";
    private static final String AND = " AND ";
    private static final String LIMIT = "limit ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String DESC = " DESC ";
    private static final String ASC = " ASC ";
    private static final String EQUAL = " = ";
    private static final String USER_ORGS_FIELD = "use_orgs";
    private static final String MATCH = " MATCH";
    private static final String WHERE = "WHERE";
    private static final String AGAINST = " AGAINST";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";


    /**
     * 当天
     */
    private static final String DAY = "#{day}";
    /**
     * 本周
     */
    private static final String WEEK = "#{week}";
    /**
     * 上周
     */
    private static final String LAST_WEEK = "#{last_week}";
    /**
     * 本月
     */
    private static final String MONTH = "#{month}";
    /**
     * 上月
     */
    private static final String LAST_MONTH = "#{last_month}";

    public static final String LEFT_BRACKETS = "(";
    public static final String RIGHT_BRACKETS = ")";
    /**
     * 查询SQL解析
     *
     * @param infoList 查询dto
     * @param sql      sql
     * @param paramMap 参数映射
     */
    public static void where(List<SearchQueryDTO.QueryInfo> infoList, StrBuilder sql, Map<String, Object> paramMap) {
        ParamIndex paramIndex = new ParamIndex();
        paramIndex.setParamType(WHERE_PARAM_TYPE);
        //解析条件参数
        if (CollUtil.isNotEmpty(infoList) && StrUtil.isNotBlank(infoList.get(0).getSearchKey()) && infoList.get(0).getSearchConfigInfoId() != null) {
            sql.append(AND);
            Map<String, List<SearchQueryDTO.QueryInfo>> childListMap = infoList.stream().collect(Collectors.groupingBy(t -> t.getSearchGroup()));
            int i=0;
            String preGroup="";
            for (String key : childListMap.keySet()) {
                List<SearchQueryDTO.QueryInfo> queryInfos = childListMap.get(key);
                String parentGroup = queryInfos.get(0).getParentGroup();
                String searchGroup = queryInfos.get(0).getSearchGroup();
                if(StrUtil.isBlank(parentGroup)){
                    i = packageGroupSql(sql, paramMap, paramIndex, i, queryInfos);
                }else {
                    for (String p : childListMap.keySet()) {
                        List<SearchQueryDTO.QueryInfo> ps = childListMap.get(p);
                        String parentGroups = ps.get(0).getParentGroup();
                        String parentGroupRelations = ps.get(0).getParentGroupRelation();
                        if(parentGroups.equals(preGroup)){
                            if(i!=0){
                                sql.append(" ");
                                sql.append(parentGroupRelations);
                                sql.append(" ");
                            }
                            i = packageGroupSql(sql, paramMap, paramIndex, i, queryInfos);
                        }
                    }
                }
                sql.append(" ");
                preGroup=searchGroup;
            }
        }
    }

    private static int packageGroupSql(StrBuilder sql, Map<String, Object> paramMap, ParamIndex paramIndex, int i, List<SearchQueryDTO.QueryInfo> queryInfos) {
        sql.append(LEFT_BRACKETS);
        int j=0;
        for (SearchQueryDTO.QueryInfo queryInfo : queryInfos) {
            SearchConfigInfo configInfo = SearchConfigInfoFastDAO.create().dao().findByPrimaryKey(queryInfo.getSearchConfigInfoId());
            String searchType = configInfo.getSearchType();
            if(j !=0){
                sql.append(" ");
                sql.append(queryInfo.getSearchGroupRelation());
                sql.append(" ");
            }
            condition(queryInfo, sql, paramMap, paramIndex,searchType);
            i++;
            j++;
        }
        sql.append(RIGHT_BRACKETS);
        return i;
    }

    /**
     * 条件转化SQL
     *
     * @param queryInfo  查询信息
     * @param sql        sql
     * @param paramMap   参数映射
     * @param paramIndex 参数坐标
     */
    private static void condition(SearchQueryDTO.QueryInfo queryInfo, StrBuilder sql, Map<String, Object> paramMap, ParamIndex paramIndex, String type) {
        SearchConditionsEnum conditionsEnum = SearchConditionsEnum.getSearchConditions(queryInfo.getSearchCondition());
        if (conditionsEnum == null) {
            return;
        }
        //条件类型判断
        switch (conditionsEnum) {
            case IS:
                if (!StrUtil.equalsAny(queryInfo.getSearchValue().toString(), "NULL", "NOT NULL")) {
                    throw new ResultException("非法查询");
                }
                sql.append(queryInfo.getSearchKey()).append(conditionsEnum.expression).append(queryInfo.getSearchValue() + " ");
                break;
            case In:
            case NotIn:
                String[] strings = StrUtil.splitToArray(queryInfo.getSearchValue().toString(), StrUtil.C_COMMA);
                if (ArrayUtil.isEmpty(strings)) {
                    return;
                }
                sql.append(queryInfo.getSearchKey()).append(conditionsEnum.expression).append(LEFT_BRACKETS);
                for (String s : strings) {
                    packParam(sql, paramMap, s, paramIndex).append(StrUtil.COMMA);
                }
                sql.del(sql.length() - 1, sql.length()).append(RIGHT_BRACKETS);
                sql.append(CRLF);
                break;
            case Like:
            case NotLike:
                sql.append(queryInfo.getSearchKey()).append(conditionsEnum.expression);
                packParam(sql, paramMap, StrBuilder.create("%", queryInfo.getSearchValue().toString(), "%").toString(), paramIndex);
                sql.append(CRLF);
                break;
            case Match:
            case NotMatch:
                sql.append(conditionsEnum.name).append(LEFT_BRACKETS).append(queryInfo.getSearchKey())
                        .append(RIGHT_BRACKETS).append(conditionsEnum.expression).append(LEFT_BRACKETS);
                packParam(sql, paramMap, queryInfo.getSearchValue(), paramIndex).append(RIGHT_BRACKETS);
                sql.append(CRLF);
                break;
            case NotEqual:
                if(type.equals("date")){
                    String dayStr = DateUtil.format(DateUtil.parse(queryInfo.getSearchValue().toString()), "yyyy-MM-dd");
                    String startDate = dayStr + " 00:00:00";
                    String endDate = dayStr + " 23:59:59";
                    sql.append(" (").append(queryInfo.getSearchKey()).append(" < '").append(startDate + "' ")
                            .append(" or ").append(queryInfo.getSearchKey()).append(" > '").append(endDate + "' ").append(") ");
                }else {
                    sql.append(queryInfo.getSearchKey()).append(conditionsEnum.expression);
                    packParam(sql, paramMap, queryInfo.getSearchValue(), paramIndex);
                }
                sql.append(CRLF);
                break;
            default:
                sql.append(queryInfo.getSearchKey()).append(conditionsEnum.expression);
                packParam(sql, paramMap, queryInfo.getSearchValue(), paramIndex);
                sql.append(CRLF);
        }
    }

    /**
     * 参数索引
     */
    private static class ParamIndex {
        private int index = 0;
        private String paramType;

        public int get() {
            return index;
        }

        public int add() {
            this.index++;
            return this.index;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }
    }

    /**
     * 对封装SQL拼接时的参数信息
     *
     * @param paramMap   参数
     * @param value      值
     * @param paramIndex 索引
     * @return 封装后的SQL
     */
    private static StrBuilder packParam(StrBuilder sqlBuilder, Map<String, Object> paramMap, Object value, ParamIndex paramIndex) {
        String paramKey = paramIndex.getParamType() + paramIndex.get();
        paramMap.put(paramKey, value);
        paramIndex.add();
        return packJdbcParam(sqlBuilder, paramKey);
    }

    /**
     * 包装jdbc参数
     *
     * @param sqlBuilder sql builder
     * @param paramKey   关键参数
     * @return {@link StrBuilder}
     */
    private static StrBuilder packJdbcParam(StrBuilder sqlBuilder, String paramKey) {
        sqlBuilder.append(PARAM_PREFIX).append(paramKey).append(PARAM_SUFFIX);
        return sqlBuilder;
    }

    /**
     * 拼接数据权限
     *
     * @param searchConfig 查询dto
     * @param sql          sql
     */
    public static void dataPermission(SeSearchConfig searchConfig, StrBuilder sql) {
        sql.append(AND);
        String useOrgLimitTableAlias = searchConfig.getUseOrgLimitTableAlias();
        String useOrgsLimitTableAlias = searchConfig.getUseOrgsLimitTableAlias();
        if(StrUtil.isNotBlank(useOrgLimitTableAlias)){
            useOrgDataLimit(useOrgLimitTableAlias, sql);
        }
        if(StrUtil.isNotBlank(useOrgsLimitTableAlias)){
            useOrgsDataLimit(useOrgsLimitTableAlias, sql);
        }
    }

    private static void useOrgDataLimit(String useOrgLimitTableAlias, StrBuilder sql) {
        String[] split = useOrgLimitTableAlias.split(",");
        for (String useOrg : split) {
            if (WebUtil.getUser().getOrganizationId().equals(GROUP_ORG)) {
                sql.append(LEFT_BRACKETS);
            }
            if (!WebUtil.getUser().getOrganizationId().equals(GROUP_ORG)) {
                sql.append(useOrg).append(EQUAL)
                        .append(WebUtil.getUser().getOrganizationId().toString());
            }else {
                Long[] orgIds = WebUtil.getUser().getUser().getOrgIds();
                int i= 0;
                for (Long orgId : orgIds) {
                    i++;
                    sql.append(useOrg).append(EQUAL)
                            .append(orgId);
                    if(i!=orgIds.length){
                        sql.append(" or ");
                    }
                }
            }
            if (WebUtil.getUser().getOrganizationId().equals(GROUP_ORG)) {
                sql.append(RIGHT_BRACKETS);
            }
        }
    }

    private static void useOrgsDataLimit(String useOrgsLimitTableAlias, StrBuilder sql) {
        String[] split = useOrgsLimitTableAlias.split(",");
        if(split.length>1){
            sql.append(LEFT_BRACKETS);
        }
        int i= 0;
        for (String useOrgs : split) {
            i++;
            if (WebUtil.getUser().getOrganizationId().equals(GROUP_ORG)) {
                sql.append(MATCH).append(LEFT_PARENTHESIS).append(useOrgs).append(RIGHT_PARENTHESIS)
                        .append(AGAINST).append(LEFT_PARENTHESIS)
                        .append(CharUtil.SINGLE_QUOTE + ArrayUtil.join(WebUtil.getUser().getUser().getOrgIds(), StrUtil.COMMA) + CharUtil.SINGLE_QUOTE)
                        .append(RIGHT_PARENTHESIS);
            } else {
                sql.append(MATCH).append(LEFT_PARENTHESIS).append(useOrgs).append(RIGHT_PARENTHESIS)
                        .append(AGAINST).append(LEFT_PARENTHESIS)
                        .append(WebUtil.getUser().getOrganizationId().toString())
                        .append(RIGHT_PARENTHESIS);
            }
            if(i!=split.length){
                sql.append(AND);
            }
        }
        if(split.length>1) {
            sql.append(RIGHT_BRACKETS);
        }
    }


}
