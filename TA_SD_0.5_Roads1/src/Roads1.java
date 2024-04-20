import java.io.*;

public class Roads1 {
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
        Roads1 roads = new Roads1();
        roads.solve();
    }

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        String line = br.readLine();
        int n = Integer.parseInt(line.split(" ")[0]);
        int q = Integer.parseInt(line.split(" ")[1]);
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        processQueries(br, writer, q, dsu);
        br.close();
        writer.close();
    }

    public void processQueries(BufferedReader br, BufferedWriter writer, int q, DisjointSetUnion dsu) throws IOException {
        String line;
        int u, v;
        for (int i = 0; i < q; i++) {
            line = br.readLine();
            u = Integer.parseInt(line.split(" ")[0]);
            v = Integer.parseInt(line.split(" ")[1]);
            dsu.union(u, v);
            writer.write(Integer.toString(dsu.getNumberOfComponents()) + '\n');
        }
        writer.flush();
    }
}
