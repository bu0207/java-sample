package com.bnt.sample.java_api.index;

import com.bnt.sample.java_api.ConnectElasticsearch;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

public class GetIndex {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            GetIndexRequest user2 = new GetIndexRequest("user2");
            GetIndexResponse response = client.indices().get(user2, RequestOptions.DEFAULT);
            System.out.println("aliases:"+response.getAliases());
            System.out.println("mappings:"+response.getMappings());
            System.out.println("settings:"+response.getSettings());
        });
    }
}
