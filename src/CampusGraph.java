import java.util.*;

/**
 * Graph data structure representing campus locations and pathways.
 * Supports Dijkstra's algorithm for finding shortest navigation paths between campus buildings.
 */
public class CampusGraph {

    public static class Edge {
        String targetLocation;
        double distanceMeters;

        public Edge(String targetLocation, double distanceMeters) {
            this.targetLocation = targetLocation;
            this.distanceMeters = distanceMeters;
        }

        public String getTargetLocation() {
            return targetLocation;
        }

        public double getDistanceMeters() {
            return distanceMeters;
        }

        @Override
        public String toString() {
            return String.format("%s (%.0fm)", targetLocation, distanceMeters);
        }
    }

    private Map<String, List<Edge>> adjacencyList;

    public CampusGraph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a campus location/building vertex.
     */
    public void addLocation(String locationName) {
        if (locationName != null && !adjacencyList.containsKey(locationName)) {
            adjacencyList.put(locationName, new ArrayList<>());
        }
    }

    /**
     * Adds a bidirectional path between two campus locations with a distance in meters.
     */
    public void addPath(String source, String destination, double distanceMeters) {
        addLocation(source);
        addLocation(destination);

        adjacencyList.get(source).add(new Edge(destination, distanceMeters));
        adjacencyList.get(destination).add(new Edge(source, distanceMeters));
    }

    /**
     * Finds the shortest path between start and end locations using Dijkstra's algorithm.
     */
    public PathResult getShortestPath(String startLocation, String endLocation) {
        if (!adjacencyList.containsKey(startLocation) || !adjacencyList.containsKey(endLocation)) {
            return new PathResult(Collections.emptyList(), Double.POSITIVE_INFINITY, false);
        }

        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<StringDistanceNode> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distance));

        for (String node : adjacencyList.keySet()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(startLocation, 0.0);
        pq.add(new StringDistanceNode(startLocation, 0.0));

        while (!pq.isEmpty()) {
            StringDistanceNode current = pq.poll();
            String u = current.node;

            if (u.equals(endLocation)) break;

            if (current.distance > distances.get(u)) continue;

            for (Edge edge : adjacencyList.get(u)) {
                String v = edge.targetLocation;
                double weight = edge.distanceMeters;
                double newDist = distances.get(u) + weight;

                if (newDist < distances.get(v)) {
                    distances.put(v, newDist);
                    previousNodes.put(v, u);
                    pq.add(new StringDistanceNode(v, newDist));
                }
            }
        }

        if (distances.get(endLocation) == Double.POSITIVE_INFINITY) {
            return new PathResult(Collections.emptyList(), Double.POSITIVE_INFINITY, false);
        }

        List<String> path = new LinkedList<>();
        String current = endLocation;
        while (current != null) {
            path.add(0, current);
            current = previousNodes.get(current);
        }

        return new PathResult(path, distances.get(endLocation), true);
    }

    /**
     * Displays all campus locations and connected paths.
     */
    public void displayGraph() {
        System.out.println("=== Campus Graph Map (Locations & Pathways) ===");
        if (adjacencyList.isEmpty()) {
            System.out.println("No locations in graph.");
            return;
        }
        for (Map.Entry<String, List<Edge>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " connects to -> " + entry.getValue());
        }
    }

    public Set<String> getLocations() {
        return adjacencyList.keySet();
    }

    private static class StringDistanceNode {
        String node;
        double distance;

        StringDistanceNode(String node, double distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class PathResult {
        private List<String> path;
        private double totalDistance;
        private boolean pathFound;

        public PathResult(List<String> path, double totalDistance, boolean pathFound) {
            this.path = path;
            this.totalDistance = totalDistance;
            this.pathFound = pathFound;
        }

        public List<String> getPath() {
            return path;
        }

        public double getTotalDistance() {
            return totalDistance;
        }

        public boolean isPathFound() {
            return pathFound;
        }

        @Override
        public String toString() {
            if (!pathFound) {
                return "No available path found between specified locations.";
            }
            return String.format("Route: %s | Total Distance: %.1f meters",
                    String.join(" -> ", path), totalDistance);
        }
    }
}
