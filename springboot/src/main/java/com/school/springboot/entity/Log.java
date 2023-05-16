package com.school.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log implements Serializable {

    private String phone;

    private String event;

    private Date time;

}
