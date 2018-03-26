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
    private ArrayList<T> ColLabels;
    private ArrayList<T> RowLabels;    
    private ArrayList<ArrayList<Byte>> Ind;
    
    /**
     * Contructs empty graph.
     */
    public IndMatrix() {

        ColLabels = new ArrayList<T>();
        RowLabels = new ArrayList<T>();
        Ind = new ArrayList<ArrayList<Byte>>();
    } // end of IndMatrix()
    
    
    public void addVertex(T vertLabel) {
        
        if (!RowLabels.contains(vertLabel)){
            RowLabels.add(vertLabel);
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
        
        int srcIndex = ColLabels.indexOf(srcLabel);
        int tarIndex = ColLabels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }
        
        else {
            ColLabels.add(srcLabel);
            ColLabels.add(tarLabel);
            Ind.add(createEmptyArrayList(Ind.size()));

            for (ArrayList<Byte> incidence : Ind) {
                incidence.add((byte) 0);
            }

            display();
        }
            
    } // end of addEdge()
    

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        int index = RowLabels.indexOf(vertLabel);
        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return neighbours;
        }
        
        for (int i = 0; i < RowLabels.size(); i++)
            if (Ind.get(index).get(i) == 1)
                neighbours.add(RowLabels.get(i));


        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        
        int index = RowLabels.indexOf(vertLabel);

        if (index == -1){
            System.out.printf("\n%s does not Exists",vertLabel);
            return;
        }
      
        RowLabels.remove(index);
        for (ArrayList<Byte> incidences : Ind) {
            incidences.remove(index);
        }

        RowLabels.remove(index);

        display();
    } // end of removeVertex()
    
    
    public void removeEdge(T srcLabel, T tarLabel) {
        
        int srcIndex = ColLabels.indexOf(srcLabel);
        int tarIndex = ColLabels.indexOf(tarLabel);
        if (srcIndex == -1 || tarIndex == -1){
            System.out.printf("\n%s does not Exists", (srcIndex == -1)? srcLabel : tarLabel);
            return;
        }
        
        RowLabels.remove(srcIndex);
        RowLabels.remove(tarIndex);
        for (ArrayList<Byte> incidences : Ind) {
            incidences.remove(srcIndex);
            incidences.remove(tarIndex);
        }

    } // end of removeEdges()
    
    
    public void printVertices(PrintWriter os) {
        
        for (T label : RowLabels) {
            os.printf("%s ", label);
        }
    } // end of printVertices()
    
    
    public void printEdges(PrintWriter os) {
        T srcLabel = null;
        T tarLabel = null;
        
        int srcIndex = ColLabels.indexOf(srcLabel);
        int tarIndex = ColLabels.indexOf(tarLabel);
        for (int i = 0; i < ColLabels.size(); i++){

            for (int j = 0; j < ColLabels.size(); j++){
                if (srcIndex == -1 || tarIndex == -1)
                    os.printf("%s %s\n",ColLabels.get(i),ColLabels.get(j));
            }
        }
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        // Implement me!
    
        boolean[] checked = new boolean[RowLabels.size()];
        int[] distances = new int[RowLabels.size()];
        
        Queue <T> queue = new LinkedList<T>();

        T curVert = vertLabel1;
        int curIndex = RowLabels.indexOf(vertLabel1);

        int wantedIndex = RowLabels.indexOf(vertLabel2);


        if (curIndex == wantedIndex)
            return 0;

        while (curVert != null){

            if (!checked[curIndex]){
                checked[curIndex] = true;

                for(T nVert : neighbours(curVert)){
                    int nIndex = RowLabels.indexOf(nVert);

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
            curIndex = RowLabels.indexOf(curVert);
        }//end While

        // if we reach this point, source and target are disconnected
        return disconnectedDist;        
    } // end of shortestPathDistance()


    private void display(){
        T srcLabel = null;
        T tarLabel = null;
        
        int srcIndex = ColLabels.indexOf(srcLabel);
        int tarIndex = ColLabels.indexOf(tarLabel);
        
        System.out.printf("\n   ");

        for (int i = 0; i < ColLabels.size(); i++){
            if (srcIndex == -1 || tarIndex == -1)
            for (int j = 0; j < ColLabels.size(); j++){
                System.out.printf("\n%s |", ColLabels.get(i), ColLabels.get(j));
            }
        }
        
        for (T label : RowLabels) {
            System.out.printf("%s  ",label);
            for (byte incidence : Ind.get(i)) {        
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




