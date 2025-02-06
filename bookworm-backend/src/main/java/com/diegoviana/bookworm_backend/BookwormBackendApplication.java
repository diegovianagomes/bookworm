package com.diegoviana.bookworm_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BookwormBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookwormBackendApplication.class, args);
	}

}
