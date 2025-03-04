package microservices1.products.controller;

import lombok.extern.slf4j.Slf4j;
import microservices1.products.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${eureka.instance.instance-id}")
    private String currentInstance;

    @GetMapping("/{productName}")
    public Product getProduct(@PathVariable String productName){
        log.info("Current instance running: " + currentInstance);
        return new Product(productName, ((int)(Math.random()*5000+1)));
    }

}
