package com.management.system;

import com.management.system.DAO.*;
import com.management.system.Entity.*;
import com.management.system.Service.CityService;
import com.management.system.Service.EmailService;
import com.management.system.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
public class SystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository){

		return args -> {

			//System.out.println(userRepository.getUserByUsername("security102"));

			//User user = new User("bogdan","bogdan", "securityEmail@gmail.com","security104",new BCryptPasswordEncoder().encode("securityPassword"),"user", 20, false);
			//userRepository.save(user);

		};
	}






}
