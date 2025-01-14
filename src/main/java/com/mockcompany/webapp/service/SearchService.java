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
        /*
         *  For an added challenge, update the ProductItemRepository to do the filtering at the database layer :)
         */

        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        // This is a loop that the code inside will execute on each of the items from the database.
        for (ProductItem item : allItems) {
            if (query.charAt(0) == '"' && query.charAt(query.length() - 1) == '"') {
                // exact match
                String queryWithoutQuotes = query.substring(1, query.length() - 1);
                if (!(item.getName().equalsIgnoreCase(queryWithoutQuotes)
                        || item.getDescription().equalsIgnoreCase(queryWithoutQuotes)))
                    continue;
            } else {
                // partial match
                if (!(item.getName().toLowerCase().contains(query.toLowerCase())
                        || item.getDescription().toLowerCase().contains(query.toLowerCase())))
                    continue;
            }

            itemList.add(item);
        }
        return itemList;

        // filter from database layer
        //return productItemRepository.findByNameOrDescriptionEqualsOrContainsIgnoreCase(query);
    }
}
