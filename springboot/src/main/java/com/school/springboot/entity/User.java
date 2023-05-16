package com.school.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements Serializable {

    private String phone;

    private String password;

    private String salt;

    private Integer identity;

    private String username;

    private Integer status;

    private List<Log> userLogs;
    
}
