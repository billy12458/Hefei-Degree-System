package com.school.springboot.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO implements Serializable {

    @NotBlank(message = "用户名不能为空！")
    @Size(min = 1, max = 32, message = "用户名长度必须在{min}到{max}之间！")
    private String userName;
    
    @NotBlank(message = "密码不能为空！")
    @Size(min = 1, max = 20, message = "密码长度应该在{min}到{max}之间")
    private String password;

    private String phone;

}
