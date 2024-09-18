package com.bnt.sample.java_api.doc;

import com.bnt.sample.java_api.ConnectElasticsearch;
import com.bnt.sample.java_api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * 新增文档
 */
public class InsertDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            IndexRequest request = new IndexRequest();
            request.index("user").id("1001");

            User user = new User();
            user.setName("zhangsan");
            user.setAge(20);
            user.setSex("男");

            ObjectMapper objectMapper = new ObjectMapper();
            String produceJson = objectMapper.writeValueAsString(user);
            request.source(produceJson, XContentType.JSON);

            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            System.out.println("_index:" + response.getIndex());
            System.out.println("_id:" + response.getId());
            System.out.println("_result:" + response.getResult());
        });
    }
}
