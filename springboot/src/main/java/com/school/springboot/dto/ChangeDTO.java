package com.school.springboot.dto;

import java.io.Serializable;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDTO implements Serializable {
    
    @NotBlank(message = "目的学校不能为空！")
    private String destSchoolName;

    private List<String> degreeIds;

    private String degreeId;

    @NotBlank(message = "身份证不能为空！")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "身份证不符合规范！")
    private String identity;

}
