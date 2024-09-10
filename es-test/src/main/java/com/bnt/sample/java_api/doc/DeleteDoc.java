package com.bnt.sample.java_api.doc;

import com.bnt.sample.java_api.ConnectElasticsearch;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;

public class DeleteDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            DeleteResponse deleteResponse = client.delete(new DeleteRequest().index("user").id("1001"), RequestOptions.DEFAULT);
            System.out.println(deleteResponse.toString());
        });
    }
}
