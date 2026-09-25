/**
 * Student data model representing a student in the CIT300 University System.
 */
public class Student implements Comparable<Student> {
    private int studentId;
    private String name;
    private double gpa;
    private String major;
    private String email;

    public Student(int studentId, String name, double gpa, String major, String email) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpa;
        this.major = major;
        this.email = email;
    }

    public Student(int studentId, String name, double gpa, String major) {
        this(studentId, name, gpa, major, name.toLowerCase().replaceAll("\\s+", ".") + "@university.edu");
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.studentId, other.studentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(studentId);
    }

    @Override
    public String toString() {
        return String.format("Student[ID=%d, Name='%s', GPA=%.2f, Major='%s', Email='%s']",
                studentId, name, gpa, major, email);
    }
}
