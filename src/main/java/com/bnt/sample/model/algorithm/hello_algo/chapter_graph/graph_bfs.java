package com.bnt.sample.model.algorithm.hello_algo.chapter_graph;

import com.bnt.sample.model.algorithm.hello_algo.utils.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

/**
 * 图广度优先遍历
 */
public class graph_bfs {
    /* 广度优先遍历 */
    // 使用邻接表来表示图，以便获取指定顶点的所有邻接顶点
    static List<Vertex> graphBFS(GraphAdjList graph, Vertex startVet) {
        // 顶点遍历序列
        List<Vertex> res = new ArrayList<>();
        // 哈希表，用于记录已被访问过的顶点
        Set<Vertex> visited = new HashSet<>();
        visited.add(startVet);
        // 队列用于实现 BFS
        Queue<Vertex> que = new LinkedList<>();
        // 以顶点 vet 为起点，循环直至访问完所有顶点
        que.offer(startVet);
        while (!que.isEmpty()) {
            // 队首顶点出队
            Vertex poll = que.poll();
            // 记录访问顶点
            res.add(poll);
            List<Vertex> vertices = graph.adjList.get(poll);
            for (Vertex vertex : vertices) {
                if (visited.contains(vertex)) {
                    // 跳过已被访问的顶点
                    continue;
                }
                // 只入队未访问的顶点
                que.offer(vertex);
                // 标记该顶点已被访问
                visited.add(vertex);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        /* 初始化无向图 */
        Vertex[] v = Vertex.valsToVets(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        Vertex[][] edges = { { v[0], v[1] }, { v[0], v[3] }, { v[1], v[2] }, { v[1], v[4] },
                { v[2], v[5] }, { v[3], v[4] }, { v[3], v[6] }, { v[4], v[5] },
                { v[4], v[7] }, { v[5], v[8] }, { v[6], v[7] }, { v[7], v[8] } };
        GraphAdjList graph = new GraphAdjList(edges);
        System.out.println("\n初始化后，图为");
        graph.print();

        /* 广度优先遍历 */
        List<Vertex> res = graphBFS(graph, v[0]);
        System.out.println("\n广度优先遍历（BFS）顶点序列为");
        System.out.println(Vertex.vetsToVals(res));
    }
}
