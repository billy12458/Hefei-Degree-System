package com.school.springboot.dto;

import lombok.Builder;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDTO implements Serializable {
    
    private String content;
}
