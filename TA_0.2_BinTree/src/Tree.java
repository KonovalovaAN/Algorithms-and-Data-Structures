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
            writer.write(String.valueOf(node.value));
            writer.newLine();
            PreOrderTraversalToFile(node.left, writer);
            PreOrderTraversalToFile(node.right, writer);
        }
    }


    private void ReplaceChild(Node parent, Node old, Node newChild) {
        if (parent == null) {
            this.root = newChild;
        } else if (parent.left == old) {
            parent.left = newChild;
        } else if (parent.right == old) {
            parent.right = newChild;
        }
    }

    public void DeleteIteratively(int value) {
        Node parent = null;
        Node node = this.root;

        while (true) {
            if (node == null) {
                return;
            }

            if (value < node.value) {
                parent = node;
                node = node.left;
            } else if (value > node.value) {
                parent = node;
                node = node.right;
            } else {
                break;
            }
        }

        Node result = null;

        if (node.left == null) {
            result = node.right;
        } else if (node.right == null) {
            result = node.left;
        } else {
                Node minNodeParent = node;
                Node minNode = node.right;

                while (minNode.left != null) {
                    minNodeParent = minNode;
                    minNode = minNode.left;
                }

                result = node;
                node.value = minNode.value;
                ReplaceChild(minNodeParent, minNode, minNode.right);
            }
        ReplaceChild(parent, node, result);
    }


    public static void main(String[] args) throws IOException {
        new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            Tree tree = new Tree();
            BufferedReader br = new BufferedReader(new FileReader
                    (new File("input.txt")));

            int elForDel = Integer.parseInt(br.readLine());
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                int value = Integer.parseInt(line.trim());
                tree.iterInsert(value);
            }
            br.close();

            tree.DeleteIteratively(elForDel);

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File
                    ("output.txt")));

            tree.PreOrderTraversalToFile(tree.root, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
