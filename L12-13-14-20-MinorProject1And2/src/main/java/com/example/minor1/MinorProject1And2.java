package com.example.minor1;

import com.example.minor1.model.Admin;
import com.example.minor1.model.Author;
import com.example.minor1.repository.AuthorRepository;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.service.Impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MinorProject1And2 implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(MinorProject1And2.class);

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AdminServiceImpl adminService;

	public static void main(String[] args) {

		SpringApplication.run(MinorProject1And2.class, args);
		logger.info("Minor1Application has started");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("In run function of main class");
		List<Author> authorList = authorRepository.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(
				30,"India","S");
//		authorList.stream()
//				.map(author -> author.getBookList())
//				.forEach(books -> logger.info(String.valueOf(books.size())));
		authorList.forEach(System.out::println);

		List<Admin> adminList = adminService.getAllAdmin();
		if(adminList.isEmpty()){
			StudentCreateRequest studentCreateRequest = StudentCreateRequest.builder()
					.name("Sudhir Daga")
					.email("sudhirdaga1998@gmail.com")
					.age(24)
					.address("179, Bangur Park, Rishra, Hooghly")
					.contact("+91-8335940590")
					.username("sudhir18")
					.password("Sudhir@123")
					.build();

			Admin admin = adminService.create(studentCreateRequest);
			logger.info("Admin created is : {}",admin);
		}
	}
}
