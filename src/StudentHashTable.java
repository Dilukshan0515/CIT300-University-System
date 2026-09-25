/**
 * Custom Hash Table implementation for O(1) average time Student lookups.
 * Uses Separate Chaining (Linked Lists) to resolve collisions.
 */
public class StudentHashTable {

    private static class HashNode {
        int key;
        Student value;
        HashNode next;

        HashNode(int key, Student value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private HashNode[] table;
    private int capacity;
    private int size;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public StudentHashTable(int initialCapacity) {
        this.capacity = initialCapacity > 0 ? initialCapacity : 16;
        this.table = new HashNode[capacity];
        this.size = 0;
    }

    public StudentHashTable() {
        this(16);
    }

    private int hash(int key) {
        return Math.abs(key) % capacity;
    }

    /**
     * Inserts or updates a student record in the hash table.
     */
    public void put(int key, Student student) {
        if (student == null) return;
        int bucketIndex = hash(key);
        HashNode head = table[bucketIndex];

        // Search if key already exists in chain
        HashNode current = head;
        while (current != null) {
            if (current.key == key) {
                current.value = student;
                return;
            }
            current = current.next;
        }

        // Insert new node at the head of the bucket chain
        HashNode newNode = new HashNode(key, student);
        newNode.next = head;
        table[bucketIndex] = newNode;
        size++;

        // Rehash if load factor exceeded
        if ((double) size / capacity >= DEFAULT_LOAD_FACTOR) {
            rehash();
        }
    }

    /**
     * Overload for convenience using student.getStudentId() as key.
     */
    public void put(Student student) {
        if (student != null) {
            put(student.getStudentId(), student);
        }
    }

    /**
     * Retrieves a student by ID. Returns null if not found.
     */
    public Student get(int key) {
        int bucketIndex = hash(key);
        HashNode current = table[bucketIndex];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes a student record by ID.
     */
    public boolean remove(int key) {
        int bucketIndex = hash(key);
        HashNode current = table[bucketIndex];
        HashNode prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    table[bucketIndex] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if key exists.
     */
    public boolean containsKey(int key) {
        return get(key) != null;
    }

    private void rehash() {
        int oldCapacity = capacity;
        HashNode[] oldTable = table;

        capacity = oldCapacity * 2;
        table = new HashNode[capacity];
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            HashNode current = oldTable[i];
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Displays hash table buckets and chain elements.
     */
    public void displayHashTable() {
        System.out.println("=== Student Hash Table (Capacity=" + capacity + ", Size=" + size + ") ===");
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            HashNode current = table[i];
            if (current == null) {
                System.out.println("[ Empty ]");
            } else {
                while (current != null) {
                    System.out.print("[" + current.key + " -> " + current.value.getName() + "] ");
                    current = current.next;
                }
                System.out.println();
            }
        }
    }
}
