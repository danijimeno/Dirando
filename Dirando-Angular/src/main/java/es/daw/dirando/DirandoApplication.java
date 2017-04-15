package es.daw.dirando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class DirandoApplication extends WebMvcConfigurerAdapter  {

	public static void main(String[] args) {
		SpringApplication.run(DirandoApplication.class, args);
	}

}
