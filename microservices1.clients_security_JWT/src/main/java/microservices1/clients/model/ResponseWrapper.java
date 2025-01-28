package microservices1.clients.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWrapper <T>{
    private T data;
    private String error;

    public ResponseWrapper(String error){
        this.data = null;
        this.error = error;
    }
    public ResponseWrapper(T data){
        this.data = data;
        this.error = null;
    }
}
