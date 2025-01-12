package com.mockcompany.webapp.controller

import com.mockcompany.webapp.data.ProductItemRepository
import com.mockcompany.webapp.model.ProductItem
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerSpec extends Specification {

    private static final Collection<ProductItem> mockProducts = Arrays.asList(
            new ProductItem(name: "Item 1", description: "Test Item 1", cost: 100, image: ""),
            new ProductItem(name: "Item 2", description: "Test Item 2", cost: 100, image: ""),
            new ProductItem(name: "Foo Bar", description: "The foo bar item", cost: 100, image: ""),
            new ProductItem(name: "Last", description: "Last item to test", cost: 100, image: "")
    )

    @Autowired
    private MockMvc mvc

    @Autowired
    ProductItemRepository productItemRepository

    def setup() {
        productItemRepository.deleteAll()
        productItemRepository.saveAll(mockProducts)
    }

    def "GET on /api/products/list will return all products"() {
        when:
        MvcResult result = mvc.perform(get("/api/products/list"))
            .andExpect(status().isOk())
            .andReturn()

        then: "json array is returned"
        Set<ProductItem> resultObjects = new JsonSlurper().parseText(result.response.contentAsString).collect { new ProductItem(it) } as Set
        resultObjects.size() == mockProducts.size()
        for (ProductItem item : mockProducts) {
            assert resultObjects.contains(item)
        }
    }
}
