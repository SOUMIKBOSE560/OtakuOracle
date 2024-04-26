package io.otakuoracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OtakuOracleSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtakuOracleSearchServiceApplication.class, args);
	}

}
