package com.school.springboot.utils;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author shijiu
 */
public class LogUtils {

    public static HashMap<String, String> map = new HashMap<>();

    public static void init() {
        map.put("getAllSchoolsList", "获取所有学校信息");
        map.put("getMyDegrees", "搜索学位信息");
        map.put("agreeChangeSchool", "同意转学操作");
        map.put("getMyDegreesByAddress", "根据地址搜索房产信息");
        map.put("changeSchool.do","转校");
        map.put("search/address","根据地址查询学位");
        map.put("add","添加学位");
        map.put("delete","删除学位");
        map.put("currentSchool","获取学位信息");
        map.put("increment","增加学位");
        map.put("auditSpecificUser","审核学位");
        map.put("decrement","减少学位");
        map.put("all","获取这个学区所有学校");
        map.put("search","搜索学位");
        map.put("getCurrentSchool","获取本学校学位信息");
    }

    public static String getMethod(String target){
        init();
        String method = map.get(target);
        return method;
    }

    public static void main(String[] args) {
        String getCurrentSchool = getMethod("getCurrentSchool");
        System.out.println(getCurrentSchool);
    }

}
