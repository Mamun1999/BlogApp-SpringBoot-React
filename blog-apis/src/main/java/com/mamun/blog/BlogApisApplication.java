package com.mamun.blog;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mamun.blog.config.Constants;
import com.mamun.blog.entities.Role;
import com.mamun.blog.repositories.RoleRepo;



@SpringBootApplication
public class BlogApisApplication implements CommandLineRunner {
    
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleRepo roleRepo;
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
			
			System.out.println(this.bCryptPasswordEncoder.encode("1234"));
			// System.out.println(this.bCryptPasswordEncoder.encode("12345"));

			try {
				Role role=new Role();
			role.setId(Constants.ADMIN_ROLE);
			role.setName("ROLE_ADMIN");

			Role role2=new Role();
			role2.setId(Constants.NORMAL_ROLE);
			role2.setName("ROLE_NORMAL");

			// List<Role> roles=new ArrayList<>();
			List<Role> roles=List.of(role,role2);
			// roles.add(role);
			// roles.add(role2);
            
		List<Role> savedRoles=	this.roleRepo.saveAll(roles);
			savedRoles.forEach(r->{
				System.out.println(r.getName());
			});
		
			} catch (Exception e) {
				System.out.println(e);
			}
	

}
}
