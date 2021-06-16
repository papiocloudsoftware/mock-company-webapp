package com.mockcompany.webapp.config;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import com.mockcompany.webapp.service.ProductGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Collection;

/**
 * This class will use the generator to generate items then save them to the repository
 */
@Configuration
public class DataPopulator {

    private final ProductGenerator productGenerator;
    private final ProductItemRepository productItemRepository;

    @Autowired
    public DataPopulator(ProductGenerator productGenerator, ProductItemRepository productItemRepository) {
        this.productGenerator = productGenerator;
        this.productItemRepository = productItemRepository;
    }

    @Bean
    public Collection<ProductItem> populatedItems() throws IOException {
        Collection<ProductItem> items = this.productGenerator.generateProducts();
        this.productItemRepository.saveAll(items);
        return items;
    }
}
