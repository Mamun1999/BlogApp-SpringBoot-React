package com.mamun.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostPageInfo {
    
    private List<PostDto> contents;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalpages;
    private boolean lastPage;
    
}
