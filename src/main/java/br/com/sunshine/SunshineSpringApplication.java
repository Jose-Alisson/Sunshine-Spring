package br.com.sunshine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "br.com.sunshine")
@SpringBootApplication
public class SunshineSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunshineSpringApplication.class, args);
	}

}
