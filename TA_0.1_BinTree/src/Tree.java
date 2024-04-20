import java.io.*;

public class Tree implements Runnable{
    public class Node {
        int value;
        Node left;
        Node right;
        Node (int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
    Node root;

    Tree() {
        this.root = null;
    }

    public void iterInsert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (value < current.value) {
                current = current.left;
            } else if (value > current.value) {
                current = current.right;
            } else {
                return;
            }
        }

        if (value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public void PreOrderTraversalToFile(Node node, BufferedWriter writer) throws IOException {
        if (node != null) {
            writer.write(String.valueOf(node.value) + '\n');
            PreOrderTraversalToFile(node.left, writer);
            PreOrderTraversalToFile(node.right, writer);
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        Tree tree = new Tree();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File("input.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int value = Integer.parseInt(line.trim());
            tree.iterInsert(value);
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File("output.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            tree.PreOrderTraversalToFile(tree.root, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
