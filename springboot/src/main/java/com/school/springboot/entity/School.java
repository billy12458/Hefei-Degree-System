package com.school.springboot.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "district")
public class School implements Serializable {
    
    @TableId(value = "schoolId")
    private String schoolId;

    @TableField(value = "district")
    private String district;

    @TableField(value = "schoolName")
    private String schoolName;

    @TableField(value = "total")
    private Integer total;

    @TableField(value = "available")
    private Integer available;

    @TableField(value = "leader")
    private String leader;
}
