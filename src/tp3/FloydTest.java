package tp3;


import java.util.ArrayList;
import java.util.HashMap;

public class FloydTest {

    public static void main(String[] args) {
        AdjacencyMatrixPond<Integer> grafo = new AdjacencyMatrixPond<Integer>();
        grafo.addVertex(1);
        grafo.addVertex(2);
        grafo.addVertex(3);
        grafo.addVertex(4);
        grafo.addEdge(1,1, -1);
        grafo.addEdge(2,2, -1);
        grafo.addEdge(3,3, -1);
        grafo.addEdge(4,4, -1);

        grafo.addEdge(1,2,10);
        grafo.addEdge(2,1, -1);

        grafo.addEdge(3,1,2);
        grafo.addEdge(1,3, 6);
        grafo.addEdge(3,2,3);
        grafo.addEdge(2,3, 2);

        grafo.addEdge(1,4,1);
        grafo.addEdge(4,1, 6);
        grafo.addEdge(3,4,12);
        grafo.addEdge(4,3, 8);
        grafo.addEdge(4,2,1);
        grafo.addEdge(2,4, -1);

        HashMap<Integer,Integer> flo = (HashMap<Integer,Integer>) grafo.getA(1);

        flo.size();
        Floyd floyd = new Floyd(grafo);

        AdjacencyMatrixPond<Integer> Resultante = (AdjacencyMatrixPond<Integer>) floyd.FloydAlgorithm();

    }

}
