package com.mockcompany.webapp.service;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SearchService {

    private final ProductItemRepository productItemRepository;

    @Autowired
    public SearchService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public Collection<ProductItem> search(String query) {

        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        boolean exactMatch = false;
        if(query.startsWith("\"") && query.endsWith("\"")) {
            exactMatch = true;
            // Extracting the quotes from the query
            query = query.substring(1, query.length() - 1);
        } else {
            // Converting to lowercase for handling case insensitivity
            query.toLowerCase();
        }

        // This is a loop that the code inside will execute on each of the items from the database.
        for (ProductItem item : allItems) {

            boolean nameMatches;
            boolean descMatches;

            if(exactMatch){
                // Check if name matches with the query
                nameMatches = query.equals(item.getName());
                // Check if description matches with the query
                descMatches = query.equals(item.getDescription());
            } else {
                // If neither of them matches
                // Ignore case check by normalizing everything to lowercase
                // Then check whether name contains query
                nameMatches = item.getName().toLowerCase().contains(query);
                // Check whether description contains query
                descMatches = item.getDescription().toLowerCase().contains(query);
            }
            // Add item to the list if either of them matches
            if(nameMatches || descMatches) {
                itemList.add(item);
            }
        }
        // Returning the list of items
        return itemList;
    }
}

