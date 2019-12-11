package com.example.elasticsearch.dao;

import com.example.elasticsearch.model.User;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import org.elasticsearch.index.query.MatchQueryBuilder;


@Repository
public class UserDAOImpl implements UserDAO {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Value("${elasticsearch.user.type}")
    private String userTypeName;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public List<User> getAllUsers() {
        SearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery()).build();
        return esTemplate.queryForList(getAllQuery, User.class);
    }

    @Override
    public List<User> getUserById(String userId) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(matchQuery("userId", userId).operator(Operator.AND)).build();
        return esTemplate.queryForList(searchQuery, User.class);
    }

    @Override
    public User addNewUser(User user) {

        IndexQuery userQuery = new IndexQuery();
        userQuery.setIndexName(indexName);
        userQuery.setType(userTypeName);
        userQuery.setObject(user);

        LOG.info("User indexed: {}", esTemplate.index(userQuery));
        esTemplate.refresh(indexName);

        return user;
    }

    @Override
    public Object getAllUserSettings(String name) {

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("name", name)).build();
                
        List<User> users = esTemplate.queryForList(searchQuery, User.class);
        if(!users.isEmpty()) {
            System.out.println("settings: "+users.get(0).getUserSettings().toString());
            return users.get(0).getUserSettings();
        }

        return null;
    }

    @Override
    public String getUserSetting(String name, String key) {

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("name", name)).build();
        List<User> users = esTemplate.queryForList(searchQuery, User.class);
        if(!users.isEmpty()) {
            return users.get(0).getUserSettings().get(key);
        }

        return null;
    }

    @Override
    public String addUserSetting(String name, String key, String value) {

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("name", name)).build();
        List<User> users = esTemplate.queryForList(searchQuery, User.class);
        if(!users.isEmpty()) {

            User user = users.get(0);
            user.getUserSettings().put(key, value);

            IndexQuery userQuery = new IndexQuery();
            userQuery.setIndexName(indexName);
            userQuery.setType(userTypeName);
            userQuery.setId(user.getUserId());
            userQuery.setObject(user);
            esTemplate.index(userQuery);
            return "Setting added.";
        }

        return null;
    }


    /*
    public List<User> getUserById_1(String userId) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(matchQuery("userId", userId)).build();
        List<User> users = esTemplate.queryForList(searchQuery, User.class);
        return users;
    }

    public List<User> getUserById_2(String userId) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withFilter(boolFilter().must(termFilter("userId", userId))).build();
        List<User> users = esTemplate.queryForList(searchQuery, User.class);
        return users;
    }
    */

}
