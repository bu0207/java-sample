package com.bnt.sample.esspringboottest.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/10/29 下午6:23 bnt
 * @history
 */
@RestController
public class KnnSearchTest {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() throws IOException {
        List<Float> a = new ArrayList<>();
        SearchRequest searchRequest = SearchRequest.of(s -> s.index("test")
                .query(q -> q.bool(b -> b.must(m -> m.match(mm -> mm.field("a").query("1"))).must(
                        m -> m.knn(k -> k.field("b").numCandidates(20L).queryVector(a))
                )))
        );
        elasticsearchClient.search(searchRequest, Void.class);
    }
}
