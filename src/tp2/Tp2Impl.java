package tp2;

import graph.Graph;

import java.util.*;

public class Tp2Impl<T> implements Tp2<T> {
    @Override
    public List<T> depth_first_search(Graph<T> graph) {
        return dfs(graph,graph.getVertexes().get(0));
    }

    private List<T> dfs(Graph<T> graph, T v){
        HashMap<T,Boolean> visited=new HashMap<>();
        for (T vertex: graph.getVertexes()) {
            visited.put(vertex,false);
        }
        Stack<T> stack = new Stack<>();
        stack.push(v);
        while (visited.containsValue(Boolean.FALSE)){
            T vertex=null;
            if(stack.isEmpty()) {
                for (T value : visited.keySet()) {
                    if (!visited.get(value)){
                        vertex = value;
                        break;
                    }
                }
            }
            else vertex= stack.pop();
            visited.replace(vertex,Boolean.TRUE);
            for (T adj: graph.getAdjacencyList(vertex)) {
                if(!visited.get(adj)&&!stack.contains(adj)) stack.push(adj);
            }
        }
        return new ArrayList<>(visited.keySet());
    }

    @Override
    public List<T> breadth_first_search(Graph<T> graph) {
        return bfs(graph,graph.getVertexes().get(0));
    }

    public List<T> bfs(Graph<T> graph, T v){
        HashMap<T, Boolean> visited = new HashMap<>();
        LinkedList<T> queue = new LinkedList<>();
        visited.put(v,true);
        queue.add(v);
        while (queue.size()!=0){
            v = queue.poll();
            Iterator<T> listOfAdyacent = graph.getAdjacencyList(v).listIterator();
            while (listOfAdyacent.hasNext()){
                T n = listOfAdyacent.next();
                if (visited.get(n) == null || visited.get(n) == false){
                    visited.put(n,true);
                    queue.add(n);
                }
            }
        }
        return new ArrayList<>(visited.keySet());
    }
    @Override
    public boolean exercise_a(Graph<T> graph, T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_b(Graph<T> graph, T v) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_c(Graph<T> graph) {
        for (T vertex: graph.getVertexes()) {
            ArrayList<T> initialPath = new ArrayList<>();
            initialPath.add(vertex);
            ArrayList<ArrayList<T>> listOfPaths= new ArrayList<>();
            possiblePaths(graph,vertex,initialPath,listOfPaths);
            for (ArrayList<T> path:listOfPaths) {
                if(path.size() > 3) return true;
            }
        }
        return false;
    }

    @Override
    public boolean exercise_d(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int exercise_e(Graph<T> graph, T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<T> exercise_f(Graph<T> graph,T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<T> exercise_g(Graph<T> graph, T v, T w) {
        ArrayList<ArrayList<T>> listOfPaths = new ArrayList<>();
        ArrayList<T> initialPath = new ArrayList<>();
        initialPath.add(v);
        possiblePaths(graph,w,initialPath,listOfPaths);
        ArrayList<T> maxSize=new ArrayList<>();
        for (ArrayList<T> path:listOfPaths) {
            if(path.size()>maxSize.size()) maxSize=path;
        }
        ArrayList<T> minSize=maxSize;
        for (ArrayList<T> path:listOfPaths) {
            if(path.size()<minSize.size()) minSize= path;
        }
        return minSize;
    }

    private void possiblePaths(Graph<T> graph,T w,ArrayList<T> path,ArrayList<ArrayList<T>> possiblePaths){
        List<T> adjacent = graph.getAdjacencyList(path.get(path.size()-1));
        for (T adj : adjacent) {
            if(adj.equals(w)){
                path.add(w);
                possiblePaths.add(path);
            }
            else if(!path.contains(adj)){
                ArrayList<T> newPath = new ArrayList<>(path);
                newPath.add(adj);
                possiblePaths(graph,w,newPath,possiblePaths);
            }
        }
    }

    @Override
    public int exercise_h(Graph<T> graph) {
        List<T> vertex = graph.getVertexes();
        if(vertex.size()==0) return 0;
        else{
            return h(graph,graph.getVertexes().get(0),1);
        }
    }

    private int h(Graph<T> graph,T v,int groups){
        HashMap<T,Boolean> visited=new HashMap<>();
        for (T vertex: graph.getVertexes()) {
            visited.put(vertex,false);
        }
        Stack<T> stack = new Stack<>();
        stack.push(v);
        while (visited.containsValue(Boolean.FALSE)){
            T vertex=null;
            if(stack.isEmpty()) {
                for (T value : visited.keySet()) {
                    if (!visited.get(value)){
                        vertex = value;
                        groups++;
                        break;
                    }
                }
            }
            else vertex= stack.pop();
            visited.replace(vertex,Boolean.TRUE);
            for (T adj: graph.getAdjacencyList(vertex)) {
                List<T> test= graph.getAdjacencyList(adj);
                if(!visited.get(adj)&&!stack.contains(adj)) stack.push(adj);
            }
        }
        return groups;
    }

    @Override
    public boolean exercise_i(Graph<T> g1, Graph<T> g2) {
        int amountOfVertex = 0;
        int amountOfEdeges = 0;
        int amountA =0;

        for (T vertex:g1.getVertexes()){
            if(g2.getVertexes().contains(vertex)){
                amountOfVertex ++;
            }
            List<T> listOfEdges = g1.getAdjacencyList(vertex);
            for (T edge:listOfEdges){
                if(g2.getAdjacencyList(vertex).contains(edge)){
                    amountOfEdeges ++;
                }
            }
        }
        System.out.println(g1.alpha());
        System.out.println(amountOfEdeges);
        System.out.println(g1.order());
        System.out.println(amountOfVertex);
        if((amountOfEdeges)/2 == g1.alpha() && amountOfVertex == g1.order()){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean exercise_j(Graph<T> g1, Graph<T> g2) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_k(Graph<T> g1) {
        int alpha = g1.alpha();
        int n= g1.order();
        int aux = n*(n-1)/2;
        boolean lazo = false;
        for (T vertex:g1.getVertexes()){
            for (T edge:g1.getAdjacencyList(vertex)){
                if (edge == vertex){
                    lazo = true;
                }
            }
        }
        if(alpha == aux && aux!=0 && !lazo) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Graph<T> exercise_l(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int exercise_m(Graph<T> graph, T v) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Map<T, Integer> exercise_n(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

}
