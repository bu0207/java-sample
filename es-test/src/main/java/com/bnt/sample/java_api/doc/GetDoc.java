package com.bnt.sample.java_api.doc;

import com.bnt.sample.java_api.ConnectElasticsearch;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;

/**
 * 查询文档
 */
public class GetDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            GetRequest getRequest = new GetRequest();
            GetRequest user = getRequest.index("user").id("1001");
            GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);

            System.out.println("_index:" + response.getIndex());
            System.out.println("_type:" + response.getType());
            System.out.println("_id:" + response.getId());
            System.out.println("source:" + response.getSourceAsString());
        });
    }
}
