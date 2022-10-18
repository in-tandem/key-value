package com.org.somak.store.keyvalue;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude={MongoAutoConfiguration.class})
@OpenAPIDefinition(info = @Info(
		title = "Key Value Store",
		version = "1.0",
		description = "Project can be used to add/get/delete key value objects of type Item",
		contact = @Contact(email = "dutta.somak@outlook.com")))
public class KeyvalueApplication {

//	private static final String SECRET_KEY = "b2fc6f85ae1e4a6fbd779ffe67d186fc";
//	private static final String JASYPT_ENCRIPTOR = "jasypt.encryptor.password";
//
//	static {
//		System.setProperty(JASYPT_ENCRIPTOR, SECRET_KEY);
//	}

	public static void main(String[] args) {
		SpringApplication.run(KeyvalueApplication.class, args);
	}

}
