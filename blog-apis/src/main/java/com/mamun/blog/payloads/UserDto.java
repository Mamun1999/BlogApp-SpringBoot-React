package com.mamun.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min=4, message="Name must be min of characters")
    private String name;
    @Email(message="Email addressed is nt valid")
    private String email;
    
    @NotEmpty
    @Size(min=4, max=15, message="Password must be min of 4 char and maximum of 15 character")
    private String password;
     
    @NotEmpty
    private String about;

    private Set<RoleDto>roles=new HashSet<>();// we will get user with what roles that the usre possese
}
