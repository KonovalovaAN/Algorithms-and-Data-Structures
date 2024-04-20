import java.io.*;
import java.util.StringTokenizer;

public class DFS {
    public static int[][] matrix;
    public static boolean[] check;
    public static int n;
    public static int id;
    public static int[] labels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        readGraphFromFile(br);
        dfsTraversal(writer);

        br.close();
        writer.close();
    }

    static void readGraphFromFile(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        matrix = new int[n + 1][n + 1];
        check = new boolean[n + 1];
        labels = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            check[i] = false;
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dfsTraversal(BufferedWriter writer) throws IOException {

        dfs(0);

        for (int i = 1; i <= n; i++) {
            if (check[i] == false) {
                dfs(i);
            }
        }
        for (int i = 1; i <= n; i++) {
            writer.write(labels[i] + " ");
        }
    }

    static void dfs(int v) {
        labels[v] = id++;
        check[v] = true;

        for (int i = 1; i <= n ; i++) {
            if ((matrix[v][i] == 1) && (check[i] == false)) {
                dfs(i);
            }
        }
    }
}
