package com.example.elasticsearch.dao;

import com.example.elasticsearch.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
    List<User> findByCreationDateBetween(Long startTime, Long endTime, Sort sort);
    Page<User> findByCreationDateBetween(Long startTime, Long endTime, Pageable pageable);
    List<User> findByUserId(String userId);

    List<User> findByName(String name);
    List<User> findAllByOrderByNameDesc();
    List<User> findByNameAndUserId(String userId, String name);
    List<User> findByCreationDateBetweenOrderByNameDesc(Long startTime, Long endTime);
    List<User> findByNameInAndCreationDateBetween(Collection<String> names, Long startTime, Long endTime);

    /*
    List<User> findByCreationDateBetweenOrderByNameDesc(Long startTime, Long endTime);
    List<User> findByCreationDateBetweenOrderByNameAsc(Long startTime, Long endTime);
    List<User> findByCreationDateBetweenOrderByNameDescPage(Long startTime, Long endTime);
    */
}
