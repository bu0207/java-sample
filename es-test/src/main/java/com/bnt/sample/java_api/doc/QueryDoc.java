package com.bnt.sample.java_api.doc;

import com.bnt.sample.java_api.ConnectElasticsearch;
import com.bnt.sample.java_api.ElasticsearchTask;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

import java.util.Map;

/**
 * 全量查询
 */
public class QueryDoc {

    private static final ElasticsearchTask SEARCH_MATCHALL_QUERY = client -> {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    };


    private static final ElasticsearchTask SEARCH_BY_CONDITION = client -> {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("age","30"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    };


    public static final ElasticsearchTask SEARCH_BY_PAGING = client -> {
        SearchRequest searchRequest = new SearchRequest("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(2);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    };

    public static final ElasticsearchTask SEARCH_WITH_ORDER = client -> {
        // 创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices("user");

        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        // 排序
        sourceBuilder.sort("age", SortOrder.ASC);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    };

    private static final ElasticsearchTask SEARCH_BY_CONDITION2 = client -> {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("age","30"));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name","zhangsan"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("sex","男"));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    };

    private static final ElasticsearchTask SEARCH_BY_RANGE = client -> {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
        rangeQueryBuilder.gte("30");
        rangeQueryBuilder.lt("40");
        searchSourceBuilder.query(rangeQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    };

    private static final ElasticsearchTask SEARCH_BY_FUZZY_CONDITION = client -> {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        FuzzyQueryBuilder fuzziness = QueryBuilders.fuzzyQuery("name", "wangwu").fuzziness(Fuzziness.ONE);
        searchSourceBuilder.query(fuzziness);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    };

    private static final ElasticsearchTask SEARCH_WITH_HIGHLIGHT = client -> {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name","zhangsan");
        searchSourceBuilder.query(termsQueryBuilder);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            System.out.println(highlightFields);
        }
        System.out.println("<<========");
    };

    private static final ElasticsearchTask SEARCH_WITH_MAX = client -> {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(AggregationBuilders.max("maxAage").field("age"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //4.打印响应结果
        SearchHits hits = response.getHits();
        Aggregations aggregations = response.getAggregations();
        System.out.println(response);
        System.out.println(aggregations);
        System.out.println("<<========");
    };

    private static final ElasticsearchTask SEARCH_WITH_GROUP = client -> {
        SearchRequest request = new SearchRequest().indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4.打印响应结果
        SearchHits hits = response.getHits();
        System.out.println(response);
        System.out.println("<<========");
    };

    public static void main(String[] args) {
        ConnectElasticsearch.connect(SEARCH_MATCHALL_QUERY);
        ConnectElasticsearch.connect(SEARCH_BY_CONDITION);
        ConnectElasticsearch.connect(SEARCH_BY_PAGING);
        ConnectElasticsearch.connect(SEARCH_WITH_ORDER);
        ConnectElasticsearch.connect(SEARCH_BY_CONDITION2);
        ConnectElasticsearch.connect(SEARCH_BY_RANGE);
        ConnectElasticsearch.connect(SEARCH_BY_FUZZY_CONDITION);
        ConnectElasticsearch.connect(SEARCH_WITH_HIGHLIGHT);
        ConnectElasticsearch.connect(SEARCH_WITH_MAX);
        ConnectElasticsearch.connect(SEARCH_WITH_GROUP);
    }
}
