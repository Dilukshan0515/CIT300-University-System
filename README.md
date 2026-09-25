# CIT300 University System

A Java-based university management system demonstrating custom implementations of fundamental data structures and algorithms, built for performance, modularity, and maintainability.

---

## 📁 Project Structure

```text
CIT300-University-System/
│
├── README.md
├── .gitignore
├── TEST_CASES.md
│
└── src/
    ├── Student.java
    ├── StudentLinkedList.java
    ├── ActionStack.java
    ├── ServiceRequest.java
    ├── ServiceRequestQueue.java
    ├── StudentBST.java
    ├── StudentHashTable.java
    ├── CampusGraph.java
    └── Main.java
```

---

## 🛠️ Data Structures & Architecture

| Data Structure | Source File | Purpose / Use Case | Time Complexity |
| :--- | :--- | :--- | :--- |
| **Student Entity** | [`Student.java`](src/Student.java) | Core domain object holding student profile details (ID, Name, GPA, Major, Email). | N/A |
| **Singly Linked List** | [`StudentLinkedList.java`](src/StudentLinkedList.java) | Linear dynamic collection storing all student records sequentially. | Search: O(n), Insert: O(1) |
| **Audit Action Stack** | [`ActionStack.java`](src/ActionStack.java) | LIFO stack tracking administrative system operations and audit history. | Push/Pop: O(1) |
| **Service Request Queue** | [`ServiceRequestQueue.java`](src/ServiceRequestQueue.java) | FIFO queue processing student service desk requests in arrival order. | Enqueue/Dequeue: O(1) |
| **Binary Search Tree** | [`StudentBST.java`](src/StudentBST.java) | Tree structure ordering students by ID for in-order traversals and search. | Search/Insert: O(log n) avg |
| **Separate Chaining Hash Table** | [`StudentHashTable.java`](src/StudentHashTable.java) | Hash table with separate chaining for constant-time student lookups by ID. | Lookup/Put: O(1) avg |
| **Campus Weighted Graph** | [`CampusGraph.java`](src/CampusGraph.java) | Adjacency list representation of campus buildings with **Dijkstra's Algorithm** for shortest navigation routes. | Shortest Path: O((V + E) log V) |
| **System Driver** | [`Main.java`](src/Main.java) | Integrated console menu interface orchestrating all data structure modules. | N/A |

---

## 🔀 Git Workflow & Collaboration Guidelines

To prevent merge conflicts during team development:
- **Modular Component Ownership**: Team members develop individual data structure modules independently on feature branches (e.g. `feature/student-bst`, `feature/campus-graph`).
- **Main.java Integration Rule**: `Main.java` orchestrates all system modules. Avoid concurrent edits to `Main.java`. `Main.java` is integrated after component pull requests (PRs) have been thoroughly reviewed and merged into `main`.

---

## 🚀 How to Compile and Run

### Prerequisites
- Java Development Kit (JDK 8 or higher) installed and configured on your system environment path.

### 1. Compile all Java files
From the project root directory (`CIT300-University-System`):

```bash
javac -d bin src/*.java
```

### 2. Run the Application
Execute the compiled byte code:

```bash
java -cp bin Main
```

---

## 🧪 Testing

Refer to [`TEST_CASES.md`](TEST_CASES.md) for step-by-step manual and automated verification scenarios for each module and integrated CLI workflows.
