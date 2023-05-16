package com.school.springboot.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements Serializable {

    private Long phone;
    
    private String password;

    private String salt;

}
