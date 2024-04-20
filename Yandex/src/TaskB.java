import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class TaskB {
    public static BufferedReader reader;
    public static BufferedWriter writer;
    public static StringTokenizer tokenizer;
    public static int numElements;
    public static int windowSize;
    public static long result;
    public static int[] elements;
    public static int[] takenCount;


    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        tokenizer = new StringTokenizer(reader.readLine());
        numElements = Integer.parseInt(tokenizer.nextToken());
        windowSize = Integer.parseInt(tokenizer.nextToken());

        elements = readElements();
        result = calculateResult();

        writer.write(Long.toString(result));
        writer.newLine();

        takenCount = calculateTakenCount();
        writeTakenCount();

        writer.flush();
    }


    static int[] readElements() throws IOException {
        int[] elements = new int[numElements];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numElements; ++i) {
            elements[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return elements;
    }

    static long calculateResult() {
        long result = 0;
        Deque<Pair> deque = new ArrayDeque<>();
        int value;
        for (int i = 0; i < numElements; ++i) {
            value = elements[i];
            while (!deque.isEmpty() && deque.peekFirst().second <= i - windowSize) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && deque.peekLast().first >= value) {
                deque.pollLast();
            }
            deque.offerLast(new Pair(value, i));
            result = result + deque.peekFirst().first;
        }
        return result;
    }

    static int[] calculateTakenCount() {
        int[] takenCount = new int[numElements];
        Deque<Pair> deque = new ArrayDeque<>();
        int value;
        for (int i = 0; i < numElements; ++i) {
            value = elements[i];
            while (!deque.isEmpty() && deque.peekFirst().second <= i - windowSize) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && deque.peekLast().first >= value) {
                deque.pollLast();
            }
            deque.offerLast(new Pair(value, i));
            takenCount[deque.peekFirst().second]++;
        }
        return takenCount;
    }

    static void writeTakenCount() throws IOException {
        for (int count : takenCount) {
            writer.write(count + " ");
        }
        writer.newLine();
    }

    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}

class RunSolution {
    public static void main(String[] args) throws IOException {
        TaskB.main(args);
    }
}
