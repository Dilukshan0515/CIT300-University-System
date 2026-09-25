public class StudentHashTable {

    private class HashNode {

        String studentId;
        Student student;
        HashNode next;

        public HashNode(
                String studentId,
                Student student) {

            this.studentId = studentId;
            this.student = student;
            this.next = null;
        }
    }

    private HashNode[] table;

    public StudentHashTable(int size) {

        table = new HashNode[size];
    }

    // Convert Student ID into table index
    private int hash(String studentId) {

        return (
                studentId.toUpperCase().hashCode()
                        & 0x7fffffff
        ) % table.length;
    }

    // Add student to hash table
    public boolean addStudent(Student student) {

        String studentId =
                student.getStudentId();

        int index =
                hash(studentId);

        HashNode current =
                table[index];

        // Check duplicate
        while (current != null) {

            if (current.studentId
                    .equalsIgnoreCase(studentId)) {

                return false;
            }

            current = current.next;
        }

        HashNode newNode =
                new HashNode(
                        studentId,
                        student
                );

        // Add at beginning of bucket
        newNode.next =
                table[index];

        table[index] =
                newNode;

        return true;
    }

    // Search student using Student ID
    public Student searchStudent(String studentId) {

        int index =
                hash(studentId);

        HashNode current =
                table[index];

        while (current != null) {

            if (current.studentId
                    .equalsIgnoreCase(studentId)) {

                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    // Remove student from hash table
    public Student removeStudent(String studentId) {

        int index =
                hash(studentId);

        HashNode current =
                table[index];

        HashNode previous = null;

        while (current != null) {

            if (current.studentId
                    .equalsIgnoreCase(studentId)) {

                if (previous == null) {

                    table[index] =
                            current.next;

                } else {

                    previous.next =
                            current.next;
                }

                return current.student;
            }

            previous = current;

            current = current.next;
        }

        return null;
    }
}
