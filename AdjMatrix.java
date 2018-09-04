import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 *
 * @author Timothy Novice and Joshua Reason, 2018.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{


    //Arrays to hold data
    private ArrayList<T> Labels;
    private ArrayList<ArrayList<Byte>> Edges;


	/**
	 * Contructs empty graph.
	 */
    public AdjMatrix() {
        //instantiate arays
        Labels = new ArrayList<T>();
        Edges = new ArrayList<ArrayList<Byte>>();
    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {
        
        //Check if label doesn't exist in array already
        if (!Labels.contains(vertLabel)){

            //add to Label Array
            Labels.add(vertLabel);

            //Adds a new inner array size of the outer array (Adds new Row)
            Edges.add(createEmptyArrayList(Edges.size()));

            //loop through each of the inner arrays and adds a new entry to it (adds new colum)
            for (ArrayList<Byte> innerEdges : Edges) {
                innerEdges.add((byte) 0);
            }

            printMatrix();

        }else{ //else print that the vertex already exits
            System.out.println("Label already Exists");
        }
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        
        //checks if labels exist in graph
        int srcIndex = Labels.indexOf(srcLabel);
        int tarIndex = Labels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }

        //sets rows and columns in graph to one
        Edges.get(srcIndex).set(tarIndex, (byte) 1);
        Edges.get(tarIndex).set(srcIndex, (byte) 1);

        printMatrix();
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {

        //instatiate array to return
        ArrayList<T> neighbours = new ArrayList<T>();
        
        //get index of label 
        int index = Labels.indexOf(vertLabel);

        //if doesn't exist return
        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return neighbours;
        }
        
        //iterate through row of index adding edges
        for (int i = 0; i < Labels.size(); i++)
            if (Edges.get(index).get(i) == 1)
                neighbours.add(Labels.get(i));

        //return
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        
        //get index of label
        int index = Labels.indexOf(vertLabel);

        //if doesn't exist return
        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return;
        }
       
        //remove outer array of vert (row)
        Edges.remove(index);

        //iterate through inner arrays and removes vert (column)
        for (ArrayList<Byte> innerEdges : Edges) {
            innerEdges.remove(index);
        }

        //remove label from label array
        Labels.remove(index);

        printMatrix();
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        
        //checks if labels exist in graph
        int srcIndex = Labels.indexOf(srcLabel);
        int tarIndex = Labels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }

        //set rows and columns to zero
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
        

        //T[] visitedLabels = new T[Labels.size()];

        boolean[] visited = new boolean[Labels.size()];
        int[] distances = new int[Labels.size()];
        
        Queue <T> queue = new LinkedList<T>();

        T curVert = vertLabel1;
        int curIndex = Labels.indexOf(vertLabel1);

        int wantedIndex = Labels.indexOf(vertLabel2);


        if (curIndex == wantedIndex)
            return 0;

        while (curVert != null){

            if (!visited[curIndex]){
                visited[curIndex] = true;

                for(T nVert : neighbours(curVert)){
                    int nIndex = Labels.indexOf(nVert);

                    if (visited[nIndex])
                        continue;

                    System.out.printf("  - Checking %s: %s", curVert,nVert);

                    if (nIndex == wantedIndex){
                        System.out.printf("  (%s = %s)\n",nVert, vertLabel2);
                        return distances[curIndex] + 1;
                    }else {
                        System.out.printf("  (%s != %s)\n",nVert, vertLabel2);
                        distances[nIndex] = distances[curIndex] + 1;
                        queue.add(nVert);
                    }
                } //end For
            } //end (if !visited)
        
            curVert = queue.poll();
            curIndex = Labels.indexOf(curVert);
        }//end While

        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()



    public void printMatrix(){

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
