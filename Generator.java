import java.io.PrintWriter;
import java.io.File;
import java.io.*;
import java.util.*;


public class Generator{

	// Test class to run main and generate test cases
	public static void main(String[] args) throws IOException{

		// Accepts 2 arguments - 1st for density, eg: 0.1, 0.2, etc;
		// and 2nd for number of Vertices

		// Deeclair class variables
		double density = Double.parseDouble(args[0]);
		int vertex = Integer.parseInt(args[1]);
		int edges = (int) Math.round((vertex * vertex) * density);

		// seed random number
		Random rand_ver = new Random();

		int[][] array = new int[edges][2];
	
	 	// instantiate PrintWriter
		PrintWriter output = new PrintWriter(new FileWriter("output.txt"));

		for(int i = 0; i < edges; i++){
			for(int k = 0; k < 2; k++){

				array[i][k] = -1;

			}	
		}

		for(int l = 0; l < edges; l++){

			int data1 = rand_ver.nextInt(5000) + 1;
			int data2 = rand_ver.nextInt(5000) + 1;	

			if(data1 == data2){
				
				l = l - 1;

			}else{
			
				for(int i= 0; i < edges; i++){

					if(array[i][0] == data1 && array[i][1] == data2){

						l = l - 1;
						break;
					}else if(array[i][0] == data2 && array[i][1] == data1){

						l = l - 1;
						break;
					}else {
						
						if(array[i][0] == -1 && array[i][1] == -1){

							array[i][0] = data1;
							array[i][1] = data2;
							
							data.print(array[i][0]);
							data.print(" ");
							data.print(array[i][1]);
							data.print("\n");

							break;
						}
					}

					

				}
		
				


			}

		}
				
		//Close PrintWriter Stream
		output.close();

		System.out.println("Complete.");

	}

}
