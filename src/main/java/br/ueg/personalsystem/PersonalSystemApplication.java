package br.ueg.personalsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.ueg.genericarchitecture", "br.ueg.personalsystem"})
public class PersonalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalSystemApplication.class, args);
	}

}
