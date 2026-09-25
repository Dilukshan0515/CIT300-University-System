/**
 * Custom Linked List implementation for managing Student records.
 */
public class StudentLinkedList {

    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public StudentLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Appends a student to the end of the linked list.
     */
    public void add(Student student) {
        if (student == null) return;
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Removes a student by ID. Returns true if removed successfully.
     */
    public boolean remove(int studentId) {
        if (head == null) return false;

        if (head.data.getStudentId() == studentId) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data.getStudentId() != studentId) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }

        return false;
    }

    /**
     * Searches for a student by ID. Returns Student object or null if not found.
     */
    public Student search(int studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId() == studentId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Returns the size of the linked list.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the linked list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Displays all student records in the linked list.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Linked List is empty.");
            return;
        }
        Node current = head;
        System.out.println("=== Student Linked List (" + size + " entries) ===");
        while (current != null) {
            System.out.println(" -> " + current.data);
            current = current.next;
        }
    }
}
