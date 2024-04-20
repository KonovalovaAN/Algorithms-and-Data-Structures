import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader bufferedReader;
    public static StringTokenizer stringTokenizer;
    public static String pattern;
    public static String input;
    public static StringBuilder current;
    public static int j = 0;


    public static void main(String[] args) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        pattern = stringTokenizer.nextToken();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        input = stringTokenizer.nextToken();
        current = new StringBuilder("");
        refactor();
        if (pattern.equals(current.toString())) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static void refactor() {
        for (int i = 0; i < input.length(); i++) {
            if (!(input.charAt(i) == '<')) {
                current.insert(j, input.charAt(i));
                j++;
            } else {
                if (Integer.compare(i + 1, input.length())  < 0) {
                    if (input.charAt(i + 1) == 'l') {
                        if (Integer.compare(j, 0) > 0) {
                            j--;
                        }
                        i = i + 5;
                    } else if (Character.compare(input.charAt(i + 1), 'r') == 0) {
                        if (Integer.compare(j, current.length()) < 0) {
                            j++;
                        }
                        i = i + 6;
                    } else if (Character.compare(input.charAt(i + 1),'d') == 0 ) {
                        if (j < current.length()) {
                            current.deleteCharAt(j);
                        }
                        i = i + 7;
                    } else if (Character.compare(input.charAt(i + 1),'b') == 0 ) {
                        if (Integer.compare(j, 0) > 0) {
                            j--;
                            current.deleteCharAt(j);
                        }
                        i = i + 7;
                    }
                }
            }
        }
    }
}
