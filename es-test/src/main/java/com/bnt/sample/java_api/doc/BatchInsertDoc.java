package com.bnt.sample.java_api.doc;

import com.bnt.sample.java_api.ConnectElasticsearch;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;

/**
 * 批量新增文档
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/9/17 下午7:08 bnt
 * @history
 */
public class BatchInsertDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add(new IndexRequest().index("user").id("1001").source("n", "zhangsan", "age", 30, "sex", "男"))
        });
    }
}
