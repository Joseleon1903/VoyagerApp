package com.discovery.voyager.aplication;

import com.discovery.voyager.aplication.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class VoyagerAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(VoyagerAppApplication.class, args);
	}  

}
