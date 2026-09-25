/**
 * Custom Binary Search Tree (BST) for efficient Student record storage and traversal.
 * Nodes are ordered by Student ID.
 */
public class StudentBST {

    private static class Node {
        Student data;
        Node left;
        Node right;

        Node(Student data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int count;

    public StudentBST() {
        this.root = null;
        this.count = 0;
    }

    /**
     * Inserts a student into the BST.
     */
    public void insert(Student student) {
        if (student == null) return;
        root = insertRecursive(root, student);
    }

    private Node insertRecursive(Node current, Student student) {
        if (current == null) {
            count++;
            return new Node(student);
        }
        if (student.getStudentId() < current.data.getStudentId()) {
            current.left = insertRecursive(current.left, student);
        } else if (student.getStudentId() > current.data.getStudentId()) {
            current.right = insertRecursive(current.right, student);
        } else {
            // Duplicate ID, update existing student data
            current.data = student;
        }
        return current;
    }

    /**
     * Searches for a student by ID in O(log n) average time.
     */
    public Student search(int studentId) {
        Node resultNode = searchRecursive(root, studentId);
        return resultNode != null ? resultNode.data : null;
    }

    private Node searchRecursive(Node current, int studentId) {
        if (current == null || current.data.getStudentId() == studentId) {
            return current;
        }
        if (studentId < current.data.getStudentId()) {
            return searchRecursive(current.left, studentId);
        }
        return searchRecursive(current.right, studentId);
    }

    /**
     * Deletes a student node by ID.
     */
    public boolean delete(int studentId) {
        int initialCount = count;
        root = deleteRecursive(root, studentId);
        return count < initialCount;
    }

    private Node deleteRecursive(Node current, int studentId) {
        if (current == null) return null;

        if (studentId < current.data.getStudentId()) {
            current.left = deleteRecursive(current.left, studentId);
        } else if (studentId > current.data.getStudentId()) {
            current.right = deleteRecursive(current.right, studentId);
        } else {
            // Node to delete found
            count--;
            // Case 1: No child or 1 child
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            // Case 2: Node with 2 children - get inorder successor (smallest in right subtree)
            Node minNode = findMin(current.right);
            current.data = minNode.data;
            // Balance deleted node count since deleteRecursive will decrement again
            count++;
            current.right = deleteRecursive(current.right, minNode.data.getStudentId());
        }
        return current;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * In-Order traversal (sorted by Student ID).
     */
    public void inOrderTraversal() {
        System.out.println("=== BST In-Order Traversal (Sorted by ID) ===");
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.println(" -> " + node.data);
            inOrderRecursive(node.right);
        }
    }

    /**
     * Pre-Order traversal.
     */
    public void preOrderTraversal() {
        System.out.println("=== BST Pre-Order Traversal ===");
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        preOrderRecursive(root);
    }

    private void preOrderRecursive(Node node) {
        if (node != null) {
            System.out.println(" -> " + node.data);
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    /**
     * Post-Order traversal.
     */
    public void postOrderTraversal() {
        System.out.println("=== BST Post-Order Traversal ===");
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        postOrderRecursive(root);
    }

    private void postOrderRecursive(Node node) {
        if (node != null) {
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.println(" -> " + node.data);
        }
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
