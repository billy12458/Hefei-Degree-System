package com.school.springboot.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostponeDTO implements Serializable {
    
    private String identity;

    private Integer timeToPostpone;

    private Date startDate;

}
