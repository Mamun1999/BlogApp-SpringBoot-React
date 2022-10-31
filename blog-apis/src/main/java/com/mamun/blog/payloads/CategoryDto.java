package com.mamun.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    
    private Integer categoryId;

    @NotEmpty
    @Size(min=4)
    private String categoryTitle;

    @NotEmpty
    @Size(max=20)
    private String categoryDescription;
}
