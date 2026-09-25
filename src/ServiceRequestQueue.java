/**
 * Custom Queue implementation (FIFO) for managing student service requests.
 */
public class ServiceRequestQueue {

    private static class Node {
        ServiceRequest request;
        Node next;

        Node(ServiceRequest request) {
            this.request = request;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public ServiceRequestQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Enqueues a new ServiceRequest at the end of the queue.
     */
    public void enqueue(ServiceRequest request) {
        if (request == null) return;
        Node newNode = new Node(request);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /**
     * Dequeues and returns the next ServiceRequest at the front of the queue.
     */
    public ServiceRequest dequeue() {
        if (isEmpty()) {
            return null;
        }
        ServiceRequest request = front.request;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return request;
    }

    /**
     * Peeks at the request at the front without removing it.
     */
    public ServiceRequest peek() {
        if (isEmpty()) return null;
        return front.request;
    }

    /**
     * Checks if queue is empty.
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Returns size of queue.
     */
    public int size() {
        return size;
    }

    /**
     * Displays all pending service requests in queue order.
     */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Service Request Queue is empty.");
            return;
        }
        System.out.println("=== Service Request Queue (Front to Rear, Size=" + size + ") ===");
        Node current = front;
        while (current != null) {
            System.out.println(" -> " + current.request);
            current = current.next;
        }
    }
}
