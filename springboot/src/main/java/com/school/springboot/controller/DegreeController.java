package com.school.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.checkerframework.checker.units.qual.degrees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.school.springboot.annotation.ShiroLogin;
import com.school.springboot.dto.AddressDTO;
import com.school.springboot.dto.AddressSearchDTO;
import com.school.springboot.dto.AgreeDTO;
import com.school.springboot.dto.ChangeDTO;
import com.school.springboot.dto.CurrentDTO;
import com.school.springboot.dto.DeleteDTO;
import com.school.springboot.dto.PostponeDTO;
import com.school.springboot.dto.SelfSearchDTO;
import com.school.springboot.entity.Degree;
import com.school.springboot.entity.tool.Result;
import com.school.springboot.service.DegreeService;
import com.school.springboot.service.DistrictService;
import com.school.springboot.utils.SaTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings("all")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "http://192.168.10.6:8081" })
@RequestMapping(value = "/degree")
@RequiredArgsConstructor
// 别忘了加上所有的角色！
public class DegreeController {

    //想要省略冗余的@Autowireds，就可以使用RequiredArgsConstructor，字段必须是final且非空
    private final DegreeService degreeService;

    private final DistrictService districtService;

    @RequiresUser
    @RequiresRoles(value = "MOE")
    @RequestMapping(value = "/all", method = RequestMethod.PUT)
    public Result getAllDegreesInDistrict() {
        return Result.success(degreeService.getAllDegrees());
    }

    @RequiresUser
    @RequestMapping(value = "/all/my", method = RequestMethod.PUT)
    public Result getMyDegrees() {
        return Result.success(degreeService
                .getDegreeByIdentity(
                        new SelfSearchDTO(null, degreeService.getIdentityByUserName(SaTokenUtils.getCurrentUserName()))));
    }

    @RequiresUser
    @RequiresRoles(value = "school")
    @RequestMapping(value = "/schools", method = RequestMethod.PUT)
    public Result getAllSchoolsList() {
        return Result.success(200, "学校信息获取成功！", degreeService.getAllSchools());
    }

    // 加入hibernate-validator，关键数据要好好校验
    @ShiroLogin
    @RequestMapping(value = "/search/self", method = RequestMethod.POST)
    public Result getMyDegrees(@RequestBody SelfSearchDTO selfSearchDTO) {
        return Result.success(200, "查询成功！",
                degreeService.getDegreeBySearch(selfSearchDTO));
    }

    @ShiroLogin
    @RequestMapping(value = "/search/Address", method = RequestMethod.POST)
    public Result getMyDegreesByDegreeId(@RequestBody AddressSearchDTO searchDTO) {
        return Result.success(200, "查询成功！",
                degreeService.getDegreeByAddressSearch(searchDTO));
    }

    @RequiresUser
    @RequestMapping(value = "/search/{degreeId}", method = RequestMethod.POST)
    public Result getMyDegrees(@PathVariable("degreeId") String degreeId) {
        return Result.success(200, "查询成功！", degreeService.getDegreeByDegreeId1(degreeId));
    }

    @RequiresUser
    @RequiresRoles(value = "school")
    @RequestMapping(value = "/audit/agree", method = RequestMethod.POST)
    public Result agreeChangeSchool(@RequestBody AgreeDTO agreeDTO) {
        degreeService.agreeChangeSchool(agreeDTO);
        return Result.success(200, "审核成功！", "审核成功！");
    }

    @RequiresUser
    @RequiresRoles(value = "MOE")
    @RequestMapping(value = "/search/address", method = RequestMethod.PUT)
    public Result getMyDegreesByAddress(@RequestBody AddressDTO addressDTO) {
        return Result.success(200, "查询成功！", degreeService.getDegreeByAddress(addressDTO.getAddress()));
    }

    // 没问题了
    @RequiresUser
    @RequiresRoles(value = "school")
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Result addDegreeWithPermission(@RequestBody Degree degree) {
        degreeService.addDegree(degree);
        return Result.success("学位添加成功！");
    }

    @RequiresUser
    @RequiresRoles(value = "school")
    @RequestMapping(value = "/delete.do", method = RequestMethod.DELETE)
    public Result deleteDegreeWithPermission(@RequestBody DeleteDTO deleteDTO) {
        degreeService.deleteDegree(deleteDTO);
        return Result.success("学位删除成功！");
    }

    // 没问题了
    @RequiresUser
    @RequiresRoles(value = "school")
    @RequestMapping(value = "/currentSchool", method = RequestMethod.PUT)
    public Result getCurrentSchool() {
        return Result.success(degreeService.getDegreeOfCurrentSchool(new CurrentDTO(SaTokenUtils.getUserName())));
    }

    // 没问题了
    @RequiresUser
    @RequiresRoles(value = "school")
    @RequestMapping(value = "/currentSchool/unaudit", method = RequestMethod.PUT)
    public Result getUnauditedDegreesInCurrentSchool() {
        return Result.success(degreeService.getUnauditedDegrees(new CurrentDTO(SaTokenUtils.getUserName())));
    }

    @RequiresUser
    @RequiresRoles(value = "school")
    @RequestMapping(value = "/postpone", method = RequestMethod.PUT)
    public Result postponeGraduation(@RequestBody @Validated PostponeDTO postponeDTO) {
        degreeService.postponeGraduation(postponeDTO);
        return Result.success(200, "延迟毕业成功！", "延迟毕业成功！");
    }

    // 先提交，后面再审核
    @RequiresUser
    @RequiresRoles(value = "school")
    @RequestMapping(value = "/changeSchool.do", method = RequestMethod.PUT)
    public Result changeSchoolWithPermission(@RequestBody ChangeDTO changeDTO) {
        degreeService.changeSchoolSingle(changeDTO);
        // districtService.changeSchool(changeDTO.getOriginSchoolName(),
        // changeDTO.getDestSchoolName(),
        // changeDTO.getDegreeIds().size());
        return Result.success("学位转出成功，请等待审核！");
    }

}
