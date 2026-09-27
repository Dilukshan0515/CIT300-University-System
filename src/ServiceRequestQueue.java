public class ServiceRequestQueue {

    private class Node {

        ServiceRequest request;
        Node next;

        public Node(ServiceRequest request) {
            this.request = request;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public ServiceRequestQueue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    // Option 5 - Add Service Request
    public void enqueue(ServiceRequest request) {

        Node newNode = new Node(request);

        if (rear == null) {

            front = newNode;
            rear = newNode;

            return;
        }

        rear.next = newNode;

        rear = newNode;
    }

    // Option 6 - Process Next Service Request
    public ServiceRequest dequeue() {

        if (front == null) {
            return null;
        }

        ServiceRequest request =
                front.request;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        return request;
    }

    // View next request
    public ServiceRequest peek() {

        if (front == null) {
            return null;
        }

        return front.request;
    }

    // Useful for testing
    public void displayQueue() {

        if (front == null) {

            System.out.println(
                    "No service requests in queue."
            );

            return;
        }

        System.out.println(
                "\n--- Service Request Queue ---"
        );

        Node current = front;

        while (current != null) {

            System.out.println(
                    current.request
            );

            current = current.next;
        }
    }
}
