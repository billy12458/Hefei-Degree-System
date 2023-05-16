package com.school.springboot.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.springboot.dto.AddressSearchDTO;
import com.school.springboot.dto.AgreeDTO;
import com.school.springboot.dto.ChangeDTO;
import com.school.springboot.dto.CurrentDTO;
import com.school.springboot.dto.DeleteDTO;
import com.school.springboot.dto.PostponeDTO;
import com.school.springboot.dto.SelfSearchDTO;
import com.school.springboot.entity.Degree;
import com.school.springboot.mapper.DegreeMapper;

@Service(value = "degreeService")
@SuppressWarnings("all")
@Transactional
public class DegreeService {

    @Autowired
    private DegreeMapper degreeMapper;

    // @Autowired
    // private QueryWrapper<Degree> queryWrapper;

    public String getIdentityByUserName(String userName) {
        return degreeMapper.getIdentityByUserName(userName);
    }

    public List<Degree> getDegreeByDegreeId1(String degreeId) {
        return degreeMapper.getDegreeByDegreeId1(degreeId);
    }

    // public List<Degree> getAllSchools() {
    // return degreeMapper.getAllSchools();
    // }

    public List<Degree> getAllSchools() {
        QueryWrapper<Degree> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT schoolName");
        return degreeMapper.selectList(queryWrapper);
    }

    // public List<Degree> getAllDegrees() {
    // return degreeMapper.getAllDegrees();
    // }
    public List<Degree> getAllDegrees() {
        QueryWrapper<Degree> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("degreeId", "address", "zone", "occupied", "startDate", "school", "studentName");
        return degreeMapper.selectList(queryWrapper);
    }

    // public List<Degree> getDegreeBySearch(SelfSearchDTO selfSearchDTO) {
    // QueryWrapper<Degree> queryWrapper = new QueryWrapper<>();
    // queryWrapper.eq(selfSearchDTO.getIdentity() != null, "identity",
    // selfSearchDTO.getIdentity())
    // .eq(selfSearchDTO.getStudentName() != null, "studentName",
    // selfSearchDTO.getStudentName());
    // return degreeMapper.getDegreeBySearch(selfSearchDTO);
    // }

    public List<Degree> getDegreeBySearch(SelfSearchDTO selfSearchDTO) {
        QueryWrapper<Degree> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(selfSearchDTO.getIdentity() != null, "identity", selfSearchDTO.getIdentity())
                .eq(selfSearchDTO.getStudentName() != null, "studentName", selfSearchDTO.getStudentName());
        return degreeMapper.selectList(queryWrapper);
    }

    public List<Degree> getDegreeByAddressSearch(AddressSearchDTO searchDTO) {
        return degreeMapper.getDegreeByAddressSearch(searchDTO);
    }

    // @Cacheable(value = "cache1", key = "#currentDTO.phone")
    // public List<Degree> getDegreeOfCurrentSchool(CurrentDTO currentDTO) {
    // return degreeMapper.getDegreeOfCurrentSchool(currentDTO.getPhone());
    // }

    public List<Degree> getDegreeOfCurrentSchool(CurrentDTO currentDTO) {
        return degreeMapper.selectList(new QueryWrapper<Degree>()
                .select("degreeId", "address", "zone", "occupied", "startDate", "school", "studentName")
                .inSql("school", "SELECT schoolName FROM district WHERE leader = " + currentDTO.getPhone())
                .eq("status", 0));
        // return null;
    }

    public List<Degree> getUnauditedDegrees(CurrentDTO currentDTO) {
        return degreeMapper.getUnauditedDegrees(currentDTO);
    }

    @Cacheable(value = "cache1", key = "#selfSearchDTO.identity")
    public List<Degree> getDegreeByIdentity(SelfSearchDTO selfSearchDTO) {
        return degreeMapper.getDegreeByIdentity(selfSearchDTO.getIdentity());
    }

    public void addDegree(Degree degree) {
        degree.setStartDate(new Date());
        degreeMapper.addDegree(degree);
    }

    public void deleteDegree(DeleteDTO deleteDTO) {
        degreeMapper.deleteDegreeById(deleteDTO.getDegreeId(), deleteDTO.getIdentity());
    }

    public void agreeChangeSchool(AgreeDTO agreeDTO) {
        degreeMapper.agreeChangeSchool(agreeDTO);
    }

    public List<Degree> getDegreeByAddress(String address) {
        return degreeMapper.getDegreeByAddress(address);
    }

    public void postponeGraduation(PostponeDTO postponeDTO) {
        Date startDate = postponeDTO.getStartDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.YEAR, postponeDTO.getTimeToPostpone());
        postponeDTO.setStartDate(cal.getTime());
        degreeMapper.postponeGraduation(postponeDTO);
    }

    public void changeSchool(List<String> degreeIds) {
        degreeMapper.changeSchool(degreeIds);
    }

    public void changeSchoolSingle(ChangeDTO changeDTO) {
        degreeMapper.changeSchoolSingle(changeDTO);
    }
}
