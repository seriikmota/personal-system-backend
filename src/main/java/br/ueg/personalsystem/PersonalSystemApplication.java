package br.ueg.personalsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@EnableFeignClients
public class PersonalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalSystemApplication.class, args);
	}

}
