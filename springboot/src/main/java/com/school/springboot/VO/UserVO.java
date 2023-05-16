package com.school.springboot.VO;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.school.springboot.entity.Log;

/**
 * @author shijiu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    private String phone;

    private String username;

    private Integer status;

    private Integer identity;

    private List<Log> userLogs;
    
}
