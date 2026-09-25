import java.util.Scanner;

/**
 * Main application driver integrating all CIT300 University System modules:
 * - Student Records (LinkedList, BST, Hash Table)
 * - Action Audit Stack
 * - Service Request Processing Queue
 * - Campus Graph Navigation
 */
public class Main {

    private static StudentLinkedList studentList = new StudentLinkedList();
    private static StudentBST studentBST = new StudentBST();
    private static StudentHashTable studentHashTable = new StudentHashTable();
    private static ActionStack actionStack = new ActionStack();
    private static ServiceRequestQueue requestQueue = new ServiceRequestQueue();
    private static CampusGraph campusGraph = new CampusGraph();

    private static int requestSequence = 1001;

    public static void main(String[] args) {
        initializeSampleData();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("   WELCOME TO CIT300 UNIVERSITY MANAGEMENT SYSTEM");
        System.out.println("=================================================");

        while (running) {
            printMainMenu();
            System.out.print("Enter choice (1-9): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    addNewStudent(scanner);
                    break;
                case "2":
                    searchStudent(scanner);
                    break;
                case "3":
                    displayStudentRecords();
                    break;
                case "4":
                    submitServiceRequest(scanner);
                    break;
                case "5":
                    processNextServiceRequest();
                    break;
                case "6":
                    viewActionStackHistory();
                    break;
                case "7":
                    navigateCampusGraph(scanner);
                    break;
                case "8":
                    runSystemSelfTest();
                    break;
                case "9":
                    running = false;
                    System.out.println("\nExiting CIT300 University System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("                 MAIN MENU");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Add New Student (Updates LinkedList, BST, Hash Table)");
        System.out.println("2. Search Student (Fast Lookup via Hash Table / BST)");
        System.out.println("3. Display All Student Records (In-Order BST & LinkedList)");
        System.out.println("4. Submit Student Service Request (Enqueue)");
        System.out.println("5. Process Next Service Request (Dequeue)");
        System.out.println("6. View Action Stack History (Audit Log)");
        System.out.println("7. Find Shortest Campus Route (Graph Navigation)");
        System.out.println("8. Run Data Structures Self-Test Demonstration");
        System.out.println("9. Exit System");
        System.out.println("-------------------------------------------------");
    }

    private static void initializeSampleData() {
        // Seed initial students
        Student s1 = new Student(101, "Alice Smith", 3.85, "Computer Science");
        Student s2 = new Student(102, "Bob Johnson", 3.42, "Software Engineering");
        Student s3 = new Student(103, "Charlie Davis", 3.91, "Cybersecurity");
        Student s4 = new Student(104, "Diana Prince", 3.75, "Data Science");

        addStudentToStructures(s1);
        addStudentToStructures(s2);
        addStudentToStructures(s3);
        addStudentToStructures(s4);

        actionStack.push("SYSTEM_INIT", "Seeded 4 initial student records into system structures.");

        // Seed initial service requests
        requestQueue.enqueue(new ServiceRequest(requestSequence++, 101, "TRANSCRIPT", "Request official transcript for grad application", "HIGH"));
        requestQueue.enqueue(new ServiceRequest(requestSequence++, 102, "ID_CARD", "Replacement ID card request", "MEDIUM"));

        // Seed campus graph nodes and paths
        campusGraph.addPath("Main Entrance", "Library", 250);
        campusGraph.addPath("Library", "Computer Lab", 150);
        campusGraph.addPath("Main Entrance", "Student Center", 300);
        campusGraph.addPath("Student Center", "Computer Lab", 200);
        campusGraph.addPath("Computer Lab", "Engineering Building", 180);
        campusGraph.addPath("Student Center", "Dormitory A", 400);
        campusGraph.addPath("Engineering Building", "Dormitory A", 350);
    }

    private static void addStudentToStructures(Student student) {
        studentList.add(student);
        studentBST.insert(student);
        studentHashTable.put(student);
    }

    private static void addNewStudent(Scanner scanner) {
        try {
            System.out.print("Enter Student ID (integer): ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            if (studentHashTable.containsKey(id)) {
                System.out.println("Error: Student with ID " + id + " already exists!");
                return;
            }

            System.out.print("Enter Student Full Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter GPA (0.0 - 4.0): ");
            double gpa = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter Major: ");
            String major = scanner.nextLine().trim();

            Student student = new Student(id, name, gpa, major);
            addStudentToStructures(student);
            actionStack.push("ADD_STUDENT", "Added student ID " + id + " (" + name + ")");

            System.out.println("Successfully registered student: " + student);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Student creation cancelled.");
        }
    }

    private static void searchStudent(Scanner scanner) {
        try {
            System.out.print("Enter Student ID to search: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("\n--- Searching via Hash Table (O(1) avg) ---");
            Student fromHash = studentHashTable.get(id);
            if (fromHash != null) {
                System.out.println("Found in Hash Table: " + fromHash);
            } else {
                System.out.println("Not found in Hash Table.");
            }

            System.out.println("\n--- Searching via BST (O(log n) avg) ---");
            Student fromBST = studentBST.search(id);
            if (fromBST != null) {
                System.out.println("Found in BST: " + fromBST);
            } else {
                System.out.println("Not found in BST.");
            }

            actionStack.push("SEARCH_STUDENT", "Searched for student ID " + id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID format.");
        }
    }

    private static void displayStudentRecords() {
        System.out.println("\n--- Displaying via Student LinkedList ---");
        studentList.display();

        System.out.println("\n--- Displaying Sorted Records via BST In-Order Traversal ---");
        studentBST.inOrderTraversal();

        System.out.println("\n--- Displaying Hash Table Buckets ---");
        studentHashTable.displayHashTable();

        actionStack.push("VIEW_STUDENTS", "Viewed all student records across structures.");
    }

    private static void submitServiceRequest(Scanner scanner) {
        try {
            System.out.print("Enter Student ID submitting request: ");
            int studentId = Integer.parseInt(scanner.nextLine().trim());

            if (!studentHashTable.containsKey(studentId)) {
                System.out.println("Warning: Student ID " + studentId + " is not registered in the system.");
            }

            System.out.print("Enter Request Type (e.g., ADVISING, REGISTRATION, TRANSCRIPT): ");
            String type = scanner.nextLine().trim();

            System.out.print("Enter Short Description: ");
            String desc = scanner.nextLine().trim();

            System.out.print("Enter Priority (HIGH, MEDIUM, LOW): ");
            String priority = scanner.nextLine().trim();

            ServiceRequest req = new ServiceRequest(requestSequence++, studentId, type, desc, priority);
            requestQueue.enqueue(req);
            actionStack.push("SUBMIT_REQUEST", "Enqueued service request ID " + req.getRequestId() + " for student " + studentId);

            System.out.println("Service request submitted successfully: " + req);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        }
    }

    private static void processNextServiceRequest() {
        ServiceRequest req = requestQueue.dequeue();
        if (req == null) {
            System.out.println("No pending service requests in queue.");
        } else {
            req.setStatus("COMPLETED");
            actionStack.push("PROCESS_REQUEST", "Dequeued and completed request ID " + req.getRequestId());
            System.out.println("Processed & Resolved Service Request: " + req);
        }
    }

    private static void viewActionStackHistory() {
        actionStack.displayHistory();
    }

    private static void navigateCampusGraph(Scanner scanner) {
        campusGraph.displayGraph();
        System.out.println("\nLocations available: " + campusGraph.getLocations());
        System.out.print("Enter Start Building: ");
        String start = scanner.nextLine().trim();

        System.out.print("Enter Destination Building: ");
        String end = scanner.nextLine().trim();

        CampusGraph.PathResult result = campusGraph.getShortestPath(start, end);
        System.out.println("\n=== Dijkstra Shortest Path Result ===");
        System.out.println(result);

        actionStack.push("NAVIGATE_CAMPUS", "Calculated route from " + start + " to " + end);
    }

    private static void runSystemSelfTest() {
        System.out.println("\n==========================================");
        System.out.println("     RUNNING SYSTEM DATA STRUCTURE TESTS   ");
        System.out.println("==========================================");

        System.out.println("\n1. Testing Student LinkedList Size: " + studentList.size());
        System.out.println("2. Testing ActionStack Top Item: " + actionStack.peek());
        System.out.println("3. Testing ServiceRequestQueue Pending Count: " + requestQueue.size());
        System.out.println("4. Testing BST Search for ID 103: " + studentBST.search(103));
        System.out.println("5. Testing Hash Table Search for ID 101: " + studentHashTable.get(101));

        CampusGraph.PathResult sampleRoute = campusGraph.getShortestPath("Main Entrance", "Engineering Building");
        System.out.println("6. Testing Campus Graph Dijkstra Route (Main Entrance -> Engineering Building):");
        System.out.println("   " + sampleRoute);

        actionStack.push("SELF_TEST", "Ran data structures verification self-test.");
        System.out.println("\nSelf-Test execution complete! All structures operating normally.");
    }
}
