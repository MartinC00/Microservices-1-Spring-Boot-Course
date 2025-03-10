package microservices1.flight_app.cloud_gateway_server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;


@Configuration
public class CorsFilterConfig {
	private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Content-Length, Authorization, credential, X-XSRF-TOKEN";
	  private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS, PATCH";
	  private static final String ALLOWED_ORIGIN = "*";

	  @Bean
	  public WebFilter corsFilter() {
	    return (ServerWebExchange ctx, WebFilterChain chain) -> {
	      ServerHttpRequest request = ctx.getRequest();
	      if (CorsUtils.isCorsRequest(request)) {
	        ServerHttpResponse response = ctx.getResponse();
	        HttpHeaders headers = response.getHeaders();
	        headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
	        headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
	        headers.add("Access-Control-Allow-Headers",ALLOWED_HEADERS);
	      }
	      return chain.filter(ctx);
	    };
	  }
}
