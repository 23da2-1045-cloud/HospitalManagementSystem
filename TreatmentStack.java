// TreatmentStack.java
// A custom LIFO Stack (linked-node based) that stores completed
// treatment records.

public class TreatmentStack {

    private class Node {
        TreatmentRecord record;
        Node next;

        Node(TreatmentRecord record) {
            this.record = record;
        }
    }

    private Node top;
    private int size;

    // ---------- PUSH ----------
    public void push(TreatmentRecord record) {
        Node newNode = new Node(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // ---------- POP ----------
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to remove.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    // ---------- EMPTY CHECK ----------
    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }

    // ---------- DISPLAY ----------
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No completed treatments recorded yet.");
            return;
        }
        System.out.println("Treatment history (most recent first):");
        Node current = top;
        while (current != null) {
            System.out.println(current.record);
            current = current.next;
        }
    }
}