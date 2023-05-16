package com.school.springboot.service;

import java.util.List;
import java.util.logging.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.school.springboot.entity.Log;
import com.school.springboot.mapper.LogMapper;

@Service(value = "logService")
@SuppressWarnings("all")
@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
public class LogService {
    
    @Autowired
    private LogMapper logMapper;

    public List<Log> getAllLogs() {
        return logMapper.selectAllLogs();
    }

    public List<Log> getAllLogsWithMP() {
        return logMapper.selectList(null);
    }

}
