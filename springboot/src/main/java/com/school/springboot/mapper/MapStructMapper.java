// package com.school.springboot.mapper;

// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.ReportingPolicy;
// import org.mapstruct.factory.Mappers;

// import com.school.springboot.dto.RegisterDTO;
// import com.school.springboot.entity.User;

// @Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
// public interface MapStructMapper {
    
//     MapStructMapper mapStructMapper = Mappers.getMapper(MapStructMapper.class);

//     @Mapping(target = "userName", source = "userName")
//     User registerDTOToUser(RegisterDTO registerDTO);
    
// }
