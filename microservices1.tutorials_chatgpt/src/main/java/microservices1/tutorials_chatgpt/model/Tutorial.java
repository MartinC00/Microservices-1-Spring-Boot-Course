package microservices1.tutorials_chatgpt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    private String url;
    private String description;
    private int rating;
}
