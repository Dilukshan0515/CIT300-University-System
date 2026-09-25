import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom Stack implementation for maintaining action history (LIFO).
 * Useful for auditing administrative operations and supporting undo features.
 */
public class ActionStack {

    public static class Action {
        private String actionType;
        private String description;
        private String timestamp;

        public Action(String actionType, String description) {
            this.actionType = actionType;
            this.description = description;
            this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        public String getActionType() {
            return actionType;
        }

        public String getDescription() {
            return description;
        }

        public String getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s: %s", timestamp, actionType, description);
        }
    }

    private static class StackNode {
        Action action;
        StackNode next;

        StackNode(Action action) {
            this.action = action;
            this.next = null;
        }
    }

    private StackNode top;
    private int size;

    public ActionStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Pushes a new action onto the stack.
     */
    public void push(Action action) {
        if (action == null) return;
        StackNode newNode = new StackNode(action);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * Pushes an action using type and description parameters.
     */
    public void push(String actionType, String description) {
        push(new Action(actionType, description));
    }

    /**
     * Pops and returns the most recent action from the stack.
     */
    public Action pop() {
        if (isEmpty()) {
            return null;
        }
        Action action = top.action;
        top = top.next;
        size--;
        return action;
    }

    /**
     * Peeks at the top action without removing it.
     */
    public Action peek() {
        if (isEmpty()) return null;
        return top.action;
    }

    /**
     * Returns true if stack is empty.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns the size of the stack.
     */
    public int size() {
        return size;
    }

    /**
     * Displays all action history in order from newest to oldest.
     */
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("Action Stack is empty.");
            return;
        }
        System.out.println("=== Action History Stack (Most Recent First, Size=" + size + ") ===");
        StackNode current = top;
        while (current != null) {
            System.out.println(" | " + current.action);
            current = current.next;
        }
    }
}
