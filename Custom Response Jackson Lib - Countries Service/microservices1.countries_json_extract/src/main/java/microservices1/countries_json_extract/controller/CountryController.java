package microservices1.countries_json_extract.controller;

import lombok.RequiredArgsConstructor;
import microservices1.countries_json_extract.model.Country;
import microservices1.countries_json_extract.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getAll(){
        try {
            return ResponseEntity.ok(countryService.findAll());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/{name}")
    public ResponseEntity<Country> getByName(@PathVariable String name){
        try {
            return ResponseEntity.ok(countryService.findByName(name));
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
