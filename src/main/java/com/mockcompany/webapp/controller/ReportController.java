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
    // Capturing important terms as mentioned in code/tests in an array
    private static final String[] importantTerms = new String[]{
            "Cool",
            "Amazing",
            "Perfect",
            "Kids"
    };
    private final EntityManager entityManager;
    // Declare SearchService as instance field of the below constructor same as EntityManager
    private final SearchService searchService;

    // Pass SearchService as the parameter of the constructor
    @Autowired
    public ReportController(EntityManager entityManager, SearchService searchService) {
        this.entityManager = entityManager;
        this.searchService = searchService;
    }


    @GetMapping("/api/products/report")
    public SearchReportResponse runReport() {

        // Query Creation for directly getting the count of items
        Number count = (Number) this.entityManager.createQuery("SELECT count(item) FROM ProductItem item").getSingleResult();

        // Executing query on each important terms and then add the size of results to the Map
        Map<String, Integer> hits = new HashMap<>();
        for(String term : importantTerms) {
            hits.put(term, searchService.search(term).size());
        }

        // Generating API response and returning it
        SearchReportResponse response = new SearchReportResponse();
        response.setProductCount(count.intValue());
        response.setSearchTermHits(hits);


        return response;
    }
}
