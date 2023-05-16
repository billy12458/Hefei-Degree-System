package com.school.springboot.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncrementDTO implements Serializable {
    
    @NotBlank(message = "学校名称不能为空！")
    private String schoolName;

    @NotNull(message = "修改的数量不能为空！")
    @Range(min = 0,max = 100,message = "修改的数量必须符合规范！")
    private Integer number;

}
