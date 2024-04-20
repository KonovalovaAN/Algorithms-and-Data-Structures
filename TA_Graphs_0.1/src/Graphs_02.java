import java.io.*;
import java.util.*;

public class Graphs_02 {
    public static BufferedReader br;
    public static int n;
    public static Map<Integer, Integer> parentMap;
    public static Set<Integer> children;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(new File("input.txt")));
        n = Integer.parseInt(br.readLine());
        parentMap = new HashMap<>();
        children = new HashSet<>();

        readInput();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.txt")));
        writeOutput(writer);
        br.close();
    }

    public static void readInput() throws IOException {
        int parent, child;
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            parent = Integer.parseInt(st.nextToken());
            child = Integer.parseInt(st.nextToken());
            children.add(child);
            parentMap.put(child, parent);
        }
    }

    public static void writeOutput(BufferedWriter writer) throws IOException {
        for (int i = 1; i <= n; i++) {
            if (!children.contains(i)) {
                writer.write("0 ");
            } else {
                writer.write(parentMap.get(i) + " ");
            }
        }
        writer.close();
    }
}
