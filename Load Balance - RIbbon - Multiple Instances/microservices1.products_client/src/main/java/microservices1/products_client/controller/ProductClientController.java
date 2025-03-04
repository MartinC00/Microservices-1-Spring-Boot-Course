package microservices1.products_client.controller;

import lombok.RequiredArgsConstructor;
import microservices1.products_client.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class ProductClientController {
    private String url = "http://product-service/";
    private final RestTemplate restTemplate;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return IntStream.rangeClosed(1, 5)
                .mapToObj(i -> restTemplate.getForObject(url + "product/product"+i, Product.class))
                .collect(Collectors.toList());
    }
}


