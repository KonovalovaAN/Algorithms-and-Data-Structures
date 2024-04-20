import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS {

    static int[] array;
    static int[][] matrix;
    static boolean[] visited;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        array = new int[n + 1];
        matrix = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        queue = new LinkedList<>();

        readGraph(br, n);
        bfs(n);
        writeResult(writer, n);

        writer.close();
        br.close();
    }

    static void readGraph(BufferedReader br, int n) throws IOException {
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void bfs(int n) {
        int counter = 1;
        int u, w;
        for (int i = 1; i <= n; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                queue.add(i);
                array[i] = counter;
                for (; !queue.isEmpty();) {
                    u = queue.poll();
                    array[u] = counter++;
                    w = 1;
                    while (w <= n) {
                        if (visited[w] == false && matrix[u][w] == 1) {
                            queue.add(w);
                            visited[w] = true;
                        }
                        w++;
                    }

                }
            }
        }
    }

    static void writeResult(BufferedWriter writer, int n) throws IOException {
        for (int i = 1; i <= n; i++) {
            writer.write(array[i] + " ");
        }
    }
}
