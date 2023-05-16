package com.school.springboot.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {
    
    @NotBlank(message = "地址不能为空！")
    @Pattern(regexp = "^合肥市蜀山区", message = "地址格式不符合规范！")
    private String address;
    
}
