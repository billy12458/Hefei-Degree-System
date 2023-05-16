package com.school.springboot.controller;

import jakarta.validation.Valid;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.school.springboot.dto.IncrementDTO;
import com.school.springboot.dto.LeaderDTO;
import com.school.springboot.entity.School;
import com.school.springboot.entity.tool.Result;
import com.school.springboot.service.DistrictService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings("all")
@CrossOrigin(origins = { "http://localhost:8130" })
@RequestMapping(value = "/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping(value = "/increment", method = RequestMethod.PUT)
    public Result incrementDegree(@RequestBody @Valid IncrementDTO incrementDTO) {
        String result = new StringBuilder().append("学位增加了")
                .append(districtService.incrementNumberOfDegree(incrementDTO)).append("个！").toString();
        return Result.success(result);
    }

    @RequestMapping(value = "/decrement", method = RequestMethod.PUT)
    public Result decrementDegree(@RequestBody IncrementDTO incrementDTO) {
        String result = new StringBuilder().append("学位增加了")
                .append(districtService.decrementNumberOfDegree(incrementDTO)).append("个！").toString();
        return Result.success("学位成功减少！");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result getAllSchoolsInDistrict() {
        return Result.success(districtService.getAllSchools());
    }

    @RequestMapping(value = "/all/{district}", method = RequestMethod.GET)
    public Result getAllSchoolsInDistrict(@PathVariable("district") String district) {
        return Result.success(districtService.getAllSchoolsInSameDistrict(district));
    }

    @RequiresUser
    @RequiresRoles(value = "MOE")
    @RequestMapping(value = "/search" , method = RequestMethod.GET)
    public Result getSchoolByName(@RequestParam("name") String schoolName) {
        return Result.success(districtService.getSchoolByName(schoolName));
    }

    @RequiresUser
    @RequiresRoles(value = "MOE")
    @RequestMapping(value = "/add/school", method = RequestMethod.POST)
    public Result addSchoolWithPermission(@RequestBody School school) {
        districtService.addSchool(school);
        return Result.success("添加学校成功！");
    }

    @RequiresUser
    @RequiresRoles(value = "MOE", logical = Logical.AND)
    @RequestMapping(value = "/leader/set", method = RequestMethod.PUT)
    public Result setLeaderWithPermission(@RequestBody LeaderDTO leaderDTO) {
        districtService.bindLeaderOfSchool(leaderDTO);
        return Result.success("学校负责人设置成功！");
    }

}
