package com.bnt.sample.java_api.doc;

import com.bnt.sample.java_api.ConnectElasticsearch;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;

/**
 * 批量删除
 */
public class BatchDeleteDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add(new DeleteRequest().index("user").id("1001"));
            bulkRequest.add(new DeleteRequest().index("user").id("1002"));
            bulkRequest.add(new DeleteRequest().index("user").id("1003"));
            BulkResponse responses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            System.out.println("took:" + responses.getTook());
            System.out.println("items:" + responses.getItems());
        });
    }
}
