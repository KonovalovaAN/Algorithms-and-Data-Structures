import java.io.*;
import java.util.StringTokenizer;

public class HuffmanCoding_2 {
    public static class MyQueue {
        int queueLength;
        long[] items;
        int front;
        int back;

        public MyQueue(int queueLength) {
            this.queueLength = queueLength;
            items = new long[queueLength];
            front = -1;
            back = -1;
        }

        public boolean isFull() {
            return back == queueLength - 1;
        }

        public boolean isEmpty() {
            return front == -1 && back == -1;
        }

        public void add(long item) {
            if (isFull()) {
                return;
            }
            if (isEmpty()) {
                front = back = 0;
            } else {
                back++;
            }
            items[back] = item;
        }

        public long poll() {
            if (isEmpty()) {
                return -1;
            }
            long item = items[front];
            if (front == back) {
                front = back = -1;
            } else {
                front++;
            }
            return item;
        }

        public long peek() {
            if (isEmpty()) {
                return -1;
            }
            return items[front];
        }

        public int size() {
            if (isEmpty()) {
                return 0;
            }
            return back - front + 1;
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("huffman.in"));
            int n = Integer.parseInt(br.readLine());
            MyQueue frequencies = readFrequencies(br, n);
            br.close();
            long length = calculateHuffmanLength(frequencies);
            writeLengthToFile(length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static MyQueue readFrequencies(BufferedReader br, int n) throws IOException {
        MyQueue frequencies = new MyQueue(n);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            frequencies.add(Long.parseLong(st.nextToken()));
        }
        return frequencies;
    }

    private static long calculateHuffmanLength(MyQueue frequencies) {
        MyQueue tempResults = new MyQueue(frequencies.size());
        long length = 0;
        tempResults.add(frequencies.poll());
        long sum;
        while (!frequencies.isEmpty() || tempResults.size() > 1) {
            sum = getNextFrequency(frequencies, tempResults) + getNextFrequency(frequencies, tempResults);
            tempResults.add(sum);
            length += sum;
        }
        return length;
    }

    private static long getNextFrequency(MyQueue frequencies, MyQueue tempResults) {
        if (!frequencies.isEmpty() && (tempResults.isEmpty() || frequencies.peek() < tempResults.peek())) {
            return frequencies.poll();
        } else {
            return tempResults.poll();
        }
    }

    private static void writeLengthToFile(long length) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("huffman.out"));
        writer.write(Long.toString(length));
        writer.close();
    }
}