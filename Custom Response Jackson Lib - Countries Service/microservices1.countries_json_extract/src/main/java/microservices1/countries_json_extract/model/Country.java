package microservices1.countries_json_extract.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Country {
    private String name;
    private String capital;
    private int population;
    private String flag;
}
