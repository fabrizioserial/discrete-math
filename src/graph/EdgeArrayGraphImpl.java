package graph;

import java.util.ArrayList;
import java.util.List;

// TODO: implement
public class EdgeArrayGraphImpl<T> implements Graph<T> {

    private final ArrayList<T> vertex= new ArrayList<>();
    private int alfa=0;
    private int n=0;

    private final List<T[]> edgeArray=new ArrayList<>();

    @Override
    public void addVertex(T x) {
        vertex.add(x);
        n++;
    }

    @Override
    public boolean hasVertex(T v){
        return vertex.contains(v);
    }

    @Override
    public void removeVertex(T x) {
        if(!hasVertex(x)){
            throw new RuntimeException();
        }
        else{
            vertex.remove(x);
            n--;
            edgeArray.removeIf(node -> node[0].equals(x));
            edgeArray.removeIf(node -> node[1].equals(x));
        }
    }

    @Override
    public void addEdge(T v, T w) {
        if(!hasVertex(v)||!hasVertex(w)) return;
        else{
            T[] node =(T[])new Object[2];
            node[0]=v;
            node[1]=w;
            edgeArray.add(node);
            alfa++;
        }
    }

    @Override
    public void removeEdge(T v, T w) {
        edgeArray.removeIf(node -> node[0].equals(v) && node[1].equals(w) || node[0].equals(w) && node[1].equals(v));
        alfa--;
    }

    @Override
    public boolean hasEdge(T v, T w) {
        for (T[] node:edgeArray) {
            if ((node[0].equals(v)&&node[1].equals(w))||(node[0].equals(w)&&node[1].equals(v))) return true;
        }
        return false;
    }

    @Override
    public int order() {
        return n;
    }

    @Override
    public int alpha() {
        return alfa;
    }

    @Override
    public List<T> getVertexes() {
        return vertex;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        List<T> adjacencyList=new ArrayList<>();
        for (T[] node:edgeArray) {
            if(node[0].equals(v)){
                adjacencyList.add(node[1]);
            }
            else if (node[1].equals(v)){
                adjacencyList.add(node[0]);
            }
        }
        return adjacencyList;
    }
}
