package com.school.springboot.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.school.springboot.annotation.ShiroLogin;
import com.school.springboot.dto.SearchDTO;
import com.school.springboot.entity.tool.Result;
import com.school.springboot.service.MeiliSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.meilisearch.sdk.exceptions.*;

@RestController
@Slf4j
@SuppressWarnings("all")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "http://192.168.10.6:8081" })
@RequestMapping(value = "/splunk")
@RequiredArgsConstructor
public class SplunkController {

    public final MeiliSearchService meiliSearchService;

    // public final SplunkService splunkTestService;

    // @ShiroLogin
    // @RequestMapping(value = "/test", method = RequestMethod.GET)
    // public Result getAllDegreesInDistrict() throws IOException {
    //     // splunkTestService.testSplunkSearch();
    //     return Result.success(splunkTestService.testSplunkSearch());
    // }

    @ShiroLogin
    @ResponseBody
    @RequestMapping(value = "/ms/search", method = RequestMethod.POST)
    public Result getUserSearchWithMS(@RequestBody SearchDTO searchDTO) throws IOException, MeilisearchException {
        return Result.success(meiliSearchService.getUserInfoSearchResult(searchDTO));
    }

}
