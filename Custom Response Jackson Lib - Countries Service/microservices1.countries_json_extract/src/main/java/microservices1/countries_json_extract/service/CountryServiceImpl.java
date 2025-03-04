package microservices1.countries_json_extract.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices1.countries_json_extract.model.Country;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService{
    private final String getAllCountryListURL = "https://restcountries.com/v3.1/subregion/South America";
    private final RestTemplate restTemplate;

    @Override
    public List<Country> findAll() throws IOException {
        List<Country> countryList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        String countryListJSON = restTemplate.getForObject(getAllCountryListURL, String.class);
        ArrayNode countriesArray = (ArrayNode) mapper.readTree(countryListJSON);

        for(JsonNode country : countriesArray){
            countryList.add(new Country(country.path("name").path("common").asText(),
                                        country.path("capital").get(0).asText(),
                                        country.path("population").asInt(),
                                        country.path("flag").asText()));
        }
        return countryList;
    }

    @Override
    public Country findByName(String name) throws IOException {
        return findAll()
                .stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
