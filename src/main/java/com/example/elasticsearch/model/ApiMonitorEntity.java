package com.example.elasticsearch.model;






import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;


@Getter
@Setter
@ToString
//@Document(indexName = "api-monitor-yyyy.mm.dd", type = "doc")
@Document(indexName = "api-monitor-2019.11.22", type = "doc")
public class ApiMonitorEntity {

    @Field
    private Date startTime;
    @Field
    private Date endTime;
    @Field
    private String class_name;
    @Field
    private String thread_name;
    @Field
    private String timestamp;
    @Field
    private String path;
    @Field
    private String host;
    @Id
    private String opType;
    @Field
    private Boolean inMemory;
    @Field
    private String exchange;
    @Field
    private Long latency;
    @Field
    private String location;
    //@Field
    //private String tags;


}
