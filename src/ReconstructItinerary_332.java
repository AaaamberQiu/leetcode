import java.util.*;

public class ReconstructItinerary_332 {

//    public static List<String> findItinerary(List<List<String>> tickets) {
//        // init graph
//        Map<String, List<String>> map = init(tickets);
//        for(String key: map.keySet()){
//            Collections.sort(map.get(key));
//        }
//        List<String> path = new ArrayList<>();
//        dfs(map, path, "JFK");
//        return path;
//    }
//
//    public static Map<String, List<String>> init(List<List<String>> tickets){
//        Map<String, List<String>> map = new HashMap<>();
//        for(List<String> ticket: tickets){
//            map.computeIfAbsent(ticket.get(0), k -> new ArrayList<>());
//            map.get(ticket.get(0)).add(ticket.get(1));
//        }
//        return map;
//    }
//
//    public static boolean dfs(Map<String, List<String>> map, List<String> path, String city){
//        path.add(city);
//        if(map.size() == 0) return true;
//        List<String> candidates = map.getOrDefault(city, new ArrayList<>());
//        for(int i = 0; i<candidates.size(); i++){
//            String next = candidates.get(i);
//            candidates.remove(i);
//            if(candidates.size() == 0) map.remove(city);
//
//            if(dfs(map, path, next)) return true;
//
//            map.computeIfAbsent(city, k -> new ArrayList<>());
//            map.get(city).add(i, next);
//        }
//        path.remove(city);
//        return false;
//    }

    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;
    public List<String> findItinerary(String[][] tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<>());
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }

    public static void main(String[] args) {
//        List<List<String>> tickets = new ArrayList<>();
//        tickets.add(Arrays.asList("JFK","KUL"));
//        tickets.add(Arrays.asList("JFK","NRT"));
//        tickets.add(Arrays.asList("NRT","JFK"));

        String[][] tickets = {{"JFK","KUL"}, {"JFK","NRT"}, {"NRT","JFK"}};
        ReconstructItinerary_332 solution = new ReconstructItinerary_332();
        List<String> path = solution.findItinerary(tickets);
        System.out.println(path);
    }

}
