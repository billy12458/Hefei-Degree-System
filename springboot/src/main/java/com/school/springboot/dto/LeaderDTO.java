package com.school.springboot.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderDTO implements Serializable {
    
    private String leader;

    private String schoolName;
    
}
