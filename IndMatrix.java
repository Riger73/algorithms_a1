import java.io.*;
import java.util.*;


/**
 * Incidence matrix implementation for the FriendshipGraph interface.
 *
 * @author Timothy Novice and Joshua Reason 2018.
 */
public class IndMatrix <T extends Object> implements FriendshipGraph<T>
{

    //Arrays to hold data
    private ArrayList<T> vertLabels;
    private ArrayList<ArrayList<Byte>> relations;
    int edgeCount;


	/**
	 * Contructs empty graph.
	 */
    public IndMatrix() {

        vertLabels = new ArrayList<T>();
        relations = new ArrayList<ArrayList<Byte>>();


    } // end of IndMatrix()
    
    
    public void addVertex(T vertLabel) {
        
        if (vertLabels.contains(vertLabel))
            return;

        vertLabels.add(vertLabel);

        //adds a new row for vertex with the size of edges
        relations.add(createEmptyArrayList(edgeCount));

        System.out.printf("\nAdding Vertex: %s ",vertLabel);

        //printMatrix();


    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        
        //checks if labels exist in graph
        int srcIndex = vertLabels.indexOf(srcLabel);
        int tarIndex = vertLabels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists\n", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }

        for (int i = 0; i < edgeCount; i++){

           byte edge1 = relations.get(srcIndex).get(i);
           byte edge2 = relations.get(tarIndex).get(i);

           if (edge1 == 1 && edge2==1){
               System.out.printf("\nEdge (%s,%s) already exists\n",srcLabel,tarLabel);
               return;
           }
        }

        edgeCount++;

        for(int i = 0; i < vertLabels.size(); i ++){

            if (i == srcIndex || i == tarIndex)
                relations.get(i).add((byte) 1);
            else
                relations.get(i).add((byte) 0);

        }

        System.out.printf("\nAdding Edge: (%s,%s) ",srcLabel,tarLabel);


        //printMatrix();
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        //get index of label 
        int index = vertLabels.indexOf(vertLabel);

        //if doesn't exist return
        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return neighbours;
        }

        for (int i = 0; i < edgeCount; i++){

            byte edge = relations.get(index).get(i);

            if (edge == 1){

                for (int j = 0; j < vertLabels.size(); j++){
                    
                    if (relations.get(j).get(i) == 1 && j != index){
                        neighbours.add(vertLabels.get(j));
                        break;
                    }
                }
            }


        } 
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        
         //get index of label
         int index = vertLabels.indexOf(vertLabel);

         //if doesn't exist return
         if (index == -1){
             System.out.printf("\n%s does not Exists\n",vertLabel);
             return;
         }


         //loop through edges and remove any that include i;
         for (int i = 0; i < edgeCount; i++){

            byte edge = relations.get(index).get(i);

            if (edge == 1){

                for (int j = 0; j < vertLabels.size(); j++){
                    relations.get(j).remove(i);
                }
                i--;
                edgeCount--;
            }
        }
        relations.remove(index);
        vertLabels.remove(vertLabel);

        printMatrix();
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        
        //checks if labels exist in graph
        int srcIndex = vertLabels.indexOf(srcLabel);
        int tarIndex = vertLabels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }

        for (int i = 0; i < edgeCount; i++){

            byte edge1 = relations.get(srcIndex).get(i);
            byte edge2 = relations.get(tarIndex).get(i);
 
            if (edge1 == 1 && edge2==1){
                
                for (int j = 0; j < vertLabels.size(); j++){
                    relations.get(j).remove(i);
                }
                i--;
                edgeCount--;
            }
        }

            printMatrix();
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
        
        for (T vert : vertLabels) {
            os.printf("%s ", vert);
        }


    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        

        for (int i = 0; i < edgeCount; i++){

            T vert1 = null;
            T vert2 = null;
            
            for (int j = 0; j < relations.size(); j++){
                
                if (vert1 == null && relations.get(j).get(i)==1){
                    vert1 = vertLabels.get(j);
                }else if (vert2 == null && relations.get(j).get(i)==1){
                    vert2 = vertLabels.get(j);
                    break;
                }
            }
            os.printf("%s %s\n",vert1,vert2);
            os.printf("%s %s\n",vert2,vert1);
        }
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        
        boolean[] visited = new boolean[vertLabels.size()];
        int[] distances = new int[vertLabels.size()];
        
        Queue <T> queue = new LinkedList<T>();

        T curVert = vertLabel1;
        int curIndex = vertLabels.indexOf(vertLabel1);

        int wantedIndex = vertLabels.indexOf(vertLabel2);


        if (curIndex == wantedIndex)
            return 0;

        while (curVert != null){

            if (!visited[curIndex]){
                visited[curIndex] = true;

                for(T nVert : neighbours(curVert)){
                    int nIndex = vertLabels.indexOf(nVert);

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
            curIndex = vertLabels.indexOf(curVert);
        }//end While


        return disconnectedDist;    	
    } // end of shortestPathDistance()


    public void printMatrix(){

        System.out.printf("\n   ");

        for (int i = 1; i <= edgeCount; i ++)
            System.out.printf("e%d ",i);

        for (int i = 0; i < relations.size(); i++){

            System.out.printf("\n%s |",vertLabels.get(i));
            for (byte relation : relations.get(i)) {        
                System.out.printf("%s, ", relation);
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
    
} // end of class IndMatrix
