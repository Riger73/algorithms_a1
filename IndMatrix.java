import java.io.*;
import java.util.*;


/**
 * Incidence matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class IndMatrixTest <T extends Object> implements FriendshipGraph<T> {
    
    private ArrayList<T> incidenceMatrix;
    private ArrayList<ArrayList<Integer>> rows;
    private ArrayList<ArrayList<Integer>> cols;
    
    private int edge;
    
    public IndMatrixTest() {
        rows = new ArrayList<ArrayList<Integer>>();
        cols = new ArrayList<ArrayList<Integer>>();
        incidenceMatrix = new ArrayList<T>();
    }
     
    public void addEdge(T srcLabel, T tarLabel) {
        int srcIndex = incidenceMatrix.indexOf(srcLabel);
        int tarIndex = incidenceMatrix.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }

        cols.get(srcIndex).set(tarIndex, (Integer) 1);
        cols.get(tarIndex).set(srcIndex, (Integer) 1);

        display();

    }

    public int getEdge(int cols, int rows) 
    {
        try {
            return incidenceMatrix.indexOf(rows);
        } catch (ArrayIndexOutOfBoundsException index) {
            System.out.println("The vertices does not exists");
        }
        return -1;
    }
    
    private void display() {
        System.out.printf("\n   "); 
        // Print Column (edge) headings
        for (T label : incidenceMatrix) {
            System.out.printf("E%s  ", label);
        }
        // Print Row (vertices) headings
        for (int i = 0; i < rows.size(); i++){
            System.out.printf("\nV%s |", incidenceMatrix.get(i));
            // Print result - 0 if not incident and 1 if incident     
            for (Integer edge : rows.get(i)) {      
                System.out.printf("%s, ", neighbours(incidenceMatrix.get(edge)).contains(edge) ? 1 : 0);
            }
            System.out.printf("|");
            
        } 
        System.out.printf("\n"); 
    }

    @Override
    public void addVertex(T vertLabel) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ArrayList<T> neighbours(T vertLabel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeVertex(T vertLabel) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeEdge(T srcLabel, T tarLabel) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void printVertices(PrintWriter os) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void printEdges(PrintWriter os) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        // TODO Auto-generated method stub
        return 0;
    } 
        
}
