# CIT300 Data Structures and Algorithms

## Graded Practical Assignment 1

### University Student Record and Campus Route Management System

---

## Project Overview

This project is a Java console-based application developed for the CIT300 Data Structures and Algorithms module.

The system manages university student records, student service requests, recent system actions, and campus locations and connections.

The project demonstrates the practical use of the following data structures:

- Linked List
- Stack
- Queue
- Binary Search Tree (BST)
- Hash Table
- Graph
- Breadth-First Search (BFS)

The application uses a menu-driven console interface with input validation and error handling.

---

## Group Members and Responsibilities

| Member | Name | Student ID | Assigned Responsibility |
|---|---|---|---|
| Member 1 | D.M.K. Sewmini Disanayaka | 23DA2-0966 | Linked List implementation and Student Record Management |
| Member 2 | M.R. Chamodi Dulanjali | 23DA2-0644 | Stack and Queue implementation and related operations |
| Member 3 | Ahamed Asri | 23DA2-0735 | BST implementation and Hashing/Search functionality |
| Member 4 | A. Dilukshan | 23DA2-0601 | Graph implementation, BFS traversal, and final system integration |

---

## Individual Contributions

### Member 1 – D.M.K. Sewmini Disanayaka
**Student ID:** 23DA2-0966

**Responsibility:** Linked List and Student Record Management

**Contribution:**

- Implemented the `Student` class.
- Stored Student ID, Name, Programme, and Marks.
- Implemented the `StudentLinkedList` class.
- Implemented student addition.
- Implemented student searching.
- Implemented student updating.
- Implemented student deletion.
- Implemented display of all student records using the Linked List.
- Added duplicate Student ID checking.
- Tested the Linked List operations.
- Completed the work using the `member1-linkedlist` GitHub branch and Pull Request.

---

### Member 2 – M.R. Chamodi Dulanjali
**Student ID:** 23DA2-0644

**Responsibility:** Stack and Queue Implementation

**Contribution:**

- Implemented the `ActionStack` class.
- Implemented Stack operations using the LIFO concept.
- Implemented `push`, `pop`, `peek`, and recent-action display operations.
- Implemented the `ServiceRequest` class.
- Implemented the `ServiceRequestQueue` class.
- Implemented Queue operations using the FIFO concept.
- Implemented adding service requests to the queue.
- Implemented processing the next service request.
- Added empty-queue handling.
- Tested Stack and Queue operations.
- Completed the work using the `member2-stack-queue` GitHub branch and Pull Request.

---

### Member 3 – Ahamed Asri
**Student ID:** 23DA2-0735

**Responsibility:** Binary Search Tree and Hashing

**Contribution:**

- Implemented the `StudentBST` class.
- Used Student ID as the key for organizing student records.
- Implemented student insertion into the BST.
- Implemented student searching in the BST.
- Implemented student deletion from the BST.
- Implemented in-order traversal to display students in sorted Student ID order.
- Implemented the `StudentHashTable` class.
- Implemented Student ID hashing.
- Implemented adding students to the Hash Table.
- Implemented fast student searching using hashing.
- Implemented student removal from the Hash Table.
- Added duplicate Student ID handling.
- Tested BST and Hashing operations.
- Completed the work using the `member3-bst-hashing` GitHub branch and Pull Request.

---

### Member 4 – A. Dilukshan
**Student ID:** 23DA2-0601

**Responsibility:** Graph Implementation, BFS Traversal, and Final System Integration

**Contribution:**

- Implemented the `CampusGraph` class.
- Used an adjacency list to represent the campus graph.
- Implemented adding campus locations.
- Implemented removing campus locations.
- Implemented adding campus connections or roads.
- Implemented removing campus connections or roads.
- Implemented displaying campus connections.
- Implemented Breadth-First Search (BFS) traversal.
- Added duplicate-location handling.
- Added invalid and unavailable connection handling.
- Integrated the completed components into the final menu-driven system.
- Assisted with final testing and integration of the complete application.
- Completed graph development using the `member4-graph` GitHub branch.
- Completed final system integration using the `leader-integration` branch.

---

## Implemented Menu

The final system contains the following menu:

1. Add Student Record
2. Update Student Record
3. Delete Student Record
4. Display All Records using Linked List
5. Add Service Request to Queue
6. Process Next Service Request
7. Display Recent Actions using Stack
8. Display Students using BST/AVL
9. Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS or DFS
16. Exit

---

## Data Structures Used

### Linked List

Used to store and manage university student records.

Main operations:

- Add
- Search
- Update
- Delete
- Display

### Stack

Used to maintain recent system actions.

The Stack follows:

**LIFO – Last In, First Out**

### Queue

Used to manage student service requests.

The Queue follows:

**FIFO – First In, First Out**

### Binary Search Tree

Used to organize student records according to Student ID.

In-order traversal is used to display the student records in sorted order.

### Hash Table

Used for efficient Student ID searching.

The Student ID is converted into a hash-table index using a hash function.

### Graph

Used to represent university campus locations and roads.

- Vertices represent campus locations.
- Edges represent roads or direct connections.
- An adjacency list is used to represent the graph.
- BFS is used to traverse connected campus locations.

---

## Main Java Files

```text
src/
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

## Input Validation and Error Handling

The system handles:

- Invalid menu selections
- Invalid numeric inputs
- Invalid marks
- Duplicate Student IDs
- Missing student records
- Duplicate campus locations
- Missing campus locations
- Duplicate campus connections
- Unavailable campus connections
- Empty service-request queue
- Invalid BFS starting locations
- Empty input values

---

## GitHub Collaboration

GitHub was used for team collaboration.

Each member worked using a separate branch:

```text
member1-linkedlist
member2-stack-queue
member3-bst-hashing
member4-graph
leader-integration
```

The collaboration process followed:

```text
Create Branch
     ↓
Develop Assigned Component
     ↓
Test Component
     ↓
Commit Changes
     ↓
Push Branch
     ↓
Create Pull Request
     ↓
Review
     ↓
Merge into Main
```

This allowed each member's individual contribution to be represented through branches, commits, and Pull Requests.

---

## How to Compile the Project

Open a terminal from the main project folder and run:

```bash
javac src/*.java
```

---

## How to Run the Project

After successful compilation, run:

```bash
java -cp src Main
```

The main 16-option console menu will then be displayed.

---

## Testing

Each component was tested individually before final integration.

The final integrated application was also tested for:

- Student record operations
- Linked List display
- Stack recent-action handling
- Queue FIFO processing
- BST traversal
- Hash-based searching
- Campus Graph operations
- BFS traversal
- Invalid and duplicate input handling

---

## Project Completion

All required Data Structures and Algorithms components were implemented and integrated into one Java console application.

The project demonstrates collaborative development through GitHub branches, commits, Pull Requests, component testing, and final integration.