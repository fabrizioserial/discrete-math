package tp3;

public class Floyd {
    private static final int INFINITO = -1;
    private final AdjacencyMatrixPond grafo;
    private final int cantNodos;
    private AdjacencyMatrixPond costosMinimos;


    public Floyd(AdjacencyMatrixPond grafoP) {
        this.grafo = grafoP;
        this.cantNodos = grafoP.getN();
        this.costosMinimos = grafoP;
    }

    public AdjacencyMatrixPond FloydAlgorithm(){

        Integer anterior, ik, kj, actual, minimo;
        for (int k = 1; k < this.cantNodos; k++) {
            for (int i = 1; i < this.cantNodos; i++) {
                for (int j = 1; j < this.cantNodos; j++) {

                    if (i != j && k != i && k != j) {

                        anterior = (Integer) costosMinimos.getA(i).get(costosMinimos.getVertexIndex(j));

                        ik = (Integer) costosMinimos.getA(i).get(costosMinimos.getVertexIndex(k));
                        kj = (Integer) costosMinimos.getA(k).get(costosMinimos.getVertexIndex(j));


                        if (ik == INFINITO || kj == INFINITO) {
                            actual = INFINITO;
                        } else {
                            actual = ik + kj;
                        }

                        if (actual != INFINITO && (actual < anterior || anterior == INFINITO)) {
                            minimo = actual;
                        } else {
                            minimo = anterior;
                        }
                        costosMinimos.addEdge(i,j,minimo);
                    }
                }
            }
        }

        return costosMinimos;
    }
}
