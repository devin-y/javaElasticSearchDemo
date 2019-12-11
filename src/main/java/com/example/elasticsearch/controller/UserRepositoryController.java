package com.example.elasticsearch.controller;

import com.example.elasticsearch.dao.UserRepository;
import com.example.elasticsearch.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This class is to demo how ElasticsearchRepository can be used to Save/Retrieve/Delete
 */
@RestController
@RequestMapping("/repo")
public class UserRepositoryController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());


    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/all")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }


    @RequestMapping("/id")
    public List<User> getUserById(@RequestParam(value = "userId") String userId) {
        LOG.info("Getting user with ID: {}", userId);
        return userRepository.findByUserId(userId);
    }

    @RequestMapping("/name")
    public List<User> getUserByName(@RequestParam(value = "name") String name) {
        LOG.info("Getting name ID: {}", name);
        return userRepository.findByName(name);
    }

    @RequestMapping("/name-id")
    public List<User> findByNameAndUserId(@RequestParam(value = "name") String name,
                                          @RequestParam(value = "userId") String userId) {
        LOG.info("Getting name ID: {}", name);
        LOG.info("Getting user with ID: {}", userId);
        return userRepository.findByNameAndUserId(userId, name);
    }

    @RequestMapping("/name-desc")
    public List<User> findAllByOrOrderBOrderByNameDesc() {
        return userRepository.findAllByOrderByNameDesc();
    }


    @RequestMapping("/time-range")
    public List<User> findByCreationDateBetween(@RequestParam(value = "startTime") Long startTime,
                                          @RequestParam(value = "endTime") Long endTime) {
        LOG.info("Getting startTime : {}", startTime);
        LOG.info("Getting endTime: {}", endTime);
        return userRepository.findByCreationDateBetween(startTime, endTime, Sort.unsorted() );
    }

    @RequestMapping("/time-range-desc")
    public List<User> findByCreationDateBetweenOrderByNameDesc(@RequestParam(value = "startTime") Long startTime,
                                                @RequestParam(value = "endTime") Long endTime) {
        LOG.info("Getting startTime : {}", startTime);
        LOG.info("Getting endTime: {}", endTime);
        return userRepository.findByCreationDateBetweenOrderByNameDesc(startTime, endTime);
    }

    @RequestMapping("/time-range-sort-more")
    public List<User> findByCreationDateBetweenSortName(@RequestParam(value = "startTime") Long startTime,
                                                        @RequestParam(value = "endTime") Long endTime) {
        LOG.info("Getting startTime : {}", startTime);
        LOG.info("Getting endTime: {}", endTime);
        Sort sort = Sort.by("name").ascending()
                .and(Sort.by("userId").descending());
        return userRepository.findByCreationDateBetween(startTime, endTime, sort);
    }

    @RequestMapping("/time-range-sort-page")
    public Page<User> findByCreationDateBetweenSortPage(@RequestParam(value = "startTime") Long startTime,
                                                        @RequestParam(value = "endTime") Long endTime) {
        LOG.info("Getting startTime : {}", startTime);
        LOG.info("Getting endTime: {}", endTime);
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Order.asc("name"), Sort.Order.desc("userId")));
        return userRepository.findByCreationDateBetween(startTime, endTime, pageable);
    }



    @RequestMapping("/time-range-name-list")
    public List<User> findByCreationDateBetweenSortPage(@RequestParam(value="names") String names,
                                                        @RequestParam(value = "startTime") Long startTime,
                                                        @RequestParam(value = "endTime") Long endTime) {
        LOG.info("Getting startTime : {}", startTime);
        LOG.info("Getting endTime: {}", endTime);
        Collection<String> nameColl = new ArrayList();
        for (String name : names.split(",")){
            nameColl.add(name);
        }
        return userRepository.findByNameInAndCreationDateBetween(nameColl, startTime, endTime);
    }





    /*
    @RequestMapping("/time-range-name-desc")
    public List<User> findByCreationDateBetweenOrderByNameDesc(@RequestParam(value = "startTime") Long startTime,
                                          @RequestParam(value = "endTime") Long endTime) {
        LOG.info("Getting startTime : {}", startTime);
        LOG.info("Getting endTime: {}", endTime);
        return userRepository.findByCreationDateBetweenOrderByNameDesc(startTime, endTime);
    }

    @RequestMapping("/time-range-name-asc")
    public List<User> findByCreationDateBetweenOrderByNameAsc(@RequestParam(value = "startTime") Long startTime,
                                          @RequestParam(value = "endTime") Long endTime) {
        LOG.info("Getting startTime : {}", startTime);
        LOG.info("Getting endTime: {}", endTime);
        return userRepository.findByCreationDateBetweenOrderByNameAsc(startTime, endTime);
    }
    */

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) {
        LOG.info("Adding user : {}", user);
        userRepository.save(user);
        LOG.info("Added user : {}", user);
        return user;
    }



    /*
    @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
    public Object getAllUserSettings(@PathVariable String userId) {

        User user = userRepository.findOne(userId);
        if (user != null) {
            return user.getUserSettings();
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
    public String getUserSetting(
            @PathVariable String userId, @PathVariable String key) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            return user.getUserSettings().get(key);
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
    public String addUserSetting(
            @PathVariable String userId,
            @PathVariable String key,
            @PathVariable String value) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            user.getUserSettings().put(key, value);
            userRepository.save(user);
            return "Key added";
        } else {
            return "User not found.";
        }
    }
     */
}
