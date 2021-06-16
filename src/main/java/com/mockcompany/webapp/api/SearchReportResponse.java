package com.mockcompany.webapp.api;

import java.util.Map;

public class SearchReportResponse {

    private Integer productCount;
    private Map<String, Integer> searchTermHits;

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Map<String, Integer> getSearchTermHits() {
        return searchTermHits;
    }

    public void setSearchTermHits(Map<String, Integer> searchTermHits) {
        this.searchTermHits = searchTermHits;
    }
}
