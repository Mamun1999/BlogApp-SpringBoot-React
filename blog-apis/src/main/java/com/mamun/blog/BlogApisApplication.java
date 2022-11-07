package com.mamun.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BlogApisApplication implements CommandLineRunner {
    
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(BlogApisApplication.class, args);



	}
		@Bean
		public ModelMapper modelMapper(){
			//We have declared bean because we can use ModelMapper as an object and we will autowired this wehere it will be nedded
			return new ModelMapper();
		}
		@Override
		public void run(String... args) throws Exception {
			
			System.out.println(this.bCryptPasswordEncoder.encode("abc"));
			
		}
	

}
