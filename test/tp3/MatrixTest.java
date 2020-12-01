package tp3;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class MatrixTest {
    @Test
    public void addVertexTest(){
        AdjacencyMatrixPond<Integer> matrix = new AdjacencyMatrixPond<Integer>();
        matrix.addVertex(1);
        matrix.addVertex(2);

        Assert.assertEquals(2,matrix.getN());
    }
    @Test
    public void addEdgeTest(){
        AdjacencyMatrixPond<Integer> matrix = new AdjacencyMatrixPond<Integer>();
        matrix.addVertex(1);
        matrix.addVertex(2);

        matrix.addEdge(1,1, -1);
        matrix.addEdge(2,2, -1);

        matrix.addEdge(1,2,10);
        matrix.addEdge(2,1, -1);

        Assert.assertEquals(4,matrix.getAlpha());
    }

    @Test
    public void getEdgeListByAVertex(){
        AdjacencyMatrixPond<Integer> matrix = new AdjacencyMatrixPond<Integer>();
        matrix.addVertex(1);
        matrix.addVertex(2);

        matrix.addEdge(1,1, -1);
        matrix.addEdge(1,2,10);

        Assert.assertTrue(!matrix.getA(1).isEmpty());
    }


}
