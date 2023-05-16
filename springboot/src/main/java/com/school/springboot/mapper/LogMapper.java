package com.school.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.springboot.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author shijiu
 */
@Mapper
public interface LogMapper extends BaseMapper<Log>{

    //插入
    int insLog(Log log);
    //查询
    Log selectLogByUsername(String username);
    //查询所有日志
    List<Log> selectAllLogs();

}
