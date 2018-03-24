import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{


    private ArrayList<T> Labels;
    private ArrayList<ArrayList<Byte>> Edges;


	/**
	 * Contructs empty graph.
	 */
    public AdjMatrix() {
        Labels = new ArrayList<T>();
        Edges = new ArrayList<ArrayList<Byte>>();
    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {
        
        if (!Labels.contains(vertLabel)){
            Labels.add(vertLabel);
            Edges.add(createEmptyArrayList(Edges.size()));

            for (ArrayList<Byte> innerEdges : Edges) {
                innerEdges.add((byte) 0);
            }

            printMatrix();

        }else{
            System.out.println("Label already Exists");
        }
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        
        int srcIndex = Labels.indexOf(srcLabel);
        int tarIndex = Labels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }

        Edges.get(srcIndex).set(tarIndex, (byte) 1);
        Edges.get(tarIndex).set(srcIndex, (byte) 1);

        printMatrix();
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        int index = Labels.indexOf(vertLabel);
        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return neighbours;
        }
        
        for (int i = 0; i < Labels.size(); i++)
            if (Edges.get(index).get(i) == 1)
                neighbours.add(Labels.get(i));


        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        
        int index = Labels.indexOf(vertLabel);

        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return;
        }

        //Remove from the edges list;
        
        Edges.remove(index);
        for (ArrayList<Byte> innerEdges : Edges) {
            innerEdges.remove(index);
        }

        Labels.remove(index);

        printMatrix();
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        
        int srcIndex = Labels.indexOf(srcLabel);
        int tarIndex = Labels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }

        Edges.get(srcIndex).set(tarIndex, (byte) 0);
        Edges.get(tarIndex).set(srcIndex, (byte) 0);


    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
        
        for (T label : Labels) {
            os.printf("%s ", label);
        }
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        
        for (int i = 0; i < Labels.size(); i++){

            for (int j = 0; j < Labels.size(); j++){
                if (Edges.get(i).get(j) == (byte) 1)
                    os.printf("%s %s\n",Labels.get(i),Labels.get(j));
            }
        }


    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()


    private void printMatrix(){

        System.out.printf("\n   ");
        for (T label : Labels) {
            System.out.printf("%s  ",label);
        }

        for (int i = 0; i < Edges.size(); i++){

            System.out.printf("\n%s |",Labels.get(i));
            for (byte edge : Edges.get(i)) {        
                System.out.printf("%s, ", edge);
            }
            System.out.printf("|");
        }
        System.out.printf("\n");
    }

    private ArrayList<Byte> createEmptyArrayList(int size){

        ArrayList<Byte> retVal = new ArrayList<Byte>();

        while(retVal.size() < size)
            retVal.add((byte)0);

        return retVal;
    }
    
} // end of class AdjMatrix