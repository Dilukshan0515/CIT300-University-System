import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Member 1
    private static final StudentLinkedList studentList =
            new StudentLinkedList();

    // Member 2
    private static final ActionStack actionStack =
            new ActionStack();

    private static final ServiceRequestQueue requestQueue =
            new ServiceRequestQueue();

    // Member 3
    private static final StudentBST studentBST =
            new StudentBST();

    private static final StudentHashTable studentHashTable =
            new StudentHashTable(10);

    // Member 4
    private static final CampusGraph campusGraph =
            new CampusGraph();

    private static int nextRequestId = 1;

    public static void main(String[] args) {

        int choice;

        do {

            displayMenu();

            choice = readInt(
                    "Enter your choice (1-16): ",
                    1,
                    16
            );

            switch (choice) {

                case 1:
                    addStudentRecord();
                    break;

                case 2:
                    updateStudentRecord();
                    break;

                case 3:
                    deleteStudentRecord();
                    break;

                case 4:
                    studentList.displayAllStudents();
                    break;

                case 5:
                    addServiceRequest();
                    break;

                case 6:
                    processNextServiceRequest();
                    break;

                case 7:
                    actionStack.displayRecentActions();
                    break;

                case 8:
                    studentBST.displayStudents();
                    break;

                case 9:
                    searchStudentUsingHashing();
                    break;

                case 10:
                    addCampusLocation();
                    break;

                case 11:
                    removeCampusLocation();
                    break;

                case 12:
                    addCampusConnection();
                    break;

                case 13:
                    removeCampusConnection();
                    break;

                case 14:
                    campusGraph.displayConnections();
                    break;

                case 15:
                    traverseCampusLocations();
                    break;

                case 16:
                    System.out.println(
                            "\nThank you for using the system."
                    );
                    System.out.println(
                            "Program terminated successfully."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid option."
                    );
            }

        } while (choice != 16);

        scanner.close();
    }

    // -------------------------------------------------
    // MENU
    // -------------------------------------------------

    private static void displayMenu() {

        System.out.println();
        System.out.println(
                "========================================================"
        );

        System.out.println(
                " UNIVERSITY STUDENT RECORD & CAMPUS ROUTE MANAGEMENT SYSTEM"
        );

        System.out.println(
                "========================================================"
        );

        System.out.println(
                "1. Add Student Record"
        );

        System.out.println(
                "2. Update Student Record"
        );

        System.out.println(
                "3. Delete Student Record"
        );

        System.out.println(
                "4. Display All Records using Linked List"
        );

        System.out.println(
                "5. Add Service Request to Queue"
        );

        System.out.println(
                "6. Process Next Service Request"
        );

        System.out.println(
                "7. Display Recent Actions using Stack"
        );

        System.out.println(
                "8. Display Students using BST/AVL"
        );

        System.out.println(
                "9. Search Student using Hashing"
        );

        System.out.println(
                "10. Add Campus Location"
        );

        System.out.println(
                "11. Remove Campus Location"
        );

        System.out.println(
                "12. Add Campus Connection/Road"
        );

        System.out.println(
                "13. Remove Campus Connection/Road"
        );

        System.out.println(
                "14. Display Campus Connections"
        );

        System.out.println(
                "15. Traverse Campus Locations using BFS or DFS"
        );

        System.out.println(
                "16. Exit"
        );

        System.out.println(
                "========================================================"
        );
    }

    // -------------------------------------------------
    // OPTION 1 - ADD STUDENT
    // -------------------------------------------------

    private static void addStudentRecord() {

        System.out.println(
                "\n--- Add Student Record ---"
        );

        String studentId =
                readNonEmpty(
                        "Enter Student ID: "
                );

        if (studentList.searchStudent(studentId) != null) {

            System.out.println(
                    "Error: Student ID already exists."
            );

            return;
        }

        String name =
                readNonEmpty(
                        "Enter Student Name: "
                );

        String programme =
                readNonEmpty(
                        "Enter Programme: "
                );

        double marks =
                readDouble(
                        "Enter Marks (0-100): ",
                        0,
                        100
                );

        Student student =
                new Student(
                        studentId,
                        name,
                        programme,
                        marks
                );

        boolean added =
                studentList.addStudent(student);

        if (added) {

            studentBST.insertStudent(student);

            studentHashTable.addStudent(student);

            actionStack.push(
                    "Added Student: " + studentId
            );

            System.out.println(
                    "Student record added successfully."
            );

        } else {

            System.out.println(
                    "Unable to add student record."
            );
        }
    }

    // -------------------------------------------------
    // OPTION 2 - UPDATE STUDENT
    // -------------------------------------------------

    private static void updateStudentRecord() {

        System.out.println(
                "\n--- Update Student Record ---"
        );

        String studentId =
                readNonEmpty(
                        "Enter Student ID to update: "
                );

        Student student =
                studentList.searchStudent(studentId);

        if (student == null) {

            System.out.println(
                    "Student record not found."
            );

            return;
        }

        System.out.println(
                "Current Record:"
        );

        System.out.println(student);

        String newName =
                readNonEmpty(
                        "Enter New Name: "
                );

        String newProgramme =
                readNonEmpty(
                        "Enter New Programme: "
                );

        double newMarks =
                readDouble(
                        "Enter New Marks (0-100): ",
                        0,
                        100
                );

        boolean updated =
                studentList.updateStudent(
                        studentId,
                        newName,
                        newProgramme,
                        newMarks
                );

        if (updated) {

            actionStack.push(
                    "Updated Student: " + studentId
            );

            System.out.println(
                    "Student record updated successfully."
            );

        } else {

            System.out.println(
                    "Unable to update student record."
            );
        }
    }

    // -------------------------------------------------
    // OPTION 3 - DELETE STUDENT
    // -------------------------------------------------

    private static void deleteStudentRecord() {

        System.out.println(
                "\n--- Delete Student Record ---"
        );

        String studentId =
                readNonEmpty(
                        "Enter Student ID to delete: "
                );

        Student deletedStudent =
                studentList.deleteStudent(studentId);

        if (deletedStudent == null) {

            System.out.println(
                    "Student record not found."
            );

            return;
        }

        studentBST.deleteStudent(studentId);

        studentHashTable.removeStudent(studentId);

        actionStack.push(
                "Deleted Student: " + studentId
        );

        System.out.println(
                "Student record deleted successfully."
        );
    }

    // -------------------------------------------------
    // OPTION 5 - ADD SERVICE REQUEST
    // -------------------------------------------------

    private static void addServiceRequest() {

        System.out.println(
                "\n--- Add Service Request ---"
        );

        String studentId =
                readNonEmpty(
                        "Enter Student ID: "
                );

        Student student =
                studentList.searchStudent(studentId);

        if (student == null) {

            System.out.println(
                    "Student record not found."
            );

            System.out.println(
                    "Add the student before creating a service request."
            );

            return;
        }

        String requestType =
                readNonEmpty(
                        "Enter Service Request: "
                );

        ServiceRequest request =
                new ServiceRequest(
                        nextRequestId,
                        studentId,
                        requestType
                );

        requestQueue.enqueue(request);

        actionStack.push(
                "Added Service Request #"
                        + nextRequestId
                        + " for Student: "
                        + studentId
        );

        System.out.println(
                "Service request added successfully."
        );

        System.out.println(
                "Request ID: " + nextRequestId
        );

        nextRequestId++;
    }

    // -------------------------------------------------
    // OPTION 6 - PROCESS SERVICE REQUEST
    // -------------------------------------------------

    private static void processNextServiceRequest() {

        System.out.println(
                "\n--- Process Next Service Request ---"
        );

        ServiceRequest request =
                requestQueue.dequeue();

        if (request == null) {

            System.out.println(
                    "No service requests available."
            );

            return;
        }

        System.out.println(
                "Processing:"
        );

        System.out.println(request);

        actionStack.push(
                "Processed Service Request #"
                        + request.getRequestId()
        );

        System.out.println(
                "Service request processed successfully."
        );
    }

    // -------------------------------------------------
    // OPTION 9 - HASH SEARCH
    // -------------------------------------------------

    private static void searchStudentUsingHashing() {

        System.out.println(
                "\n--- Search Student using Hashing ---"
        );

        String studentId =
                readNonEmpty(
                        "Enter Student ID: "
                );

        Student student =
                studentHashTable.searchStudent(
                        studentId
                );

        if (student == null) {

            System.out.println(
                    "Student record not found."
            );

        } else {

            System.out.println(
                    "Student found:"
            );

            System.out.println(student);
        }
    }

    // -------------------------------------------------
    // OPTION 10 - ADD LOCATION
    // -------------------------------------------------

    private static void addCampusLocation() {

        System.out.println(
                "\n--- Add Campus Location ---"
        );

        String location =
                readNonEmpty(
                        "Enter Location Name: "
                );

        boolean added =
                campusGraph.addLocation(location);

        if (added) {

            actionStack.push(
                    "Added Campus Location: "
                            + location
            );

            System.out.println(
                    "Campus location added successfully."
            );

        } else {

            System.out.println(
                    "Unable to add location."
            );

            System.out.println(
                    "The location may already exist."
            );
        }
    }

    // -------------------------------------------------
    // OPTION 11 - REMOVE LOCATION
    // -------------------------------------------------

    private static void removeCampusLocation() {

        System.out.println(
                "\n--- Remove Campus Location ---"
        );

        String location =
                readNonEmpty(
                        "Enter Location Name: "
                );

        boolean removed =
                campusGraph.removeLocation(location);

        if (removed) {

            actionStack.push(
                    "Removed Campus Location: "
                            + location
            );

            System.out.println(
                    "Campus location removed successfully."
            );

        } else {

            System.out.println(
                    "Campus location not found."
            );
        }
    }

    // -------------------------------------------------
    // OPTION 12 - ADD CONNECTION
    // -------------------------------------------------

    private static void addCampusConnection() {

        System.out.println(
                "\n--- Add Campus Connection / Road ---"
        );

        String location1 =
                readNonEmpty(
                        "Enter First Location: "
                );

        String location2 =
                readNonEmpty(
                        "Enter Second Location: "
                );

        boolean added =
                campusGraph.addConnection(
                        location1,
                        location2
                );

        if (added) {

            actionStack.push(
                    "Added Campus Connection: "
                            + location1
                            + " <-> "
                            + location2
            );

            System.out.println(
                    "Campus connection added successfully."
            );

        } else {

            System.out.println(
                    "Unable to add connection."
            );

            System.out.println(
                    "Check whether both locations exist or the connection already exists."
            );
        }
    }

    // -------------------------------------------------
    // OPTION 13 - REMOVE CONNECTION
    // -------------------------------------------------

    private static void removeCampusConnection() {

        System.out.println(
                "\n--- Remove Campus Connection / Road ---"
        );

        String location1 =
                readNonEmpty(
                        "Enter First Location: "
                );

        String location2 =
                readNonEmpty(
                        "Enter Second Location: "
                );

        boolean removed =
                campusGraph.removeConnection(
                        location1,
                        location2
                );

        if (removed) {

            actionStack.push(
                    "Removed Campus Connection: "
                            + location1
                            + " <-> "
                            + location2
            );

            System.out.println(
                    "Campus connection removed successfully."
            );

        } else {

            System.out.println(
                    "Connection not found or location does not exist."
            );
        }
    }

    // -------------------------------------------------
    // OPTION 15 - BFS
    // -------------------------------------------------

    private static void traverseCampusLocations() {

        System.out.println(
                "\n--- Campus Location Traversal ---"
        );

        System.out.println(
                "Traversal Method: BFS"
        );

        String startLocation =
                readNonEmpty(
                        "Enter Starting Location: "
                );

        boolean success =
                campusGraph.bfs(
                        startLocation
                );

        if (!success) {

            System.out.println(
                    "Starting location not found."
            );
        }
    }

    // -------------------------------------------------
    // INPUT VALIDATION
    // -------------------------------------------------

    private static String readNonEmpty(
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty. Please try again."
            );
        }
    }

    private static int readInt(
            String message,
            int min,
            int max) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                int number =
                        Integer.parseInt(input);

                if (number >= min
                        && number <= max) {

                    return number;
                }

                System.out.println(
                        "Please enter a number from "
                                + min
                                + " to "
                                + max
                                + "."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    private static double readDouble(
            String message,
            double min,
            double max) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                double number =
                        Double.parseDouble(input);

                if (number >= min
                        && number <= max) {

                    return number;
                }

                System.out.println(
                        "Marks must be between "
                                + min
                                + " and "
                                + max
                                + "."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid marks. Please enter a numeric value."
                );
            }
        }
    }
}
