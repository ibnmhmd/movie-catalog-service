package org.robust.catalogservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CatalogServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceDiscoveryApplication.class, args);
	}

}
