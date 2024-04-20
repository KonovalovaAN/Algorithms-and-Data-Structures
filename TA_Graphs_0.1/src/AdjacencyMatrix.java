import java.io.*;
import java.util.StringTokenizer;

public class AdjacencyMatrix {
    private static int[][] adjacencyMatrix;
    private static int n;
    private static int m;
    private static int node1;
    private static int node2;

    public static void main(String[] args) throws IOException {
        readInput();
        writeOutput();
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjacencyMatrix = new int[n + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            adjacencyMatrix[node1][node2] = adjacencyMatrix[node2][node1] = 1;
        }

        br.close();
    }

    private static void writeOutput() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.txt")));
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                writer.write(adjacencyMatrix[i][j] + " ");
            }
            writer.write('\n');
        }

        writer.close();
    }
}
