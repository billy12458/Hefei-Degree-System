package com.school.springboot.entity;

import java.io.Serializable;
import java.util.Date;
import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "degree")
public class Degree implements Serializable {
    
    @TableId(value = "degreeId")
    private String degreeId;

    @TableField(value = "address")
    private String address;

    @TableField(value = "zone")
    private String zone;

    @TableField(value = "occupied")
    private boolean occupied;

    @TableField(value = "identity")
    private String identity;

    @TableField(value = "studentName")
    private String studentName;

    @TableField(value = "school")
    private String school;

    @TableField(value = "startDate")
    @JSONField(format = "yyyy/MM/dd")
    private Date startDate;

    @TableField(value = "status")
    private Integer status;

}
