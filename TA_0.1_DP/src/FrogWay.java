import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FrogWay {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt((st.nextToken()));
        int array[] = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[] scores = new int[n];

        if (n == 1) {
            System.out.println(array[0] + "\n1");
            return;
        } else if (n == 2) {
            System.out.println("-1");
            return;
        } else {
            scores[0] = array[0];
            scores[1] = -1;
            scores[2] = scores[0] + array[2];
            for (int i = 3; i < n; i++) {
                scores[i] = Math.max(scores[i - 2], scores[i - 3]) + array[i];
            }

            if (scores[n - 1] <= 0) {
                System.out.println("-1");
                return;
            }

            List<Integer> answer = new ArrayList<>();
            int current = n - 1;
            while (current >= 0) {
                answer.add(current + 1);
                if (current - 2 >= 0 && scores[current] == scores[current - 2] + array[current]) {
                    current -= 2;
                } else {
                    current -= 3;
                }
            }

            System.out.println(scores[n - 1]);
            for (int i = answer.size() - 1; i >= 0; i--) {
                System.out.print(answer.get(i) + " ");
            }
        }
    }
}
