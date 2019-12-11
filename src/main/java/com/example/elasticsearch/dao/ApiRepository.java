package com.example.elasticsearch.dao;

import com.example.elasticsearch.model.ApiMonitorEntity;
import com.example.elasticsearch.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository  extends ElasticsearchRepository<ApiMonitorEntity, String> {
    Page<ApiMonitorEntity> findByOpType(String optype, Pageable page);
    Page<ApiMonitorEntity> findDistinctByExchangeAndLocationAndOpTypeAndHost(Pageable page);
}
