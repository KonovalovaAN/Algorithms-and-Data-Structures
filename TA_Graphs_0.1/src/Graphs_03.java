import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Graphs_03 {
    private static int n;
    private static int m;
    private static LinkedList<Integer>[] adjacencyList;

    public static void main(String[] args) throws IOException {
        readGraphFromFile("input.txt");
        printAdjacencyList();
    }

    private static void readGraphFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjacencyList = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        br.close();
    }

    private static void addEdge(int u, int v) {
        adjacencyList[u].add(v);
        adjacencyList[v].add(u);
    }

    private static void printAdjacencyList() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.txt")));
        int j;
        for (int i = 1; i <= n; i++) {
            writer.write((adjacencyList[i].size()) + " ");
            Iterator<Integer> iterator = adjacencyList[i].iterator();
            while (iterator.hasNext()) {
                j = iterator.next();
                writer.write(j + " ");
            }
            writer.write('\n');
        }
        writer.close();
    }
}
