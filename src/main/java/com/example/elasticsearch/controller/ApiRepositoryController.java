

package com.example.elasticsearch.controller;

import com.example.elasticsearch.dao.ApiRepository;
import com.example.elasticsearch.dao.UserRepository;
import com.example.elasticsearch.model.ApiMonitorEntity;
import com.example.elasticsearch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repoapi")
public class ApiRepositoryController {
    @Autowired
    private ApiRepository apiRepository;


    @RequestMapping("/optype")
    public Page<ApiMonitorEntity> getUserById(@RequestParam(value = "optype") String opType) {

        Pageable page = PageRequest.of(0, 20, Sort.by(Sort.Order.asc("startTime"), Sort.Order.desc("opType")));
        return apiRepository.findByOpType(opType, page);
    }

    @RequestMapping("/groupby")
    public Page<ApiMonitorEntity> getGroupBy() {

        Pageable page = PageRequest.of(0, 100);
        return apiRepository.findDistinctByExchangeAndLocationAndOpTypeAndHost(page);
    }
}
