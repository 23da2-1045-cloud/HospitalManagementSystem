# Mini Hospital Emergency Management System

A console-based Java application built for CIT300 - Data Structures and Algorithms
(Individual Mid Assignment). It simulates patient registration, emergency treatment
queuing, treatment history tracking, and patient visit history — each backed by a
specific data structure implemented from scratch (no `java.util.Stack`/`Queue`).

## Data Structures Used

| Feature                     | Data Structure          | File(s) |
|------------------------------|--------------------------|---------|
| Patient records (by Patient ID) | Binary Search Tree      | `Patient.java`, `PatientBST.java` |
| Emergency waiting list       | Queue (FIFO)             | `EmergencyQueue.java` |
| Completed treatment history  | Stack (LIFO)             | `TreatmentRecord.java`, `TreatmentStack.java` |
| Per-patient past visits      | Singly Linked List       | `Visit.java`, `VisitLinkedList.java` |
| Menu / orchestration         | —                        | `HospitalManagementSystem.java` |

## How It Works

1. **Register a patient** → inserted into the `PatientBST` (keyed by Patient ID) and
   simultaneously enqueued into the `EmergencyQueue`.
2. **Call next patient** → dequeues from the `EmergencyQueue`, collects treatment
   details, and pushes a `TreatmentRecord` onto the `TreatmentStack`.
3. **Search / delete a patient** → operates directly on the `PatientBST`.
4. **Display all patients** → in-order traversal of the `PatientBST` (ascending Patient ID).
5. **Visit history** → each `Patient` owns a `VisitLinkedList`; you can add, remove,
   search, and display visits for a given patient.

## How to Compile and Run

```bash
javac *.java
java HospitalManagementSystem
```

## Project Structure

```
HospitalEMS/
├── Patient.java
├── PatientBST.java
├── EmergencyQueue.java
├── TreatmentRecord.java
├── TreatmentStack.java
├── Visit.java
├── VisitLinkedList.java
├── HospitalManagementSystem.java
└── README.md
```

## Notes

- Each core data structure (BST, Queue, Stack, Singly Linked List) is implemented
  manually with its own node class, rather than using Java's built-in collections,
  to demonstrate understanding of the underlying mechanics.
- Empty-structure cases (empty queue dequeue, empty stack pop, empty visit list)
  are handled with clear console messages instead of exceptions.