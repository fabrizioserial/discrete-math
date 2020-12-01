package tp3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AdjacencyMatrixPond<T> {

    private HashMap<T,Boolean> V;
    private HashMap<Integer,HashMap<Integer,Integer>> A;
    private int n;
    private int alpha;

    public AdjacencyMatrixPond(){
        this.V = new HashMap<>();
        this.A = new HashMap<>();
        this.n=0;
        this.alpha =0;
    }

    public int getN() {
        return n;
    }

    public void addEdge(T v, T w, Integer value) {

        if(hasVertex(v) && hasVertex(w)){
            int v_index = getVertexIndex(v);
            int w_index = getVertexIndex(w);
            HashMap<Integer,Integer> mapV_Content = new HashMap<>();
            if(A.containsKey(v_index)) {
                mapV_Content.putAll(A.get(v_index));
            }
            mapV_Content.put(w_index,value);
            A.put(v_index,mapV_Content);

            HashMap<Integer,Integer> mapW_Content = new HashMap<>();

            if(A.containsKey(w_index)){
                mapW_Content.putAll(A.get(w_index));
            }
            if(A.containsKey(w_index) && !getA(w).containsKey(v_index)){
                mapW_Content.put(v_index,-1);
            }
            A.put(w_index,mapW_Content);
            alpha++;
        }else{
            System.out.println("no " + v + " " + w);
        }

    }

    public int getVertexIndex(T v){
        ArrayList<T> list = new ArrayList<>(V.keySet());
        for (int i = 0; i < V.size(); i++) {
            if (list.get(i).equals(v)) return i;
        }
        return -1;
    }

    public boolean hasEdge(T v, T w) {
        if(hasVertex(v) && hasVertex(w)){
            int v_index = getVertexIndex(v);
            int w_index = getVertexIndex(w);
            return A.get(v_index).get(w_index) != -1;
        }else {
            return false;
        }
    }


    public void addVertex(T x) {
        V.put(x,true);
        n++;
    }


    public boolean hasVertex(T v) {
        return V.get(v);
    }

    public void removeVertex(T x) {
        if(V.containsKey(x)){
            V.remove(x);
        }
        n--;
    }


    public List<T> getVertexes() {
        return new ArrayList<>(V.keySet());
    }

    public HashMap<Integer,Integer> getA(T v){
        return A.get(getVertexIndex(v));
    }


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

    public int getAlpha() {
        return alpha;
    }
}
