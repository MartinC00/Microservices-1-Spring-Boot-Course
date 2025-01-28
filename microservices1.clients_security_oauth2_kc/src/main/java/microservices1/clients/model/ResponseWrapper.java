package microservices1.clients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper <T>{
    private T data;
    private String error;

    public ResponseWrapper(T data){
        this.data = data;
        this.error = null;
    }

    public ResponseWrapper(String error){
        this.data = null;
        this.error = error;
    }
}
