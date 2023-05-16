package com.school.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.school.springboot.dto.IncrementDTO;
import com.school.springboot.dto.LeaderDTO;
import com.school.springboot.entity.School;
import com.school.springboot.mapper.DistrictMapper;

@Service(value = "districtService")
@SuppressWarnings("all")
@Transactional(rollbackFor = Exception.class)
public class DistrictService {
    
    @Autowired
    private DistrictMapper districtMapper;

    public List<School> getAllSchools() {
        return districtMapper.getAllSchools();
    }

    @Cacheable(value = "schoolCache", key = "#district")
    public List<School> getAllSchoolsInSameDistrict(String district) {
        return districtMapper.getAllSchoolsInSameDistrict(district);
    }

    public List<School> getSchoolByName(String schoolName) {
        return districtMapper.getSchoolByName(schoolName);
    }

    public Integer incrementNumberOfDegree(Integer number, String schoolName) {
        return districtMapper.incrementNumberOfDegree(number, schoolName);
    }

    public Integer incrementNumberOfDegree(IncrementDTO incrementDTO) {
        return districtMapper.incrementNumberOfDegree(incrementDTO.getNumber(), incrementDTO.getSchoolName());
    }

    public Integer decrementNumberOfDegree(Integer number, String schoolName) {
        return districtMapper.decrementNumberOfDegree(number, schoolName);
    }

    public Integer decrementNumberOfDegree(IncrementDTO incrementDTO) {
        return districtMapper.decrementNumberOfDegree(incrementDTO.getNumber(), incrementDTO.getSchoolName());
    }

    public Integer incrementByDefault(String schoolName) {
        return districtMapper.incrementNumberOfDegree(1, schoolName);
    }

    public Integer decrementByDefault(String schoolName) {
        return districtMapper.decrementNumberOfDegree(1, schoolName);
    }

    public Integer addSchool(School school) {
        return districtMapper.addSchool(school);
    }

    public Integer bindLeaderOfSchool(LeaderDTO leaderDTO) {
        return districtMapper.bindLeaderOfSchool(leaderDTO.getLeader(), leaderDTO.getSchoolName());
    }

    //换学校需要几步：1、学位信息修改；2、原学校和目的学校学位个数修改；
    public void changeSchool(String originSchoolName, String destSchoolName, Integer number) {

    }

}
