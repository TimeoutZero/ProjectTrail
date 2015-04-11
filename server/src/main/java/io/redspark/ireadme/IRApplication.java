package io.redspark.ireadme;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableJSONDoc
@SpringBootApplication
public class IRApplication  {
	
	public static void main(String[] args) {
		SpringApplication.run(IRApplication.class, args);
	}
}
