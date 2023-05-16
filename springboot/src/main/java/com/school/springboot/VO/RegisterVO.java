package com.school.springboot.VO;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shijiu
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO implements Serializable {

    @NotBlank(message = "电话号码不能为空！")
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{4,8}$", message = "电话号码格式不符合规范！")
    private String phone;

    @NotBlank(message = "密码不能为空！")
    @Size(min = 1, max = 20)
    private String password;

    @NotBlank(message = "用户名不能为空！")
    @Size(min = 1, max = 32, message = "用户名长度必须在{min}到{max}之间！")
    private String userName;

}
