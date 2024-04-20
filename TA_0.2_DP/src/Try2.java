import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Try2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] row = calculateBinomialCoefficients(n, k);

        System.out.println(row[k]);
    }

    public static long[] calculateBinomialCoefficients(int n, int k) {
        long[] row = new long[k + 1];
        row[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = k; j > 0; j--) {
                row[j] = (row[j] + row[j - 1]) % 1000000007;
            }
        }

        return row;
    }
}