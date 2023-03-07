package com.example.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class DemoSecurityApplication implements CommandLineRunner {

	@Autowired
	MyUserRepository myUserRepository;

	@Autowired
	PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(DemoSecurityApplication.class, args);

		// .....
	}

	@Override
	public void run(String... args) throws Exception {

		MyUser user1 = MyUser.builder()
				.name("Sai")
				.email("sai@gmail.com")
				.password(passwordEncoder.encode("sai123"))
				.authority("dev")
				.build();


		MyUser user2 = MyUser.builder()
				.name("Rahul")
				.email("rahul@gmail.com")
				.password(passwordEncoder.encode("rahul123"))
				.authority("qa")
				.build();

		MyUser user3 = MyUser.builder()
				.name("Rahul")
				.email("rahul@yahoo.co.in")
				.password(passwordEncoder.encode("rahul123"))
				.authority("dev")
				.build();

		MyUser user4 = MyUser.builder()
				.name("Kanika")
				.email("kanika@gmail.com")
				.password(passwordEncoder.encode("kanika123"))
				.authority("dev")
				.build();

		MyUser user5 = MyUser.builder()
				.name("Pinak")
				.email("pinak@gmail.com")
				.password(passwordEncoder.encode("pinak123"))
				.authority("dev")
				.build();

		MyUser user6 = MyUser.builder()
				.name("Carolina")
				.email("carolina@gmail.com")
				.password(passwordEncoder.encode("carolina123"))
				.authority("dev")
				.build();


		try {
			myUserRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6));

		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
