public class ActionStack {

    private class Node {

        String action;
        Node next;

        public Node(String action) {
            this.action = action;
            this.next = null;
        }
    }

    private Node top;

    public ActionStack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    // Add recent action to stack
    public void push(String action) {

        Node newNode = new Node(action);

        newNode.next = top;

        top = newNode;
    }

    // Remove most recent action
    public String pop() {

        if (top == null) {
            return null;
        }

        String action = top.action;

        top = top.next;

        return action;
    }

    // View latest action
    public String peek() {

        if (top == null) {
            return null;
        }

        return top.action;
    }

    // Option 7 - Display recent actions
    public void displayRecentActions() {

        if (top == null) {

            System.out.println(
                    "No recent actions available."
            );

            return;
        }

        System.out.println(
                "\n--- Recent Actions using Stack ---"
        );

        Node current = top;

        int number = 1;

        while (current != null) {

            System.out.println(
                    number + ". " + current.action
            );

            current = current.next;

            number++;
        }
    }
}
