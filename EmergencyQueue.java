// EmergencyQueue.java
// A custom FIFO Queue (linked-node based) used to manage patients
// waiting for emergency treatment.

public class EmergencyQueue {

    private class Node {
        Patient patient;
        Node next;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node front, rear;
    private int size;

    // ---------- ENQUEUE ----------
    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.getName() + " added to the emergency queue.");
    }

    // ---------- DEQUEUE ----------
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to treat.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null; // queue became empty
        }
        size--;
        return patient;
    }

    // ---------- EMPTY CHECK ----------
    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }

    // ---------- DISPLAY ----------
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting.");
            return;
        }
        System.out.println("Patients waiting (front to rear):");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }
}