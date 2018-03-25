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
    private ArrayList<ArrayList<Byte>> Ind;


    /**
     * Contructs empty graph.
     */
    public IndMatrix() {
        Labels = new ArrayList<T>();
        Labels = new ArrayList<T>();
        Ind = new ArrayList<ArrayList<Byte>>();
    } // end of IndMatrix()
    
    
    public void addVertex(T vertLabel) {
        
        if (!Labels.contains(vertLabel)){
            Labels.add(vertLabel);
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
        
        int srcIndex = Labels.indexOf(srcLabel);
        int tarIndex = Labels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }
        
        else {
            Labels.add(srcLabel);
            Labels.add(tarLabel);
            Ind.add(createEmptyArrayList(Ind.size()));

            for (ArrayList<Byte> incidence : Ind) {
                incidence.add((byte) 0);
            }

            display();
        }
            
    } // end of addEdge()
    

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        int index = Labels.indexOf(vertLabel);
        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return neighbours;
        }
        
        for (int i = 0; i < Labels.size(); i++)
            if (Ind.get(index).get(i) == 1)
                neighbours.add(Labels.get(i));


        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        
        int index = Labels.indexOf(vertLabel);

        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return;
        }
      
        Labels.remove(index);
        for (ArrayList<Byte> incidences : Ind) {
            incidences.remove(index);
        }

        Labels.remove(index);

        display();
    } // end of removeVertex()
    
    
    public void removeEdge(T srcLabel, T tarLabel) {
        
        int srcIndex = Labels.indexOf(srcLabel);
        int tarIndex = Labels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }
        
        Labels.remove(srcIndex);
        Labels.remove(tarIndex);
        for (ArrayList<Byte> incidences : Ind) {
            incidences.remove(srcIndex);
            incidences.remove(tarIndex);
        }

    } // end of removeEdges()
    
    
    public void printVertices(PrintWriter os) {
        
        for (T label : Labels) {
            os.printf("%s ", label);
        }
    } // end of printVertices()
    
    
    public void printEdges(PrintWriter os) {
        
        for (T label : Labels) {
            os.printf("%s ", label);
        }
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        // Implement me!
    
        boolean[] checked = new boolean[Labels.size()];
        int[] distances = new int[Labels.size()];
        
        Queue <T> queue = new LinkedList<T>();

        T curVert = vertLabel1;
        int curIndex = Labels.indexOf(vertLabel1);

        int wantedIndex = Labels.indexOf(vertLabel2);


        if (curIndex == wantedIndex)
            return 0;

        while (curVert != null){

            if (!checked[curIndex]){
                checked[curIndex] = true;

                for(T nVert : neighbours(curVert)){
                    int nIndex = Labels.indexOf(nVert);

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
            curIndex = Labels.indexOf(curVert);
        }//end While

        // if we reach this point, source and target are disconnected
        return disconnectedDist;        
    } // end of shortestPathDistance()



    private void display(){

        System.out.printf("\n   ");
        for (T vert : Labels) {
            System.out.printf("%s  ",vert);
        }

        for (int col = 0; col < Labels.size(); col++){

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



