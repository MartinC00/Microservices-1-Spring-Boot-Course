package microservices1.countries_json_extract.service;

import microservices1.countries_json_extract.model.Country;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface CountryService {
    List<Country> findAll() throws IOException;
    Country findByName(String name) throws IOException;
}
