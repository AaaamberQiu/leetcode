package Grind75.array;

import java.util.*;

public class AccountsMerge_721 {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        fillGraph(accounts, graph, map);

        List<List<String>> ret = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for(String email: graph.keySet()){
            if(visited.contains(email)) continue;
            TreeSet<String> connected = new TreeSet<>();
            dfs(graph, visited, connected, email);

            List<String> res = new ArrayList<>();
            res.add(map.get(email));
            res.addAll(connected);
            ret.add(res);
        }
        return ret;
    }


    private void fillGraph(List<List<String>> accounts,  Map<String, Set<String>> graph, Map<String, String> map){
        for(List<String> account: accounts){
            String name = account.get(0);
            List<String> emails = getEmails(account);

            for(int i = 0; i<emails.size(); i++){
                map.put(emails.get(i), name);
                graph.computeIfAbsent(emails.get(i), k -> new HashSet<>());
                if(i - 1 < 0) continue;
                graph.get(emails.get(i)).add(emails.get(i-1));
                graph.get(emails.get(i-1)).add(emails.get(i));
            }
        }
    }


    private List<String> getEmails(List<String> info){
        if(info.size() <= 1) return new ArrayList<>();
        return info.subList(1, info.size());
    }

    private void dfs(Map<String, Set<String>> graph, Set<String> visited, TreeSet<String> temp, String node){
        if(visited.contains(node)) return;

        visited.add(node);
        temp.add(node);
        for(String next: graph.get(node)){
            dfs(graph, visited, temp, next);
        }
    }


    public List<List<String>> accountsMerge_unionFind(List<List<String>> accounts) {
        // email -> user
        Map<String, String> map = new HashMap<>();
        // email -> parent email
        Map<String, String> parents = new HashMap<>();
        // user -> all emails
        Map<String, TreeSet<String>> unions = new HashMap<>();

        for(List<String> account: accounts){
            String name = account.get(0);
            for(int i = 1; i<account.size(); i++){
                String email = account.get(i);
                map.put(email, name);
                // init parents
                parents.put(email, email);
            }
        }

        // connect
        for(List<String> account: accounts){
            String parent = find(parents, account.get(1));
            for(int i = 2; i<account.size(); i++){
                // compress the path of account(i), then link to parent
                parents.put(find(parents, account.get(i)), parent);
            }
        }

        // union
        for(List<String> account: accounts){
            String parent = find(parents, account.get(1));
            unions.computeIfAbsent(parent, k -> new TreeSet<>());
            for(int i = 1; i<account.size(); i++){
                unions.get(parent).add(account.get(i));
            }
        }

        List<List<String>> ret = new ArrayList<>();
        for (String parent : unions.keySet()) {
            List<String> res = new ArrayList<>();
            res.add(map.get(parent));
            res.addAll(unions.get(parent));
            ret.add(res);
        }
        return ret;

    }

    private String find(Map<String, String> parents, String s){
        return parents.get(s) == s? s: find(parents, parents.get(s));
    }

}
