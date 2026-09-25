public class StudentLinkedList {

    private class Node {

        Student student;
        Node next;

        public Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    private Node head;

    public StudentLinkedList() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean addStudent(Student student) {

        if (searchStudent(student.getStudentId()) != null) {
            return false;
        }

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
            return true;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;

        return true;
    }

    public Student searchStudent(String studentId) {

        Node current = head;

        while (current != null) {

            if (current.student.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    public boolean updateStudent(
            String studentId,
            String newName,
            String newProgramme,
            double newMarks) {

        Student student = searchStudent(studentId);

        if (student == null) {
            return false;
        }

        student.setName(newName);
        student.setProgramme(newProgramme);
        student.setMarks(newMarks);

        return true;
    }

    public Student deleteStudent(String studentId) {

        if (head == null) {
            return null;
        }

        if (head.student.getStudentId().equalsIgnoreCase(studentId)) {

            Student deletedStudent = head.student;

            head = head.next;

            return deletedStudent;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.student.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                Student deletedStudent = current.next.student;

                current.next = current.next.next;

                return deletedStudent;
            }

            current = current.next;
        }

        return null;
    }

    public void displayAllStudents() {

        if (head == null) {

            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n--- Student Records ---");

        Node current = head;

        while (current != null) {

            System.out.println(current.student);

            current = current.next;
        }
    }
}
