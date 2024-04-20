import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.txt")));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st =new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i - 1] > arr[i]) {
                writer.write("-1");
                writer.close();
                return;
            }
        }
        int lastEl = arr[n - 1];
        long count = 0;
        for (int i = n - 2; i >= 0; i--) {
            arr[i] += count;
            while (arr[i] != lastEl) {
                count++;
                arr[i]++;
            }
        }
        writer.write(Long.toString(count));
        writer.close();
        br.close();
    }
}
