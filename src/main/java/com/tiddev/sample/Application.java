package com.tiddev.sample;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "contacts",
				description = "contacts details" ,
				version = "1.0.0",
				contact = @Contact(
						name = "mahan",
						email = "mahanfatehian@gmail.com",
						url = "https://otakusun.com/toji-fushiguro/"
				),
				license = @License(
						name = "MAPPA STUDIOS",
						url = "https://www.mappa.co.jp/en/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = " detail documentations ",
				url = "http://localhost:8080/index.html"
		)
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
