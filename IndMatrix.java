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

    private ArrayList<T> Labels;
    private ArrayList<T> Verts;
    private ArrayList<T> Edges;
    private ArrayList<ArrayList<Byte>> Ind;


    /**
     * Contructs empty graph.
     */
    public IndMatrix() {
        Verts = new ArrayList<T>();
        Edges = new ArrayList<T>();
        Ind = new ArrayList<ArrayList<Byte>>();
    } // end of IndMatrix()
    
    
    public void addVertex(T vertLabel) {
        
        if (!Verts.contains(vertLabel)){
            Verts.add(vertLabel);
            Ind.add(createEmptyArrayList(Ind.size()));

            for (ArrayList<Byte> incidence : Ind) {
                incidence.add((byte) 0);
            }

            display();

        }else{
            System.out.println("Label already Exists");
        }
    } // end of addVertex()
    
    
    public void addEdge(T srcLabel, T tarLabel) {
        
        int srcIndex = Verts.indexOf(srcLabel);
        int tarIndex = Verts.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }

        Ind.get(srcIndex).set(tarIndex, (byte) 1);
        Ind.get(tarIndex).set(srcIndex, (byte) 1);

        display();
    } // end of addEdge()
    

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        int index = Verts.indexOf(vertLabel);
        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return neighbours;
        }
        
        for (int i = 0; i < Verts.size(); i++)
            if (Ind.get(index).get(i) == 1)
                neighbours.add(Verts.get(i));


        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        
        int index = Verts.indexOf(vertLabel);

        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return;
        }

        //Remove from the edges list;
        
        Edges.remove(index);
        for (ArrayList<Byte> innerEdges : Ind) {
            innerEdges.remove(index);
        }

        Verts.remove(index);

        display();
    } // end of removeVertex()
    
    
    public void removeEdge(T srcLabel, T tarLabel) {
        
        int srcIndex = Verts.indexOf(srcLabel);
        int tarIndex = Verts.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }

        Ind.get(srcIndex).set(tarIndex, (byte) 0);
        Ind.get(tarIndex).set(srcIndex, (byte) 0);


    } // end of removeEdges()
    
    
    public void printVertices(PrintWriter os) {
        
        for (T vert : Verts) {
            os.printf("%s ", vert);
        }
    } // end of printVertices()
    
    
    public void printEdges(PrintWriter os) {
        
        for (int i = 0; i < Ind.size(); i++){

            for (int j = 0; j < Ind.size(); j++){
                if (Ind.get(i).get(j) == (byte) 1)
                    os.printf("%s %s\n",Ind.get(i),Ind.get(j));
            }
        }


    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        // Implement me!
    
        boolean[] checked = new boolean[Verts.size()];
        int[] distances = new int[Verts.size()];
        
        Queue <T> queue = new LinkedList<T>();

        T curVert = vertLabel1;
        int curIndex = Verts.indexOf(vertLabel1);

        int wantedIndex = Verts.indexOf(vertLabel2);


        if (curIndex == wantedIndex)
            return 0;

        while (curVert != null){

            if (!checked[curIndex]){
                checked[curIndex] = true;

                for(T nVert : neighbours(curVert)){
                    int nIndex = Verts.indexOf(nVert);

                    if (checked[nIndex])
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
            } //end (if !checked)
        
            curVert = queue.poll();
            curIndex = Verts.indexOf(curVert);
        }//end While

        // if we reach this point, source and target are disconnected
        return disconnectedDist;        
    } // end of shortestPathDistance()



    private void display(){

        System.out.printf("\n   ");
        for (T vert : Verts) {
            System.out.printf("%s  ",vert);
        }

        for (int col = 0; col < Edges.size(); col++){

            System.out.printf("\n%s |",Ind.get(col));
            for (byte incidence : Ind.get(col)) {        
                System.out.printf("%s, ", incidence);
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

