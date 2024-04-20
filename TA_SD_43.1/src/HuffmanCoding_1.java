import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    long frequency;
    Node left;
    Node right;
    Node parent;
    boolean visited;

    @Override
    public int compareTo(Node other) {
        return Long.compare(this.frequency, other.frequency);
    }

    Node(long frequency, Node left, Node right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.parent = null;
        this.visited = false;
    }
}

public class HuffmanCoding_1 {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("huffman.in"));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            Node root = buildHuffmanTree(st, n);
            long encodedLength = calculateEncodedLength(root);
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("huffman.out"));
            writer.write(Long.toString(encodedLength));
            writer.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Node buildHuffmanTree(StringTokenizer st, int n) {
        PriorityQueue<Node> hTree = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            hTree.add(new Node(Integer.parseInt(st.nextToken()), null, null));
        }

        while (hTree.size() != 1) {
            Node v = hTree.poll();
            Node w = hTree.poll();
            long sum = v.frequency + w.frequency;
            Node r = new Node(sum, v, w);
            v.parent = r;
            w.parent = r;
            hTree.add(r);
        }

        return hTree.poll();
    }
    private static long calculateEncodedLength(Node root) {
        long length = 0;
        long depth = 0;
        Node current = root;

        while (current != null) {
            if (current.left != null && !current.left.visited) {
                depth++;
                current.left.visited = true;
                current = current.left;
            } else if (current.right != null && !current.right.visited) {
                depth++;
                current.right.visited = true;
                current = current.right;
            } else if (current.left == null && current.right == null) {
                length += depth * current.frequency;
                depth--;
                current = current.parent;
            } else {
                current = current.parent;
                depth--;
            }
        }

        return length;
    }
}