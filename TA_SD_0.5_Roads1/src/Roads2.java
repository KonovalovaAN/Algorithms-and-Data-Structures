import java.io.*;

public class Roads2 {
    static class DisjointSetUnion {
        int[] parent;
        int components;

        public DisjointSetUnion(int n) {
            parent = new int[n + 1];
            components = n;
            for (int i = 1; i <= n; i++) {
                parent[i] = -1;
            }
        }

        public int findSet(int x) {
            if (parent[x] < 0) {
                return x;
            }
            return parent[x] = findSet(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = findSet(x);
            int rootY = findSet(y);
            if (rootX != rootY) {
                if (parent[rootX] > parent[rootY]) {
                    parent[rootY] += parent[rootX];
                    parent[rootX] = rootY;
                } else {
                    parent[rootX] += parent[rootY];
                    parent[rootY] = rootX;
                    if (parent[rootX] == parent[rootY]) {
                        parent[rootY]--;
                    }
                }
                components--;
            }
        }

        public int getNumberOfComponents() {
            return components;
        }
    }

    public static void main(String[] args) throws IOException {
        Roads2 roads = new Roads2();
        roads.solve();
    }

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int[][] roads;
        int[] earthquakes;
        int n, m, q;
        boolean flag;

        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        q = Integer.parseInt(line[2]);
        roads = readRoads(br, m);
        int[] trashArray = new int[m + 1];
        earthquakes = readEarthquakes(br, q, trashArray);

        DisjointSetUnion dsu = new DisjointSetUnion(n);
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= m; i++) {
            if (trashArray[i] == 0) {
                dsu.union(roads[i][0], roads[i][1]);
            }
        }

        for (int i = q; i >= 1; i--) {
            if (dsu.components == 1) {
                result.append('1');
                continue;
            } else {
                result.append('0');
            }
            dsu.union(roads[earthquakes[i]][0], roads[earthquakes[i]][1]);
        }

        writer.write(result.reverse().toString());

        br.close();
        writer.close();
    }

    private int[][] readRoads(BufferedReader br, int m) throws IOException {
        int[][] roads = new int[m + 1][2];
        for (int i = 1; i <= m; i++) {
            String[] line = br.readLine().split(" ");
            roads[i][0] = Integer.parseInt(line[0]);
            roads[i][1] = Integer.parseInt(line[1]);
        }
        return roads;
    }

    private int[] readEarthquakes(BufferedReader br, int q, int[] trashArray) throws IOException {
        int[] earthquakes = new int[q + 1];
        for (int i = 1; i <= q; i++) {
            earthquakes[i] = Integer.parseInt(br.readLine());
            trashArray[earthquakes[i]] = 1;
        }
        return earthquakes;
    }
}
