import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusGraph {

    // Adjacency List
    private Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new LinkedHashMap<>();
    }

    // Find the actual stored location name
    private String findLocation(String location) {

        for (String existingLocation : adjacencyList.keySet()) {

            if (existingLocation.equalsIgnoreCase(location)) {
                return existingLocation;
            }
        }

        return null;
    }

    // Option 10 - Add Campus Location
    public boolean addLocation(String location) {

        if (location == null || location.trim().isEmpty()) {
            return false;
        }

        location = location.trim();

        if (findLocation(location) != null) {
            return false;
        }

        adjacencyList.put(
                location,
                new ArrayList<>()
        );

        return true;
    }

    // Option 11 - Remove Campus Location
    public boolean removeLocation(String location) {

        String actualLocation =
                findLocation(location);

        if (actualLocation == null) {
            return false;
        }

        // Remove the location itself
        adjacencyList.remove(actualLocation);

        // Remove connections to this location
        for (List<String> neighbours :
                adjacencyList.values()) {

            neighbours.removeIf(
                    neighbour ->
                            neighbour.equalsIgnoreCase(actualLocation)
            );
        }

        return true;
    }

    // Option 12 - Add Campus Connection/Road
    public boolean addConnection(
            String location1,
            String location2) {

        String first =
                findLocation(location1);

        String second =
                findLocation(location2);

        // Both locations must exist
        if (first == null || second == null) {
            return false;
        }

        // Cannot connect a location to itself
        if (first.equalsIgnoreCase(second)) {
            return false;
        }

        // Prevent duplicate connection
        if (containsIgnoreCase(
                adjacencyList.get(first),
                second)) {

            return false;
        }

        // Undirected graph
        adjacencyList.get(first).add(second);
        adjacencyList.get(second).add(first);

        return true;
    }

    // Option 13 - Remove Campus Connection/Road
    public boolean removeConnection(
            String location1,
            String location2) {

        String first =
                findLocation(location1);

        String second =
                findLocation(location2);

        if (first == null || second == null) {
            return false;
        }

        boolean removedFromFirst =
                removeIgnoreCase(
                        adjacencyList.get(first),
                        second
                );

        boolean removedFromSecond =
                removeIgnoreCase(
                        adjacencyList.get(second),
                        first
                );

        return removedFromFirst
                && removedFromSecond;
    }

    // Option 14 - Display Campus Connections
    public void displayConnections() {

        if (adjacencyList.isEmpty()) {

            System.out.println(
                    "No campus locations available."
            );

            return;
        }

        System.out.println(
                "\n--- Campus Connections ---"
        );

        for (Map.Entry<String, List<String>>
                entry : adjacencyList.entrySet()) {

            System.out.print(
                    entry.getKey() + " -> "
            );

            if (entry.getValue().isEmpty()) {

                System.out.println(
                        "No connections"
                );

            } else {

                System.out.println(
                        String.join(
                                ", ",
                                entry.getValue()
                        )
                );
            }
        }
    }

    // Option 15 - BFS Traversal
    public boolean bfs(String startLocation) {

        String start =
                findLocation(startLocation);

        if (start == null) {
            return false;
        }

        Set<String> visited =
                new LinkedHashSet<>();

        Queue<String> queue =
                new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        System.out.println(
                "\n--- BFS Traversal ---"
        );

        while (!queue.isEmpty()) {

            String current =
                    queue.poll();

            System.out.print(
                    current + " "
            );

            for (String neighbour :
                    adjacencyList.get(current)) {

                if (!visited.contains(neighbour)) {

                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }

        System.out.println();

        return true;
    }

    // Helper method
    private boolean containsIgnoreCase(
            List<String> list,
            String value) {

        for (String item : list) {

            if (item.equalsIgnoreCase(value)) {
                return true;
            }
        }

        return false;
    }

    // Helper method
    private boolean removeIgnoreCase(
            List<String> list,
            String value) {

        for (int i = 0;
             i < list.size();
             i++) {

            if (list.get(i)
                    .equalsIgnoreCase(value)) {

                list.remove(i);
                return true;
            }
        }

        return false;
    }
}
