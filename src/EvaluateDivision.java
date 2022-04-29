import java.util.*;

public class EvaluateDivision {

    /**
     * 399. Evaluate Division
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = initGraph(equations, values);

        double[] ret = new double[queries.size()];
        for(int i = 0; i<queries.size(); i++){
            List<String> query = queries.get(i);
            ret[i] = dfsCompute(query.get(0), query.get(1), 1.0, graph, new HashSet<>());
        }
        return ret;
    }

    public static Map<String, Map<String, Double>> initGraph(List<List<String>> equations, double[] values){
        Map<String, Map<String, Double>> graph = new HashMap<>();
        if(equations.size() == 0) return graph;

        for(int i = 0; i<equations.size(); i++){
            List<String> edge = equations.get(i);
            double value = values[i];
            graph.computeIfAbsent(edge.get(0), k -> new HashMap<>());
            graph.computeIfAbsent(edge.get(1), k -> new HashMap<>());
            graph.get(edge.get(0)).put(edge.get(1), value);
            graph.get(edge.get(1)).put(edge.get(0), 1.0/value);
        }
        return graph;
    }

    public static Double bfsCompute(List<String> query, Map<String, Map<String, Double>> graph){
        String start = query.get(0);
        String end = query.get(1);
        if(!graph.containsKey(start) || !graph.containsKey(end)) return -1.0;
        if(start.equals(end)) return 1.0;
        if(graph.get(start).containsKey(end)) return graph.get(start).get(end);


        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(start, 1.0));
        Set<String> used = new HashSet<>();
        while(!queue.isEmpty()){
            Pair curr = queue.poll();
            used.add(curr.node);
            Map<String, Double> children = graph.getOrDefault(curr.node, new HashMap<>());
            for(String key: children.keySet()){
                // avoid cycle
                if(!used.contains(key)) {
                    double val = curr.value * children.get(key);
                    if(key.equals(end)){
                        return val;
                    }
                    queue.offer(new Pair(key, val));
                    used.add(key);
                }

            }
        }
        return -1.0;
    }



    public static double dfsCompute(String start, String end, double res, Map<String, Map<String, Double>> graph, Set<String> used){
        if(!graph.containsKey(start) || !graph.containsKey(end)) return -1.0;
        if(start.equals(end)){
            return res;
        }

        used.add(start);
        Map<String, Double> children = graph.getOrDefault(start, new HashMap<>());
        for(String child: children.keySet()){
            if(!used.contains(child)){
                double tmp = dfsCompute(child, end, res * children.get(child), graph, used);
                if(tmp != -1.0) return tmp;
            }
        }
        used.remove(start);
        return -1.0;
    }




    public static void main(String[] args) {
        EvaluateDivision solution = new EvaluateDivision();
        List<List<String>> eqs = new ArrayList<>();
        eqs.add(Arrays.asList("a", "b"));
        eqs.add(Arrays.asList("b", "c"));

        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a","c"));
        queries.add(Arrays.asList("b","a"));
        queries.add(Arrays.asList("a","e"));
        queries.add(Arrays.asList("a","a"));
        double[] ret = solution.calcEquation(eqs, values, queries);
        for(double res : ret){
            System.out.println(res);
        }
    }
}

class Pair{
    String node;
    Double value;
    Pair(String node, Double value){
        this.node = node;
        this.value = value;
    }
}
