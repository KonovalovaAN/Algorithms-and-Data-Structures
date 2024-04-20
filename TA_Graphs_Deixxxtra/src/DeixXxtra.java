import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DeixXxtra {
    private static LinkedList<int[]>[] adjacencyList;
    public static BufferedReader br;
    public static BufferedWriter writer;
    public static StringTokenizer st;

    public static void main(String[] args) {
        try {
            br = new BufferedReader(new FileReader(new File("input.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            writer = new BufferedWriter(new FileWriter(new File("output.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adjacencyList = new LinkedList[n + 1];

        for (int i = 1; i <= n; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

        int temp1;
        int temp2;
        int temp3;

        for (int i = 1; i <= m; i++) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            temp1 = Integer.parseInt(st.nextToken());
            temp2 = Integer.parseInt(st.nextToken());
            temp3 = Integer.parseInt(st.nextToken());

            addEdge(temp1, temp2, temp3);
        }

        deixxxtra(n, m);

        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deixxxtra(int n, int m) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Math.toIntExact(a[1] - b[1]));
        long[] distance = new long[n + 1];


        Arrays.fill(distance, Long.MAX_VALUE);
        distance[1] = 0;
        queue.add(new int[]{1,0});
        boolean[] visited = new boolean[n + 1];

        int[] node;
        int u;
        int v;
        int weight;

        while (!queue.isEmpty()) {
            node = queue.poll();
            u = node[0];
            if (visited[u]) {
                continue;
            }
            visited[u] = true;

            for (int[] el: adjacencyList[u]) {
                v = el[0];
                weight = el[1];
                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    queue.add(new int[]{v, (int)distance[v]});
                }

            }
        }

        try {
            writer.write(String.valueOf(distance[n]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addEdge(int u, int v, int w) {
        adjacencyList[u].add(new int[]{v, w});
        adjacencyList[v].add(new int[]{u, w});
    }
}