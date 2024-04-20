import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class HashTable {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\study\\4 сем\\алгосы\\TA_SD_0.6\\src\\input.txt"));
        FileWriter writer = new FileWriter("D:\\study\\4 сем\\алгосы\\TA_SD_0.6\\src\\output.txt");

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] table = createTable(m);
        fillTable(br, table, m, c, n);
        writeTable(writer, table);

        br.close();
        writer.close();
    }

    public static int[] createTable(int m) {
        int[] table = new int[m];
        for (int i = 0; i < m; i++) {
            table[i] = -1;
        }
        return table;
    }

    public static void fillTable(BufferedReader br, int[] table, int m, int c, int n) throws IOException {
        int key;
        for (int j = 0; j < n; j++) {
            key = Integer.parseInt(br.readLine());
            procFunc(table, key, m, c);
        }
    }

    public static void procFunc(int[] table, int key, int m, int c) {
        for (int i = 0; i < table.length; i++) {
            int index = hashFunc(key, m, c, i);
            if (table[index] == -1 || table[index] == key) {
                table[index] = key;
                return;
            }
        }
    }

    public static int hashFunc(int x, int m, int c, int i) {
        return ((x % m) + (c * i)) % m;
    }

    public static void writeTable(FileWriter writer, int[] table) throws IOException {
        for (int i = 0; i < table.length; i++) {
            writer.write(table[i] + " ");
        }
    }
}
