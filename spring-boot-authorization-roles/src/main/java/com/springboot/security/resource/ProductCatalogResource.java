package com.springboot.security.resource;

import java.util.List;

import com.springboot.security.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RestController
public class ProductCatalogResource {
    @Value("${message.environment.specific}")
    private String propertyMessage;

    @Autowired
    Environment environment;

    @Autowired
    private MongoTemplate mongoTemplate;

    Logger logger = LoggerFactory.getLogger("ProductCatalogService");

    @Value("${server.port}")
    String serverPort;

    @PostMapping("/product")
    @RolesAllowed("ADMIN")
    public Product addProduct(@RequestBody Product product) {
        logger.info("Add product api invoked ************");
        return mongoTemplate.insert(product);
    }

    @PutMapping("/product")
    @RolesAllowed("ADMIN")
    public Product updateProduct(@RequestBody Product product) {
        return mongoTemplate.save(product);
    }

    @GetMapping("/product/{id}")
    @RolesAllowed({"GENERAL", "ADMIN"})
    public Product getProductDetails(@PathVariable String id) {
        logger.info("get product details - process started");
        Product product = mongoTemplate.findById(id, Product.class);
        if (product != null)
            logger.info("get product details - product found");
        else
            logger.info("get product details - product not found");

        return product;
    }

    @DeleteMapping("/product/{id}")
    @RolesAllowed("ADMIN")
    public String deleteProduct(@PathVariable String id) {
        Product toDeleteProduct = new Product();
        toDeleteProduct.setId(id);

        mongoTemplate.remove(toDeleteProduct);
        return "Product Deleted-" + id;
    }

    @GetMapping("/product")
    public List<Product> getProductList() {
        return mongoTemplate.findAll(Product.class);
    }

    @GetMapping("/message")
    @PermitAll
    public String getMessage(){
        return propertyMessage;
    }

    @GetMapping("/environment")
    @PermitAll
    public String getEnvironment(){
        return environment.toString();
    }

}