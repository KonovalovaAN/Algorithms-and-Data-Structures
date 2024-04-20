import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
class Tree {
    Node root;
    class Node {
        long value;
        Node left;
        Node right;

        Node(long value) {
            this.value = value;
            left = right = null;
        }

    }
    Tree() {
        this.root = null;
    }

    public void insert(long key) throws IllegalArgumentException {
        root = insert(root, key);
    }

    private Node insert(Node node, long key) {
        if (node == null) {
            Node curr = new Node(key);
            node = curr;
            return node;
        } else if ( (key- node.value) < 0) {
            node.left = insert(node.left, key);
        } else if ((key- node.value) > 0) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    public static long sumTree(Node node) {
        if (node == null) {
            return 0;
        }
        return node.value + sumTree(node.left)+sumTree(node.right);
    }

}
public class Main2 {
    public static void main(String[] args) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("D:\\study\\4 сем\\алгосы\\TA0.0\\src\\input.txt"));
            String line = reader.readLine();
            Tree trree = new Tree();
            int n = 0;
            while (line != null) {
                n = Integer.parseInt(line);
                trree.insert(n);
                line = reader.readLine();
            }
            reader.close();
            long totalSum = trree.sumTree(trree.root);
            FileWriter wr = new FileWriter("D:\\study\\4 сем\\алгосы\\TA0.0\\src\\output.txt");
            wr.write(Long.toString(totalSum));
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}