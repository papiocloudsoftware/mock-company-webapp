package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;


/**
 * Management decided it is super important that we have lots of products that match the following terms.
 * So much so, that they would like a daily report of the number of products for each term along with the total
 * product count.
 */
@RestController
public class ReportController {

    private final EntityManager entityManager;
    private final SearchService searchService;

    // Add the SearchService to the constructor
    @Autowired
    public ReportController(EntityManager entityManager, SearchService searchService) {
        this.entityManager = entityManager;
        this.searchService = searchService;
    }

    @GetMapping("/api/products/report")
    public SearchReportResponse runReport() {
        SearchReportResponse response = new SearchReportResponse();
        Map<String, Integer> hits = new HashMap<>();
        response.setSearchTermHits(hits);

        int count = entityManager.createQuery("SELECT item FROM ProductItem item").getResultList().size();
        response.setProductCount(count);

        // search "Cool"
        response.getSearchTermHits().put("Cool", searchService.search("Cool").size());

        // search "Kids"
        response.getSearchTermHits().put("Kids", searchService.search("Kids").size());

        // search "Perfect"
        response.getSearchTermHits().put("Perfect", searchService.search("Perfect").size());

        // search "Amazing"
        response.getSearchTermHits().put("Amazing", searchService.search("Amazing").size());

        return response;
    }
}
