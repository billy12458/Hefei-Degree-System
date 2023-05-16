package com.school.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.springboot.entity.School;
import java.util.List;

@Mapper
@SuppressWarnings("all")
public interface DistrictMapper extends BaseMapper<School> {

    List<School> getAllSchools();

    List<School> getSchoolByName(String schoolName);

    Integer addSchool(School school);

    Integer bindLeaderOfSchool(@Param("leader") String leader, @Param("schoolName") String schoolName);

    Integer incrementNumberOfDegree(@Param("number") Integer number, @Param("schoolName") String schoolName);

    Integer decrementNumberOfDegree(@Param("number") Integer number, @Param("schoolName") String schoolName);

    List<School> getAllSchoolsInSameDistrict(String district);

}
