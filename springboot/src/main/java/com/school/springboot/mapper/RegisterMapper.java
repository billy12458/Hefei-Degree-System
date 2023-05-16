package com.school.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.school.springboot.dto.RegisterDTO;

@Mapper
@SuppressWarnings("all")
public interface RegisterMapper {
    
    void registerUser(RegisterDTO registerDTO);

}
