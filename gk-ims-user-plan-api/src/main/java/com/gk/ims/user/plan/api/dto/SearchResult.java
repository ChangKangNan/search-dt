package com.gk.ims.user.plan.api.dto;

import com.fast.utils.page.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class SearchResult implements Serializable {
    private static final long serialVersionUID = -1165408436552898803L;
    private int code = 0;
    private String msg;
    private PageInfo<Map> data;
    private SearchQueryDTO query;
    private List<SearchQueryDTO.QueryInfo> conditions;
    private List<SearchQueryDTO.SearchColumn> columns;

    public SearchResult(PageInfo<Map> data, SearchQueryDTO query) {
        this.data = data;
        this.query = query;
    }
}
