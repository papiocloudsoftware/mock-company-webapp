/*
 * Java classes are grouped in "packages". This allows them to be referenced and used in other
 * classes using import statements.  Any class in this project is prefixed in the com.mockcompany.webapp
 * package.
 *
 *   https://www.w3schools.com/java/java_packages.asp
 *
 * For general help with Java, see the tutorialspoint tutorial:
 *
 *   https://www.tutorialspoint.com/java/index.htm
 */
package com.mockcompany.webapp.controller;

/*
 * An import statement allows the current class to use the class being imported
 */
import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
/* The springframework package allows us to take advantage of the spring capabilities */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/* java.util package provides useful utilities */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is the entrypoint for the /api/products/search API.  It is "annotated" with
 * the "RestController" annotation which tells the spring framework that it will be providing
 * API implementations.
 *
 *   https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.developing-web-applications
 *
 * An annotation is metadata that provides data about the program.  Annotations can be checked for on a class by other
 * classes.  In this case, we're telling the spring framework that the SearchController provides API capabilities.
 *
 *   https://docs.oracle.com/javase/tutorial/java/annotations/
 */
@RestController
public class SearchController {

    /**
     * This is a instance field.  It is provided by the spring framework through the constructor because of the
     * @Autowired annotation.  Autowire tells the spring framework to automatically find and use an instance of
     * the declared class when creating this class.
     */
    private final ProductItemRepository productItemRepository;

    @Autowired
    public SearchController(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    /**
     * The search method, annotated with @GetMapping telling spring this method should be called
     * when an HTTP GET on the path /api/products/search is made.  A single query parameter is declared
     * using the @RequestParam annotation.  The value that is passed when performing a query will be
     * in the query parameter.
     * @param query
     * @return The filtered products
     */
    @GetMapping("/api/products/search")
    public Collection<ProductItem> search(@RequestParam("query") String query) {
        /*
         * TODO: !!!! Implement this method !!!!
         *  The easiest implementation will be to use the findAll as we are below. Then filter using Java
         *  string methods such as contains(...), toLowerCase(...), equals(...), etc.
         *
         *  The requirements are defined in src/test/groovy/com/mockcompany/webapp/controller/SearchControllerSpec.groovy
         *
         *  Read through the tests to get an idea of how search should work.  When the tests are written before the code,
         *  it is known as Test Driven Development (TDD) and is a common best practice. The Spock framework is a great
         *  framework for TDD because the tests are written very descriptively using sentences.
         *
         *    https://spockframework.org/spock/docs/2.0/spock_primer.html
         *
         *  For an added challenge, update the ProductItemRepository to do the filtering at the database layer :)
         */

        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        // This is a loop that the code inside will execute on each of the items from the database.
        for (ProductItem item : allItems) {
            // TODO: Figure out if the item should be returned based on the query parameter!
            boolean matchesSearch = true;
            itemList.add(item);
        }
        return itemList;
    }
}
