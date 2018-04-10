import java.io.*;
import java.util.*;


/**
 * Incidence matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
 
public class IndMatrix <T extends Object> implements FriendshipGraph<T>
{

    private static final int VERTSIZE = 100;
    private static final int EDGESIZE = 100;
    
    Object[][] edges;
    Object[] vertices;
    int vIndexFree = -1;
    
    /**
     * Constructs empty graph.
     */
    public IndMatrix() {
        // Implement me!
        // Initialize empty vertices
        vertices = new Object[VERTSIZE];
        //array of array of edge
        edges = new Object[vertices.length][];
    } // end of IndMatrix()
    
    /**
     * Allows the vertices array to grow "dynamically"
     */
    private void growVertArray(){
        Object[][] oldEdges = edges;
        Object[] oldVertices = vertices;
        //create a bigger array of vertLabel
        vertices = new Object[vertices.length + VERTSIZE];
        edges = new Object[vertices.length][];
        //copy vertLabel data from old to new
        System.arraycopy(oldVertices, 0, vertices, 0, oldVertices.length);
        System.arraycopy(oldEdges, 0, edges, 0, oldEdges.length);
        vIndexFree = oldVertices.length;
    }
    /**
     * Allows the edge array to grow "dynamically"
     * @param vertLabelIndex
     */
    private int growEdgeArray(int vertLabelIndex){
        int eIndexFree = -1;
        if(vertLabelIndex < vertices.length){
            Object[] oldEdgeArray = edges[vertLabelIndex];
            if(oldEdgeArray == null){
                edges[vertLabelIndex] = new Object[EDGESIZE];
                eIndexFree = 0;
            }else{
                edges[vertLabelIndex] = new Object[edges[vertLabelIndex].length + EDGESIZE];
                System.arraycopy(oldEdgeArray, 0, edges[vertLabelIndex], 0, oldEdgeArray.length);
                eIndexFree = oldEdgeArray.length;
            }
            
        }
        return eIndexFree;
    }
    
    private boolean isVertArrayFull(){
        boolean full = true;
        // check the removed Vertex Index
        if(vIndexFree != -1){
            full = false;
        }else{
            //loop the array of vertices and check for nulls
            for(int i = 0 ; i < vertices.length ; i ++){
                Object o = vertices[i];
                if(o == null){
                    full = false;
                    vIndexFree = i;
                    break;
                }
            }
        }
        return full;
    }
    
    /**
     * index for new edges
     * @param vertLabel
     * @return
     */
    private int getFreeEdgeIndex(int vertLabel,Object newEdge){
        int edgeIndex = -1;
        boolean exist = false;
        if(vertLabel < vertices.length){
            Object[] edgeConnect = edges[vertLabel]; 
            if(edgeConnect != null){
                for(int i = 0 ; i < edgeConnect.length ; i ++){
                    if(edgeIndex == -1 && edgeConnect[i] == null){
                        edgeIndex = i;
                    }
                    if(edgeConnect[i] != null && edgeConnect[i].equals(newEdge)){
                        exist = true;
                        break;
                    }
                }
            }
            //checks for empty then adds to the array
            if(edgeIndex == -1 && !exist) edgeIndex = growEdgeArray(vertLabel);
        }
        return edgeIndex;
    }
    
    private int getEdgeIndex(int vertLabelIndex , Object edge){
        int index = -1;
        Object[] edgeConnect = edges[vertLabelIndex]; 
        if(edgeConnect != null){
            for(int i = 0; i < edgeConnect.length; i ++){
                Object currentEdge = edgeConnect[i];
                if(currentEdge != null && currentEdge.equals(edge)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    
    /**
     * get the index of vertLabel in array
     * @param v
     * @return
     */
    private int vertIndex(Object v){
        int index = -1;
        for(int i = 0 ; i < vertices.length ; i ++){
            Object o = vertices[i];
            if(o != null && o.equals(v)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    public void addVertex(T vertLabel) {
        // Implement me!
        // checks for empty then adds to the array
        if(vertLabel !=null && vertIndex(vertLabel) == -1){
            if(isVertArrayFull()){
                growVertArray();
            }
            vertices[vIndexFree] = vertLabel;
            edges[vIndexFree] = new Object[VERTSIZE];
            vIndexFree = -1;
            
            display();
        } else {
              System.out.println("Label already Exists");
        }
        
    } // end of addVertex()
    
    
    public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
        // finds valid vertLabel nodes for edge
        if(srcLabel != null && tarLabel !=null && !srcLabel.equals(tarLabel)){
            int srcLabelIndex = vertIndex(srcLabel);
            int tarLabelIndex = vertIndex(tarLabel);
            if(srcLabelIndex != -1 && tarLabelIndex != -1){
                // add new edge to edge array
                int edgeIndex1 = getFreeEdgeIndex(srcLabelIndex,tarLabel);
                // Checks for duplicate edges
                if(edgeIndex1 == -1) return;
                    int edgeIndex2 = getFreeEdgeIndex(tarLabelIndex,srcLabel);
                if(edgeIndex2 == -1 )return;
                    edges[srcLabelIndex][edgeIndex1] = tarLabel;
                    edges[tarLabelIndex][edgeIndex2] = srcLabel;
                   
                    display();
            }else{
                throw new IllegalArgumentException();
            }
            
        }
    } // end of addEdge()
    

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        // Implement me!
        int vertIndex;
        // checks if vertLabel exists
        if(vertLabel != null && ((vertIndex=this.vertIndex(vertLabel)) != -1)){
            // identify neighbours and then add them to neighbours ArrayList
            for(int i = 0; i < edges[vertIndex].length; i ++){
                @SuppressWarnings("unchecked")
                T t = (T)edges[vertIndex][i];
                if(t != null){
                    neighbours.add(t);
                }
            }
        }else{
            throw new IllegalArgumentException();
        }
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        // Implement me!
        int vertIndex;
        // check if vertLabel exists
        if(vertLabel!=null && ((vertIndex=this.vertIndex(vertLabel)) != -1)){
            // remove vertLabel
            vertices[vertIndex] = null;
            Object[] oldEdges = edges[vertIndex];
            edges[vertIndex] = null;
            // find and remove all edges incident the vertLabel
            for(int i = 0 ; i < oldEdges.length ; i ++){
                if(oldEdges[i]!=null){
                    int tempIndex = vertIndex(oldEdges[i]);
                    int tempIndex2 = getEdgeIndex(tempIndex,vertLabel);
                    if(tempIndex!=-1 && tempIndex2!=-1){
                        edges[tempIndex][tempIndex2] = null;
                    }
                }
            }
            this.vIndexFree = vertIndex;
            
            display();
        
        }else{
            throw new IllegalArgumentException();
        }
    } // end of removeVertex()
    
    
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
        // checks for removal conditions: null & equal
        if(srcLabel != null && tarLabel !=null && !srcLabel.equals(tarLabel)){
            // Checks that edge & nodes exit
            int srcLabelIndex = vertIndex(srcLabel);
            int tarLabelIndex = vertIndex(tarLabel);
            // Checks if vertices are present in array: removeable
            if(srcLabelIndex != -1 && tarLabelIndex != -1){
                int edgeIndex1 = getEdgeIndex(srcLabelIndex, tarLabel);
                if(edgeIndex1 == -1) return; 
                int edgeIndex2 = getEdgeIndex(tarLabelIndex,srcLabel);
                if(edgeIndex2 == -1 )return; 
                // Remove edges
                edges[srcLabelIndex][edgeIndex1] = null;
                edges[tarLabelIndex][edgeIndex2] = null;
            
                display();
            
            }else{
                throw new IllegalArgumentException();
            }
            
        }
    } // end of removeEdges()
    
    /**
     * print all vertices
     */
    public void printVertices(PrintWriter os) {
        // Implement me!
        // iterate through array elements and print
        for(int i = 0 ; i < vertices.length ; i ++){
            Object vertLabel = vertices[i];
            if(vertLabel != null){
                os.print(vertLabel.toString() + " ");
            }
        }
        os.println();
        
    } // end of printVertices()
    
    /**
     * print all edges
     */
    public void printEdges(PrintWriter os) {
        // Implement me!
        // iterate through array elements and print
        for(int i = 0; i < vertices.length; i ++){
            Object vertLabel = vertices[i];
            if(vertLabel != null && edges[i] != null){
                for(int j = 0; j < edges[i].length; j ++){
                    if(edges[i][j]!=null){
                        os.println(vertLabel.toString() + " " + edges[i][j].toString());
                    }
                }
            }
        }
    
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        // Implement me!
        int vertex2Index;
        if(vertLabel2 != null && (vertex2Index= vertIndex(vertLabel2)) != -1 && edges[vertex2Index] != null){
            
            int distance = 0;
            LinkedList<T> visitedLinkedList = new LinkedList<T>();
            LinkedList<T> unvisitLinkedList = new LinkedList<T>();
            unvisitLinkedList.add(vertLabel1);
            while(unvisitLinkedList.size() != 0){   
                LinkedList<T> newUnvisitLinkedList = new LinkedList<T>();
                // check through list unvisited, add all vertices neighbours into the sameStepChildrenList
                for(int m = 0 ; m < unvisitLinkedList.size() ; m++){
                    T current = unvisitLinkedList.get(m);
                    
                    //if find any vertex equals the 'end' vertex ,the shortest path has been found
                    if(vertLabel2.equals(current)){
                        return distance;
                    }
                    int curIndex = vertIndex(current);
                    if(curIndex == -1) continue;
                    Object[] edgeConnect = this.edges[curIndex];
                    if(edgeConnect == null) continue;
                    for(int n = 0 ; n < edgeConnect.length; n ++){
                        @SuppressWarnings("unchecked")
                        T vertex = (T)edgeConnect[n];
                        if(vertex != null && !visitedLinkedList.contains(vertex) && !newUnvisitLinkedList.contains(vertex)){
                            newUnvisitLinkedList.add(vertex);
                        }
                    }
                    visitedLinkedList.add(current);
                }
                /** insert new unvisited into current position in list **/
                unvisitLinkedList = newUnvisitLinkedList;
                newUnvisitLinkedList = null;
                distance++;/** update step at very beginning of sub-loop **/
            }         
        }
        // if we reach this point, source and target are disconnected
        return disconnectedDist;        
    } // end of shortestPathDistance()  
    
    private void display(){
        System.out.printf("\n   ");
        for (int i = 0; i < verticies.length; i++) {
            System.out.printf("%s  ", verticies[i]);
        }

        for (int i = 0; i < edges.length; i++){
            System.out.printf("\n%s |", edges[][i]);
            for (j = 0; j < vertices.length; j++) {
                System.out.printf("%s, ", edges[j][]);
            }
            System.out.printf("|");
            System.out.printf("|");
        }
        System.out.printf("\n");
    }
  
} // end of class IndMatrix

