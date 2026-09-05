// HospitalManagementSystem.java
// Entry point of the Mini Hospital Emergency Management System.
// Ties together: PatientBST, EmergencyQueue, TreatmentStack, VisitLinkedList.

import java.util.Scanner;

public class HospitalManagementSystem {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: registerPatient(); break;
                case 2: searchPatient(); break;
                case 3: deletePatient(); break;
                case 4: patientBST.displayInOrder(); break;
                case 5: dequeueForTreatment(); break;
                case 6: emergencyQueue.displayQueue(); break;
                case 7: popTreatmentRecord(); break;
                case 8: treatmentStack.displayStack(); break;
                case 9: addVisitHistory(); break;
                case 10: removeVisitHistory(); break;
                case 11: searchVisitHistory(); break;
                case 12: displayVisitHistory(); break;
                case 0:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Mini Hospital Emergency Management System =====");
        System.out.println(" 1. Register New Patient          (BST + Queue)");
        System.out.println(" 2. Search Patient                (BST)");
        System.out.println(" 3. Delete Patient                (BST)");
        System.out.println(" 4. Display All Patients In Order (BST In-order Traversal)");
        System.out.println(" 5. Call Next Patient For Treatment (Dequeue)");
        System.out.println(" 6. Display Waiting Queue");
        System.out.println(" 7. Undo Last Completed Treatment (Pop Stack)");
        System.out.println(" 8. Display Treatment History     (Stack)");
        System.out.println(" 9. Add Visit To Patient History  (Linked List)");
        System.out.println("10. Remove Visit From Patient History");
        System.out.println("11. Search Visit In Patient History");
        System.out.println("12. Display Patient Visit History");
        System.out.println(" 0. Exit");
    }

    // ---------- PATIENT / BST OPERATIONS ----------
    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        String name = readLine("Enter Name: ");
        int age = readInt("Enter Age: ");
        String contact = readLine("Enter Contact Number: ");
        String condition = readLine("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        emergencyQueue.enqueue(patient);
        System.out.println("Patient registered and added to the emergency queue.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);
        System.out.println(patient == null ? "Patient not found." : "Found: " + patient);
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientBST.delete(id);
        System.out.println(deleted ? "Patient deleted successfully." : "Patient not found.");
    }

    // ---------- QUEUE OPERATIONS ----------
    private static void dequeueForTreatment() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) return;

        System.out.println("Now treating: " + patient);
        String details = readLine("Enter treatment details: ");
        String date = readLine("Enter completion date: ");

        TreatmentRecord record = new TreatmentRecord(patient.getPatientId(), patient.getName(), details, date);
        treatmentStack.push(record);
        System.out.println("Treatment recorded in history.");
    }

    // ---------- STACK OPERATIONS ----------
    private static void popTreatmentRecord() {
        TreatmentRecord record = treatmentStack.pop();
        if (record != null) {
            System.out.println("Removed most recent record: " + record);
        }
    }

    // ---------- LINKED LIST (VISIT HISTORY) OPERATIONS ----------
    private static void addVisitHistory() {
        Patient patient = findPatientOrWarn();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID: ");
        String date = readLine("Enter Visit Date: ");
        String doctor = readLine("Enter Doctor Name: ");
        String diagnosis = readLine("Enter Diagnosis: ");
        String treatment = readLine("Enter Treatment: ");

        Visit visit = new Visit(visitId, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added to patient history.");
    }

    private static void removeVisitHistory() {
        Patient patient = findPatientOrWarn();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed." : "Visit not found.");
    }

    private static void searchVisitHistory() {
        Patient patient = findPatientOrWarn();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit == null ? "Visit not found." : "Found: " + visit);
    }

    private static void displayVisitHistory() {
        Patient patient = findPatientOrWarn();
        if (patient == null) return;

        System.out.println("Visit history for " + patient.getName() + ":");
        patient.getVisitHistory().displayVisits();
    }

    private static Patient findPatientOrWarn() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
        }
        return patient;
    }

    // ---------- INPUT HELPERS ----------
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}