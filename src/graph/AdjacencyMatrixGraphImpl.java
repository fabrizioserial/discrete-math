package graph;

import java.util.*;

// TODO: implement
public class AdjacencyMatrixGraphImpl<T> implements Graph<T> {

    private ArrayList<T> V;
    private HashMap<Integer,HashMap<Integer,Boolean>> A;
    private int n;
    private int alpha;

    public AdjacencyMatrixGraphImpl(){
        this.V = new ArrayList<>();
        this.A = new HashMap<>();
        this.n=0;
        this.alpha =0;
    }


    @Override
    public void addVertex(T x) {
        V.add(n,x);
        n++;
    }

    @Override
    public boolean hasVertex(T v) {
        return V.contains(v);
    }

    @Override
    public void removeVertex(T x) {
        if(V.contains(x)){
            V.remove(x);
        }
        n--;
    }

    // Agrego una arista a partir de 2 vertices. Primero verifico que este exista con hasVertex()
    // Debido a ese metodo, no es necesario controlar que el index sea -1.
    // Si no existe tiro una excepcion de argumento

    @Override
    public void addEdge(T v, T w) {
        if(hasVertex(v) && hasVertex(w)){
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

            System.out.println(v_index);
            System.out.println(w_index);

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
        for (int i = 0; i < V.size(); i++) {
            if (V.get(i).equals(v)) return i;
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
        return V;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        ArrayList<T> arrayListToReturn = new ArrayList<>();
        if(alpha != 0){
            ArrayList<T> arrayListAux = new ArrayList<T>((Collection<? extends T>) A.get(getVertexIndex(v)).keySet());
            for (int i = 0; i < V.size(); i++) {
                if(arrayListAux.contains(i)){
                    arrayListToReturn.add(V.get(i));
                }
            }
        }

        return arrayListToReturn;
    }
}
