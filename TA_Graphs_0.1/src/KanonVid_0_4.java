import java.io.*;
import java.util.StringTokenizer;

public class KanonVid_0_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] array = new int[n + 1];

        int[][] matrix = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == 1) {
                    array[j] = i;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            writer.write(array[i] + " ");
        }
        br.close();
        writer.close();
    }
}
