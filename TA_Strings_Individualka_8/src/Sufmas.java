import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Sufmas {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();

        int n = str.length();
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = str.substring(i);
        }

        String[] helpArr = Arrays.copyOf(arr, n);

        Arrays.sort(arr, Comparator.naturalOrder());
        int counter;

        System.out.println(n);


        for (int i = 0; i < n; i++) {
            counter = 0;
            for (int j = 0; j < n; j++) {
                counter++;
                if (arr[i].equals(helpArr[j])) {
                    System.out.print(counter + " ");
                }

            }
        }


    }
}