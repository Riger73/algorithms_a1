import java.io.*;
import java.util.*;


public class EdgeGenerator{

	// Test class to run main and generate test cases
	public static void main(String[] args) throws IOException{

		// Accepts 2 arguments - 1st for size, eg: 0.1, 0.2, etc;
		// and 2nd for number of Vertices

		// Declare class variables
		double density = Double.parseDouble(args[0]);
		int vertices = Integer.parseInt(args[1]);
		int edges = (int) Math.round((vertices * (vertices - 1)) * density);

		// seed random number
		Random rand_ver = new Random();

		int[][] array = new int[edges][2];
	
	 	// instantiate PrintWriter
		PrintWriter output = new PrintWriter(new FileWriter("file.txt"));

		for(int i = 0; i < edges; i++){
			for(int j = 0; j < 2; j++){

				array[i][j] = -1;

			}	
		}

		for(int i = 0; i < edges; i++){

			int data1 = rand_ver.nextInt(4037) + 1;
			int data2 = rand_ver.nextInt(4037) + 1;	

			if(data1 == data2){
				
				i = i - 1;

			}else{
			
				for(int j= 0; j < edges; j++){

					if(array[j][0] == data1 && array[j][1] == data2){

						i = i - 1;
						break;
					}else if(array[j][0] == data2 && array[j][1] == data1){

						i = i - 1;
						break;
					}else {
						
						if(array[j][0] == -1 && array[j][1] == -1){

							array[j][0] = data1;
							array[j][1] = data2;
							
							output.print(array[j][0]);
							output.print(" ");
							output.print(array[j][1]);
							output.print("\n");

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
