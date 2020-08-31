package tp1;

import graph.Graph;

import java.util.ArrayList;
import java.util.List;

// TODO: implement
public class Tp1Impl<T> implements Tp1<T> {
    @Override
    public void exercise_a(Graph<T> graph) {
        //Primero muestro los vertices
        System.out.println("Vertex:");
        ArrayList<T> vertex = (ArrayList<T>) graph.getVertexes();
        for (int i = 0; i < graph.order(); i++) {
            if(vertex.get(i) != null){
                System.out.print(vertex.get(i) + ", ");
            }
        }
        //Hasta aca es de Orden n
        //Ahora muestro cada vertice con sus aristas
        System.out.println("Vertex by Edges:");
        for (int i = 0; i < graph.order(); i++) {
            if(vertex.get(i)!=null){
                System.out.println("The edge of " + vertex.get(i));
                ArrayList<T> edgesList = (ArrayList<T>) graph.getAdjacencyList(vertex.get(i));
                if(!edgesList.isEmpty()){
                    for (T t : edgesList) {
                        if(edgesList.size() > 1){
                            System.out.print(t + " ,");
                        }else{
                            System.out.println(t);
                        }

                    }
                }else{
                    System.out.println("No edges");
                }
            }
            System.out.println("No edges");
        }

        //Orden n^2
    }

    @Override
    public int exercise_b(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<T> exercise_c(Graph<T> graph) {
        ArrayList<T> sameEdge=new ArrayList<>();
        ArrayList<T> vertex= (ArrayList<T>) graph.getVertexes();
        for (T element:vertex) {
            if(graph.hasEdge(element,element)) sameEdge.add(element);
        }
        return sameEdge;

        //Orden n^2
    }

    @Override
    public boolean exercise_d(Graph<T> graph, T vertex) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int exercise_e(Graph<T> graph) {
        ArrayList<T> vertexList = (ArrayList<T>) graph.getVertexes();
        int cant = 0;
        for (int i = 0; i < graph.order(); i++) {
            if (vertexList.get(i) != null) {
                ArrayList<T> edgeOfAnVertex = (ArrayList<T>) graph.getAdjacencyList(vertexList.get(i));
                if (edgeOfAnVertex.size() == 0){
                    cant++;
                }
            }
        }
        return cant;
        // Orden n
    }

    @Override
    public List<T> exercise_f(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Graph<T> exercise_g(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int[][] exercise_h(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int[][] exercise_i(Graph<T> graph) {
        int[][] incidentMatrix= new int[graph.order()][graph.alpha()];
        ArrayList<T> vertex= (ArrayList<T>) graph.getVertexes();
        ArrayList<ArrayList<T>> adjacency=new ArrayList<>();
        for (T element:vertex) {
            for (T connectedVertex:graph.getAdjacencyList(element)) {
                ArrayList<T> connection=new ArrayList<>();
                connection.add(element);
                connection.add(connectedVertex);
                boolean condition=true;
                for (ArrayList<T> countedConnections:adjacency) {
                    if(countedConnections.containsAll(connection)) condition=false;
                }
                if(condition) adjacency.add(connection);
            }
        }
        for (int i = 0; i < adjacency.size(); i++) {
            int a=vertex.indexOf(adjacency.get(i).get(0));
            int b=vertex.indexOf(adjacency.get(i).get(1));
            incidentMatrix [vertex.indexOf(adjacency.get(i).get(0))][i]=1;
            incidentMatrix [vertex.indexOf(adjacency.get(i).get(1))][i]=1;
        }
        return incidentMatrix;
        //Orden n^3
        //La matriz de incidencia armada no coincide con la del test porque las columnas quedan rotadas
    }
}
