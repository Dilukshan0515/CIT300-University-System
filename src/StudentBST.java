public class StudentBST {

    private class Node {

        Student student;
        Node left;
        Node right;

        public Node(Student student) {
            this.student = student;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public StudentBST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // Insert student into BST
    public boolean insertStudent(Student student) {

        if (searchStudent(student.getStudentId()) != null) {
            return false;
        }

        root = insertRecursive(root, student);

        return true;
    }

    private Node insertRecursive(Node current, Student student) {

        if (current == null) {
            return new Node(student);
        }

        int comparison =
                student.getStudentId()
                        .compareToIgnoreCase(
                                current.student.getStudentId()
                        );

        if (comparison < 0) {

            current.left =
                    insertRecursive(
                            current.left,
                            student
                    );

        } else {

            current.right =
                    insertRecursive(
                            current.right,
                            student
                    );
        }

        return current;
    }

    // Search student using Student ID
    public Student searchStudent(String studentId) {

        Node current = root;

        while (current != null) {

            int comparison =
                    studentId.compareToIgnoreCase(
                            current.student.getStudentId()
                    );

            if (comparison == 0) {

                return current.student;

            } else if (comparison < 0) {

                current = current.left;

            } else {

                current = current.right;
            }
        }

        return null;
    }

    // Delete student from BST
    public boolean deleteStudent(String studentId) {

        if (searchStudent(studentId) == null) {
            return false;
        }

        root = deleteRecursive(
                root,
                studentId
        );

        return true;
    }

    private Node deleteRecursive(
            Node current,
            String studentId) {

        if (current == null) {
            return null;
        }

        int comparison =
                studentId.compareToIgnoreCase(
                        current.student.getStudentId()
                );

        if (comparison < 0) {

            current.left =
                    deleteRecursive(
                            current.left,
                            studentId
                    );

        } else if (comparison > 0) {

            current.right =
                    deleteRecursive(
                            current.right,
                            studentId
                    );

        } else {

            // No left child
            if (current.left == null) {
                return current.right;
            }

            // No right child
            if (current.right == null) {
                return current.left;
            }

            // Node has two children
            Node smallestNode =
                    findSmallestNode(
                            current.right
                    );

            current.student =
                    smallestNode.student;

            current.right =
                    deleteRecursive(
                            current.right,
                            smallestNode.student.getStudentId()
                    );
        }

        return current;
    }

    private Node findSmallestNode(Node node) {

        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // Display students in sorted Student ID order
    public void displayStudents() {

        if (root == null) {

            System.out.println(
                    "No students available in BST."
            );

            return;
        }

        System.out.println(
                "\n--- Students using BST ---"
        );

        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node current) {

        if (current != null) {

            inOrderTraversal(current.left);

            System.out.println(current.student);

            inOrderTraversal(current.right);
        }
    }
}