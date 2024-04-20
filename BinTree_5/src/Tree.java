import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Tree implements Runnable {
    public class Node {
        long value;
        Node left;
        Node right;
        long shortestPathLength;
        long minPathWeight;

        Node(long value) {
            this.value = value;
            left = null;
            right = null;
            shortestPathLength = -1;
            minPathWeight = Long.MAX_VALUE;
        }
    }

    Node root;

    Tree() {
        this.root = null;
    }

    public void iterInsert(long value) {
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

    private void PostOrderTraversal(Node node) {
        if (node != null) {
            PostOrderTraversal(node.left);
            PostOrderTraversal(node.right);

            if (node.left == null && node.right == null) {
                node.shortestPathLength = 0;
                node.minPathWeight = node.value;
            } else {
                long leftPathLength = Long.MAX_VALUE;
                if (node.left != null) {
                    leftPathLength = node.left.shortestPathLength + 1;
                }
                long rightPathLength = Long.MAX_VALUE;
                if (node.right != null) {
                    rightPathLength = node.right.shortestPathLength + 1;
                }
                node.shortestPathLength = Math.min(leftPathLength, rightPathLength);

                if (leftPathLength < rightPathLength) {
                    node.minPathWeight = node.left.minPathWeight + node.value;
                } else if (rightPathLength < leftPathLength) {
                    node.minPathWeight = node.right.minPathWeight + node.value;
                } else {
                    node.minPathWeight = Math.min(node.left.minPathWeight, node.right.minPathWeight)
                            + node.value;
                }
            }
        }
    }

    private Node findCentralNode(Node node) {
        if (node == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        Node locRoot = null;
        long minPathLength = Long.MAX_VALUE;
        long minPathWeight = Long.MAX_VALUE;
        long minNodeValue = Long.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.left != null && currentNode.right != null) {
                long currentPathLength = currentNode.left.shortestPathLength +
                        currentNode.right.shortestPathLength + 2;
                long currentPathWeight = currentNode.left.minPathWeight +
                        currentNode.right.minPathWeight + currentNode.value;

                if ((currentPathLength < minPathLength ||
                        (currentPathLength == minPathLength && currentPathWeight < minPathWeight) ||
                        (currentPathLength == minPathLength && currentPathWeight == minPathWeight &&
                                currentNode.value < minNodeValue))) {
                    minPathLength = currentPathLength;
                    minPathWeight = currentPathWeight;
                    minNodeValue = currentNode.value;
                    locRoot = currentNode;
                }
            }

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        
        if (minPathLength % 2 == 0) {
            if (locRoot.left.shortestPathLength == locRoot.right.shortestPathLength) {
                return locRoot;
            }
            while ((minPathLength / 2) != locRoot.shortestPathLength) {
                if ((locRoot.left.shortestPathLength < locRoot.right.shortestPathLength)
                && locRoot.right != null) {
                    locRoot = locRoot.right;
                } else if ((locRoot.left.shortestPathLength > locRoot.right.shortestPathLength
                        && locRoot.left != null)) {
                    locRoot = locRoot.left;
                }
            }
        } else {
            return null;
        }
        return locRoot;
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

    public void DeleteIteratively(long value) {
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

    public void PreOrderTraversalToFile(Node node, BufferedWriter writer) throws IOException {
        if (node != null) {
            writer.write(String.valueOf(node.value));
            writer.newLine();
            PreOrderTraversalToFile(node.left, writer);
            PreOrderTraversalToFile(node.right, writer);
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            Tree tree = new Tree();
            BufferedReader br = new BufferedReader(new FileReader(new File
                    ("D:\\study\\4 сем\\алгосы\\BinTree_5\\src\\in.txt")));

            String line;
            while ((line = br.readLine()) != null) {
                long value = Long.parseLong(line.trim());
                tree.iterInsert(value);
            }
            br.close();
            tree.PostOrderTraversal(tree.root);

            Node centralNode = tree.findCentralNode(tree.root);
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
                    "D:\\study\\4 сем\\алгосы\\BinTree_5\\src\\out.txt")));
            if (centralNode != null) {
                tree.DeleteIteratively(centralNode.value);
            }
            tree.PreOrderTraversalToFile(tree.root, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}