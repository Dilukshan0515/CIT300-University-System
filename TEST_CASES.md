# CIT300 University System - Test Cases Specification

This document details test cases designed to verify unit-level data structure components and integrated application workflows within the CIT300 University System.

---

## 🧪 Test Suite Summary

- **TC-01**: Student Linked List Dynamic Operations
- **TC-02**: Action Stack (LIFO) History & Audit Log
- **TC-03**: Service Request Queue (FIFO) Queueing & Processing
- **TC-04**: Student BST Insertion, Search, Deletion, and Traversals
- **TC-05**: Student Hash Table Chaining, Collision Resolution, & Lookup
- **TC-06**: Campus Graph Dijkstra Shortest Path Navigation
- **TC-07**: Integrated CLI System Workflow

---

### TC-01: Student Linked List Operations
- **Module**: `StudentLinkedList.java`
- **Objective**: Verify dynamic insertion, search, deletion, and iteration over student nodes.
- **Steps**:
  1. Instantiate empty `StudentLinkedList`.
  2. Add Student `101 (Alice)`.
  3. Add Student `102 (Bob)`.
  4. Search for ID `101` -> Verify object returned matches `Alice`.
  5. Delete Student ID `101` -> Verify returned `true`.
  6. Search for ID `101` -> Verify returned `null`.
  7. Verify `size()` equals `1`.
- **Expected Outcome**: Pass. Nodes link correctly, and head update works on deletion.

---

### TC-02: Action Stack History & Audit Log
- **Module**: `ActionStack.java`
- **Objective**: Confirm LIFO behavior for operational logs and undo tracking.
- **Steps**:
  1. Push Action `("ADD_STUDENT", "Added 101")`.
  2. Push Action `("DELETE_STUDENT", "Deleted 102")`.
  3. Call `peek()` -> Verify top action type is `"DELETE_STUDENT"`.
  4. Call `pop()` -> Verify popped action is `"DELETE_STUDENT"`.
  5. Call `peek()` -> Verify top action type is now `"ADD_STUDENT"`.
- **Expected Outcome**: Pass. Top pointer updates accurately; size decrements on `pop()`.

---

### TC-03: Service Request Queue Processing
- **Module**: `ServiceRequestQueue.java`
- **Objective**: Confirm FIFO ordering for student helpdesk requests.
- **Steps**:
  1. Enqueue ServiceRequest `Req 1001 (Transcript)`.
  2. Enqueue ServiceRequest `Req 1002 (ID Card)`.
  3. Call `dequeue()` -> Verify returned request ID is `1001`.
  4. Call `dequeue()` -> Verify returned request ID is `1002`.
  5. Call `isEmpty()` -> Verify returned `true`.
- **Expected Outcome**: Pass. Requests are served strictly in the order of submission.

---

### TC-04: Student BST Operations & Traversals
- **Module**: `StudentBST.java`
- **Objective**: Validate BST binary search properties, deletion cases, and traversals.
- **Steps**:
  1. Insert students in order: `103`, `101`, `105`, `102`, `104`.
  2. Perform In-Order Traversal -> Verify printed order: `101 -> 102 -> 103 -> 104 -> 105`.
  3. Search for ID `104` -> Verify fast logarithmic location.
  4. Delete root node `103` -> Verify BST structure maintains sorted order via in-order successor.
- **Expected Outcome**: Pass. Traversal produces strictly ascending order; root deletion maintains BST properties.

---

### TC-05: Student Hash Table Chaining & Lookup
- **Module**: `StudentHashTable.java`
- **Objective**: Test O(1) key-value lookup and separate chaining collision resolution.
- **Steps**:
  1. Create Hash Table with small initial capacity (e.g. 4) to force collisions.
  2. Insert Student `101`, Student `105`, Student `109` (all map to bucket `1`).
  3. Retrieve Student `105` via `get(105)` -> Verify correct object returned.
  4. Remove Student `105` via `remove(105)` -> Verify returned `true`.
  5. Retrieve `get(105)` -> Verify returned `null`.
- **Expected Outcome**: Pass. Bucket chains resolve collisions correctly without losing key references.

---

### TC-06: Campus Graph Dijkstra Navigation
- **Module**: `CampusGraph.java`
- **Objective**: Verify weighted graph paths and Dijkstra's algorithm for campus route optimization.
- **Steps**:
  1. Add path: `"Main Entrance"` -> `"Library"` (250m).
  2. Add path: `"Library"` -> `"Computer Lab"` (150m).
  3. Add path: `"Main Entrance"` -> `"Student Center"` (300m).
  4. Add path: `"Student Center"` -> `"Computer Lab"` (200m).
  5. Calculate shortest route from `"Main Entrance"` to `"Computer Lab"`.
- **Expected Outcome**: Pass. Returned path: `Main Entrance -> Library -> Computer Lab` with total distance `400 meters` (shorter than `Main Entrance -> Student Center -> Computer Lab` at `500 meters`).

---

### TC-07: Integrated CLI System Workflow
- **Module**: `Main.java`
- **Objective**: End-to-end user journey across all integrated modules via the command-line menu.
- **Steps**:
  1. Launch `Main`.
  2. Select Option `1` -> Register student `105 (Eva Green, 3.9, CS)`.
  3. Select Option `2` -> Search for ID `105` (verify Hash Table & BST find record).
  4. Select Option `4` -> Submit service request for Student `105`.
  5. Select Option `5` -> Process service request (verify dequeue).
  6. Select Option `6` -> View action history stack (verify `ADD_STUDENT` and `SUBMIT_REQUEST` present).
  7. Select Option `7` -> Calculate path between campus buildings.
  8. Select Option `8` -> Execute system self-test.
- **Expected Outcome**: Pass. System completes all options without runtime exceptions.
