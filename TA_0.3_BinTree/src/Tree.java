import java.io.*;

public class Tree implements Runnable {
    static class Node {
        int value;
        long left;
        long right;

        Node(int value) {
            this.value = value;
            this.left = Long.MIN_VALUE;
            this.right = Long.MAX_VALUE;
        }
    }
    Node[] nodes;

    public static void main(String[] args) {
        new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\study\\4 сем\\алгосы\\TA_0.3_BinTree\\src\\bst.in"));
            PrintWriter pw = new PrintWriter(new FileWriter("D:\\study\\4 сем\\алгосы\\TA_0.3_BinTree\\src\\bst.out"));
            String[] strings = br.readLine().split(" ");
            int size = Integer.parseInt(strings[0]);
            strings = br.readLine().split(" ");
            int root = Integer.parseInt(strings[0]);

            nodes = new Node[size];
            nodes[0] = new Node(root);

            constructTree(br, size, pw);

            br.close();
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void constructTree(BufferedReader br, int n, PrintWriter pw) throws IOException {
        int newValue;
        int parentIndex;
        char direction;
        String[] input;

        for (int i = 1; i < n; i++) {
            input = br.readLine().split(" ");
            newValue = Integer.parseInt(input[0]);
            parentIndex = Integer.parseInt(input[1]);
            direction = input[2].charAt(0);

            nodes[i] = new Node(newValue);

            if (!isValidPlacement(newValue, parentIndex, direction, i, pw)) {
                return;
            }
        }
        pw.println("YES");
    }

    private boolean isValidPlacement(int newValue, int parent, char direction, int index, PrintWriter pw) {
        if (direction == 'L') {
            nodes[index].left = nodes[parent - 1].left;
            nodes[index].right = nodes[parent - 1].value;
        } else {
            nodes[index].left = nodes[parent - 1].value;
            nodes[index].right = nodes[parent - 1].right;
        }
        if (newValue < nodes[index].left || newValue >= nodes[index].right) {
            pw.println("NO");
            return false;
        }
        return true;
    }
}