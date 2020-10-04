package graph;

import java.util.*;

// TODO: implement
public class AdjacencyMatrixGraphImpl<T> implements Graph<T> {

    private HashMap<T,Boolean> V;
    private HashMap<Integer,HashMap<Integer,Boolean>> A;
    private int n;
    private int alpha;


    public AdjacencyMatrixGraphImpl(){
        this.V = new HashMap<>();
        this.A = new HashMap<>();
        this.n=0;
        this.alpha =0;
    }


    @Override
    public void addVertex(T x) {
        V.put(x,true);
        n++;
    }


    @Override
    public boolean hasVertex(T v) {
        return V.get(v);
    }

    @Override
    public void removeVertex(T x) {
        if(V.containsKey(x)){
            V.remove(x);
        }
        n--;
    }


    @Override
    public void addEdge(T v, T w) {

        if(!V.containsKey(v) || !V.containsKey(w)){
            if(!V.containsKey(v)){
                V.put(v,false);
            }else{
                V.put(w,false);
            }
        }else if(hasVertex(v) && hasVertex(w) && V.get(v) && V.get(w)){
            int v_index = getVertexIndex(v);
            int w_index = getVertexIndex(w);
            HashMap<Integer,Boolean> mapV_Content = new HashMap<>();
            if(A.containsKey(v_index)){
                mapV_Content.putAll(A.get(v_index));
            }
            mapV_Content.put(w_index,true);
            A.put(v_index,mapV_Content);

            HashMap<Integer,Boolean> mapW_Content = new HashMap<>();
            if(A.containsKey(w_index)){
                mapW_Content.putAll(A.get(w_index));
            }
            mapW_Content.put(v_index,true);
            A.put(w_index,mapW_Content);

            alpha++;
        }
    }


    @Override
    public void removeEdge(T v, T w) {
        if(hasVertex(v) && hasVertex(w)){
            int v_index = getVertexIndex(v);
            int w_index = getVertexIndex(w);
            A.get(v_index).put(w_index,false);
            A.get(w_index).put(v_index,false);

            alpha --;
        }else{
            throw new IllegalArgumentException("One of the vertex, doesnt exist");
        }

    }

    private int getVertexIndex(T v){
        ArrayList<T> list = new ArrayList<>(V.keySet());
        for (int i = 0; i < V.size(); i++) {
            if (list.get(i).equals(v)) return i;
        }
        return -1;
    }

    @Override
    public boolean hasEdge(T v, T w) {
        if(hasVertex(v) && hasVertex(w)){
            int v_index = getVertexIndex(v);
            int w_index = getVertexIndex(w);
            return A.get(v_index).get(w_index);
        }else {
            return false;
        }
    }

    @Override
    public int order() {
        return n;
    }

    @Override
    public int alpha() {
        return alpha;
    }

    @Override
    public List<T> getVertexes() {
        return new ArrayList<>(V.keySet());
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        ArrayList<T> arrayListToReturn = new ArrayList<>();
        ArrayList<T> arrayList = new ArrayList<>(V.keySet());
        if(alpha != 0 && A.containsKey(getVertexIndex(v))){
            ArrayList<T> arrayListAux = new ArrayList<T>((Collection<? extends T>) A.get(getVertexIndex(v)).keySet());
            for (int i = 0; i < V.size(); i++) {
                if(arrayListAux.contains(i)){
                    arrayListToReturn.add(arrayList.get(i));
                }
            }
        }

        return arrayListToReturn;
    }
}
