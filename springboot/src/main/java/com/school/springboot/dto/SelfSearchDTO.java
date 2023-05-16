package com.school.springboot.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelfSearchDTO implements Serializable {

    @NotBlank(message = "姓名不能为空！")
    private String studentName;

    //这个就是输入身份证号查询
    @JSONField
    @NotBlank(message = "身份证不能为空！")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "身份证不符合规范！")
    private String identity;
    
}
