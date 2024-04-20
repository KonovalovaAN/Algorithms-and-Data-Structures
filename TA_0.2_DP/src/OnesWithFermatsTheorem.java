import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OnesWithFermatsTheorem {
    static final int p = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long result = calcModFactorial(n) % p;
        result = (result * modPow(calcModFactorial(k), p - 2)) % p;
        result = (result * modPow(calcModFactorial(n - k), p - 2)) % p;

        System.out.println(result);
    }

    public static long calcModFactorial(int number) {
        long fact = 1;
        for (int i = 1; i <= number; i++) {
            fact = (fact * i) % p;
        }
        return fact;
    }

    public static long modPow(long base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % p;
            }
            base = (base * base) % p;
            exponent /= 2;
        }
        return result;
    }
}
