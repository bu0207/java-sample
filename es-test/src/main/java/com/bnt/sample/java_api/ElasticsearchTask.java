package com.bnt.sample.java_api;

import org.elasticsearch.client.RestHighLevelClient;

public interface ElasticsearchTask {
    void doSomething(RestHighLevelClient client) throws Exception;

}
