package com.mockcompany.webapp.service;

import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * This class will create random products from a list of images
 */
@Service
public class ProductGenerator {

    private static final String[] RANDOM_ADJECTIVES = new String[] {
            "Beautiful",
            "Amazing",
            "Fantastic",
            "Delicious",
            "Perfect",
            "Famous",
            "Fabulous",
            "Elegant",
            "Daft"
    };

    private static final String[] RANDOM_NOUNS = new String[] {
            "Thing",
            "Stuff",
            "Object",
            "Item",
            "Goods",
            "Gadget",
            "Commodity",
            "Bit",
            "Component",
            "Piece",
            "Unit"
    };

    private static final String[] RANDOM_DESC = new String[] {
            "This is the best product we sell!",
            "One of a kind item here, get it while it lasts!",
            "We have all the best of these that anyone would ever want!",
            "Priced to sell, you can't find it cheaper anywhere else!",
            "You've been asking and we have delivered. This is the one everyone has wanted!",
            "The perfect gift for your friend, family, or significant other!",
            "This is not a trick, we are in fact selling it at this amazing price!",
            "No one else is carrying these items, we have it ALL!",
            "Grandparents go crazy for it, the perfect gift!",
            "It's what all kids are talking about these days! A great gift for an upcoming birthday"
    };

    private final Random random = new Random();

    private final Resource[] productImages;

    public ProductGenerator(@Value("classpath:/images/*.png") Resource[] productImages) {
        this.productImages = productImages;
    }

    public Collection<ProductItem> generateProducts() throws IOException {
        // Shuffle the array so the images are not always in the same order
        for (int i = 0; i < 100; i++) {
            int idx1 = this.random.nextInt(this.productImages.length);
            int idx2 = this.random.nextInt(this.productImages.length);
            Resource tmp = this.productImages[idx1];
            this.productImages[idx1] = this.productImages[idx2];
            this.productImages[idx2] = tmp;
        }

        List<ProductItem> products = new ArrayList<>();
        for (Resource image : this.productImages) {
            ProductItem product = new ProductItem();
            product.setName(generateName());
            product.setDescription(generateDescription());
            product.setCost(generateCost());
            product.setImage(String.format("data:image/png;base64,%s", encodeImage(image)));
            products.add(product);
        }
        return products;
    }

    private String generateName() {
        return String.format(
                "%s %s",
                RANDOM_ADJECTIVES[this.random.nextInt(RANDOM_ADJECTIVES.length)],
                RANDOM_NOUNS[this.random.nextInt(RANDOM_NOUNS.length)]
        );
    }

    private String generateDescription() {
        return RANDOM_DESC[this.random.nextInt(RANDOM_DESC.length)];
    }

    /**
     * @return a value from 100 -> 100000 which would be $1.00 to $1000.00
     */
    private Integer generateCost() {
        return (100 * this.random.nextInt(1000)) + 99;
    }

    private String encodeImage(Resource image) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InputStream in = image.getInputStream()) {
            byte[] buffer = new byte[8192];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }
}
