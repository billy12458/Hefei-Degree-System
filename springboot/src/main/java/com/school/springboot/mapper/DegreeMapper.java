package com.school.springboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.springboot.dto.AddressSearchDTO;
import com.school.springboot.dto.AgreeDTO;
import com.school.springboot.dto.ChangeDTO;
import com.school.springboot.dto.CurrentDTO;
import com.school.springboot.dto.PostponeDTO;
import com.school.springboot.dto.SelfSearchDTO;
import com.school.springboot.entity.Degree;

@Mapper
@SuppressWarnings("all")
public interface DegreeMapper extends BaseMapper<Degree> {

    String getIdentityByUserName(String userName);

    List<Degree> getAllDegrees();

    List<Degree> getAllSchools();

    List<Degree> getDegreeByDegreeId1(String degreeId);

    List<Degree> getDegreeBySearch(SelfSearchDTO searchDTO);
    
    List<Degree> getDegreeByAddressSearch(AddressSearchDTO searchDTO);

    void addDegree(Degree degree);

    // 根据房产证号改学校
    void changeSchoolSingle(ChangeDTO changeDTO);

    void changeSchool(List<String> degreeIds);

    void postponeGraduation(PostponeDTO postponeDTO);

    void agreeChangeSchool(AgreeDTO agreeDTO);

    void deleteDegreeById(@Param("degreeId") String degreeId, @Param("identity") String identity);

    List<Degree> getDegreeByAddress(String address);

    List<Degree> getDegreeByIdentity(String identity);

    List<Degree> getDegreeOfCurrentSchool(String phone);

    List<Degree> getUnauditedDegrees(CurrentDTO currentDTO);

    // List<Degree> getDegreesByDegreeId(String degreeId);

}
