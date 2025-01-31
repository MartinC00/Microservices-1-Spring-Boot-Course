package microservices1.flight_app.reservations_service;


import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 By default, RestTemplate uses standard JDK HttpURLConnection HTTP client to make requests. This client does not support PATCH method.
	 Was necessary configure RestTemplate to use some other HTTP client via client factory, like HttpComponentsClientHttpRequestFactory (could be OkHttpClientHttpRequestFactory too).
	 Added httpclient dependency in pom.
	*/
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		HttpClient client = HttpClients.createDefault();
		RestTemplate template= new RestTemplate();
		template.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
		return template;
	}
}
