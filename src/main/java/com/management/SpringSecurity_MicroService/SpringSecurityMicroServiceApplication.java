package com.management.SpringSecurity_MicroService;

import com.management.SpringSecurity_MicroService.entities.Role;
import com.management.SpringSecurity_MicroService.entities.User;
import com.management.SpringSecurity_MicroService.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityMicroServiceApplication {

	@Autowired
	UserService userService;
	public static void main(String[] args) {

		SpringApplication.run(SpringSecurityMicroServiceApplication.class, args);
	}
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	//@PostConstruct // Lancement de methode et execution ( the after @PreDestroy )
	void initial_users(){

		//Creation de role
		userService.addRole(new Role(null,"ADMIN"));
		userService.addRole(new Role(null,"CASHIER"));
		userService.addRole(new Role(null,"USER"));
		userService.addRole(new Role(null,"CREATE"));

		//Creation users
		userService.saveUser(new User(null,"admin","123",true,null));
		userService.saveUser(new User(null,"cashier","123",true,null));
		userService.saveUser(new User(null,"accountant","123",true,null));

		//liaisons role to user
		userService.addRoleToUser("admin","ADMIN");
		userService.addRoleToUser("admin","CREATE");
		userService.addRoleToUser("cashier","CASHIER");
		userService.addRoleToUser("accountant","USER");


	}

}
