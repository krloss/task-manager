package org.test.taskmanager;

import java.text.DateFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApp {
	@Bean
	public DateFormat dateFormat() {
		return new AppDatePattern().getDateFormat();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
