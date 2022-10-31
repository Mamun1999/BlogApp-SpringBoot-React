package com.mamun.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApisApplication.class, args);

	}
		@Bean
		public ModelMapper modelMapper(){
			//We have declared bean because we can use ModelMapper as an object and we will autowired this wehere it will be nedded
			return new ModelMapper();
		}
	

}
