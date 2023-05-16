package com.school.springboot.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO implements Serializable {
    
    @NotBlank(message = "用户名不能为空！")
    private String userName;

    @NotBlank(message = "电话号码不能为空！")
    @Size(min = 11, max = 15, message = "电话号码长度应该在{min}到{max}之间！")
    private String phone;

    private boolean status;

}
