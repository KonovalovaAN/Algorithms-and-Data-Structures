import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OnesPart1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] answer = new long[n + 1];
        answer[0] = 1;
        for (int i = 1; i <= n; i++) {
            answer[i] = cnk(i - 1, k - 1) + cnk(i - 1, k);
        }
        System.out.println(answer[n] % ((int)Math.pow(10, 9) + 7));
    }

    public static long factorial(int x) {
        if (x == 0) {
            return 1;
        }
        long[] answer = new long[x + 1];
        answer[0] = 1;
        for (int i = 1; i <= x; i++) {
            answer[i] = answer[i - 1] * i;
        }
        return answer[x];
    }

    public static long cnk (int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            return 0;
        }
        return factorial(n)/(factorial(k)*factorial(n - k));
    }
}