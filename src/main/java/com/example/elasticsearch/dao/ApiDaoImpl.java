package com.example.elasticsearch.dao;

import com.example.elasticsearch.model.ApiMonitorEntity;
import com.example.elasticsearch.model.User;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.ScriptField;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;




@Repository
public class ApiDaoImpl {

    private String indexName="api-monitor-2019.11.22";
    private String typeName = "doc";

    @Autowired
    private ElasticsearchTemplate esTemplate;


    public List<ApiMonitorEntity> getByOptype(String optype) {
        /*
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(matchQuery("opType", optype).operator(Operator.AND))
                .withIndices("api-monitor-2019.11.23")
                .withTypes("doc")
                .build();
         */
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(matchQuery("opType", optype).operator(Operator.AND))
                .build();
        return esTemplate.queryForList(searchQuery, ApiMonitorEntity.class);
    }

    public String groupByOptype() {
        /*
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(matchQuery("opType", optype).operator(Operator.AND))
                .withIndices("api-monitor-2019.11.23")
                .withTypes("doc")
                .build();
         */
        /*
        Script scriptDef = new Script(
                ScriptType.INLINE,
                "painless",
                "doc['exchange'].value + '_' + doc['location'].value + '_' + doc['opType'].value + '_' + doc['host.keyword'].value",
                null);

         */
        /*
        Script scriptDef = new Script(
                ScriptType.INLINE,
                "painless",
                "doc['exchange'].value",
                null);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withScriptField(new ScriptField("group_by_optype", scriptDef))
                .build();
        return esTemplate.query(searchQuery, ApiMonitorEntity.class);
         */
        return "aaaa";
    }
}