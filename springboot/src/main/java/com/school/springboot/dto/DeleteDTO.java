package com.school.springboot.dto;

import java.io.Serializable;
import org.apache.ibatis.lang.UsesJava8;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDTO implements Serializable {
    
    @UsesJava8
    private String degreeId;

    private String identity;
    
}
