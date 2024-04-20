import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TheNumberOfDifSubstrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        int result = calculateNumberOfDifferentSubstrings(str);
        System.out.println(result);
    }

    private static int calculateNumberOfDifferentSubstrings(String str) {
        int sum = 0;
        int[] array = new int[str.length() + 1];
        while (!str.isEmpty()) {
            sum += countDistinctSubstrings(str, array);
            str = str.substring(1);
        }
        return sum;
    }

    private static int countDistinctSubstrings(String str, int[] array) {
        int length = 0;
        int max = 0;
        for (int i = 1; i < str.length(); i++) {
            while (true) {
                if (str.charAt(i) == str.charAt(length)) {
                    length++;
                    break;
                }
                if (length == 0) {
                    break;
                }
                length = array[length - 1];
            }
            max = Math.max(max, length);
            array[i] = length;
        }
        return str.length() - max;
    }
}
