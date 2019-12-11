package com.example.elasticsearch.controller;

import com.example.elasticsearch.dao.ApiDaoImpl;
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
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiDaoImpl apiDao;


    /*
    @RequestMapping("/all")
    public Page<ApiMonitorEntity> getAllUsers() {
        //Page<ApiMonitorEntity> logs = new ArrayList<>();
        return apiRepository.findAll();
    }
     */


    @RequestMapping("/optype")
    public List<ApiMonitorEntity> getByOptype(@RequestParam(value = "optype") String opType) {

        Pageable page = PageRequest.of(0, 20);
        return apiDao.getByOptype(opType);
    }

    @RequestMapping("/groupby")
    public String groupByOptype() {

        return apiDao.groupByOptype();
    }

}
