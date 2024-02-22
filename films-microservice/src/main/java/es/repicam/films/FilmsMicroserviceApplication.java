package es.repicam.films;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FilmsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmsMicroserviceApplication.class, args);
	}

}
