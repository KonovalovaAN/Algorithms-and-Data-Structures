import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hackers {

    public static void main(String[] args) throws IOException {
        Hackers hackers = new Hackers();
        hackers.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[n];

        int[] numberOfTerminals = new int[n];
        int[] component = new int[n];
        LinkedList<Integer>[] graph = new LinkedList[n];
        LinkedList<Integer>[] transposeGraph = new LinkedList[n];
        ArrayList<Integer> ordered = new ArrayList<>();

        initializeGraphs(n, graph, transposeGraph);
        readTerminalNumbers(br, n, numberOfTerminals);
        readGraphEdges(br, graph, transposeGraph);

        findStronglyConnectedComponents(n, visited, graph, transposeGraph, ordered, component);
        int max = calculateMaxComponentSize(component);
        LinkedList<Integer>[] units = initializeComponentUnits(max, component);
        ArrayList<Integer> helpArray = calculateHelpArray(n, numberOfTerminals, units);
        ArrayList<Integer> resultArray = calculateResultArray(visited, graph, ordered, helpArray);
        writeResults(writer, resultArray);
        writer.close();
        br.close();
    }

    private void initializeGraphs(int n, LinkedList<Integer>[] graph, LinkedList<Integer>[] transposeGraph) {
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
            transposeGraph[i] = new LinkedList<>();
        }
    }

    private void readTerminalNumbers(BufferedReader br, int n, int[] numberOfTerminals) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            numberOfTerminals[i] = Integer.parseInt(st.nextToken());
        }
    }

    private void readGraphEdges(BufferedReader br, LinkedList<Integer>[] graph, LinkedList<Integer>[] transposeGraph) throws IOException {
        int u, v;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            if (u == 0 && v == 0) {
                break;
            }
            graph[u - 1].add(v - 1);
            transposeGraph[v - 1].add(u - 1);
        }
    }

    private void findStronglyConnectedComponents(int n, boolean[] visited, LinkedList<Integer>[] graph, LinkedList<Integer>[] transposeGraph, ArrayList<Integer> ordered, int[] component) {
        Arrays.fill(visited, false);
        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                firstDFS(i, visited, graph, ordered);
            }
        }

        ArrayList<Integer> reverseOrdered = IntStream.range(0, ordered.size())
                .map(i -> ordered.size() - i - 1)
                .mapToObj(ordered::get)
                .collect(Collectors.toCollection(ArrayList::new));

        Arrays.fill(visited, false);

        int components = 0;
        for (int j : reverseOrdered) {
            if (!visited[j]) {
                secondDFS(j, visited, transposeGraph, components, component);
                ++components;
            }
        }
    }

    private int calculateMaxComponentSize(int[] component) {
        int max = -1;
        for (int rest : component) {
            if (Integer.compare(max,rest) < 0) {
                max = rest;
            }
        }
        return max;
    }

    private LinkedList<Integer>[] initializeComponentUnits(int max, int[] component) {
        LinkedList<Integer>[] units = new LinkedList[max + 1];
        for (int i = 0; i < max + 1; i++) {
            units[i] = new LinkedList<>();
        }
        for (int i = 0; i < component.length; i++) {
            units[component[i]].add(i);
        }
        return units;
    }

    private ArrayList<Integer> calculateHelpArray(int n, int[] numberOfTerminals, LinkedList<Integer>[] units) {
        ArrayList<Integer> helpArray = new ArrayList<>();
        for (LinkedList<Integer> unit : units) {
            int m = numberOfTerminals[unit.get(0)];
            int optimus = unit.get(0);
            for (int j : unit) {
                if (m > numberOfTerminals[j]) {
                    optimus = j;
                    m = numberOfTerminals[optimus];
                }
            }
            helpArray.add(optimus);
            for (int j = 0; j < n; j++) {
                if (numberOfTerminals[j] == m) {
                    helpArray.add(j);
                    break;
                }
            }
        }
        return helpArray;
    }

    private ArrayList<Integer> calculateResultArray(boolean[] visited, LinkedList<Integer>[] graph, ArrayList<Integer> ordered, ArrayList<Integer> helpArray) {
        Arrays.fill(visited, false);
        ArrayList<Integer> resultArray = new ArrayList<>();
        for (int g : helpArray) {
            if (visited[g] == false) {
                resultArray.add(g + 1);
                firstDFS(g, visited, graph, ordered);
            }
        }
        return resultArray;
    }

    private void writeResults(BufferedWriter writer, ArrayList<Integer> resultArray) throws IOException {
        writer.write(resultArray.size() + "\n");
        for (int temp : resultArray) {
            writer.write(temp + " ");
        }
    }

    private void secondDFS(int v, boolean[] visited, LinkedList<Integer>[] transposeGraph, int components, int[] component) {
        visited[v] = true;
        component[v] = components;
        for (int u : transposeGraph[v]) {
            if (visited[u] == false) {
                secondDFS(u, visited, transposeGraph, components, component);
            }
        }
    }

    private void firstDFS(int v, boolean[] visited, LinkedList<Integer>[] graph, ArrayList<Integer> ordered) {
        visited[v] = true;
        for (int u : graph[v]) {
            if (visited[u] == false) {
                firstDFS(u, visited, graph, ordered);
            }
        }
        ordered.add(v);
    }
}
