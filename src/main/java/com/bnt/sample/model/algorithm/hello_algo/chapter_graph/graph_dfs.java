package com.bnt.sample.model.algorithm.hello_algo.chapter_graph;

import com.bnt.sample.model.algorithm.hello_algo.utils.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 图深度优先遍历 */
public class graph_dfs {

    /* 深度优先遍历辅助函数 */
    static void dfs(GraphAdjList graph, Set<Vertex> visited, List<Vertex> res, Vertex vet) {
        // 记录访问顶点
        res.add(vet);
        // 标记该顶点已被访问
        visited.add(vet);
        List<Vertex> vertices = graph.adjList.get(vet);
        for (Vertex vertex : vertices) {
            if (visited.contains(vertex)) {
                continue;
            }
            // 递归访问邻接顶点
            dfs(graph, visited, res, vertex);
        }
    }

    /* 深度优先遍历 */
    // 使用邻接表来表示图，以便获取指定顶点的所有邻接顶点
    static List<Vertex> graphDFS(GraphAdjList graph, Vertex startVet) {
        // 顶点遍历序列
        List<Vertex> res = new ArrayList<>();
        // 哈希表，用于记录已被访问过的顶点
        Set<Vertex> visited = new HashSet<>();
        dfs(graph, visited, res, startVet);
        return res;
    }

    public static void main(String[] args) {
        /* 初始化无向图 */
        Vertex[] v = Vertex.valsToVets(new int[] { 0, 1, 2, 3, 4, 5, 6 });
        Vertex[][] edges = { { v[0], v[1] }, { v[0], v[3] }, { v[1], v[2] },
                { v[2], v[5] }, { v[4], v[5] }, { v[5], v[6] } };
        GraphAdjList graph = new GraphAdjList(edges);
        System.out.println("\n初始化后，图为");
        graph.print();

        /* 深度优先遍历 */
        List<Vertex> res = graphDFS(graph, v[0]);
        System.out.println("\n深度优先遍历（DFS）顶点序列为");
        System.out.println(Vertex.vetsToVals(res));
    }
}
