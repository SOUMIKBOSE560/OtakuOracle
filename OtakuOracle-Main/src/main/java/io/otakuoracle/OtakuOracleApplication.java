package io.otakuoracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class OtakuOracleApplication {

    //REST TEMPLATE BEAN SHOULD BE IN MAIN CLASS OTHERWISE I GET 500 INTERNAL SERVER ERROR
    //AND UNKNOWN HOST EXCEPTION Ota...Sera... NOT FOUND

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OtakuOracleApplication.class, args);
    }

}
