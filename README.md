# algorithms_a1
Assignment 1 startup code for Algorithms and Aanalysis S1 2018  
# Authors: 
Joshua Reason 
Timothy Novice

## Compiling & Running
Compile Java code with : `javac -cp *:. *.java`  
Run Java with : `java -cp .:jopt-simple-5.0.2.jar GraphTester adjmat`  
Week one checkpoint: `python assign1TestScript.py -v ../javaSrc adjmat tests/test1.in`  

java -cp .:jopt-simple-5.0.2.jar GraphTester adjmat -f ./testing/test3LoadedGraph.txt test1-adjmat.vert.out test1-adjmat.edge.out test1-adjmat.neigh.out test1-adjmat.dist.out

java -cp .:jopt-simple-5.0.2.jar GraphTester indmat -f ./testing/D0.1-V50.txt test1-indmat.vert.out test1-indmat.edge.out test1-indmat.neigh.out test1-indmat.dist.out

## Folder Structure
The root folder "default" holds the bulk of the code including
## implemented by Joshua & Tim
AdjMatrix.java - impliments the adjacency matrix
IndMatrix.java - impliments the incidence matrix
## unaltered startup code
FriendshipGraph.java - provides interfaces for the FriendShip graph implimentations
GraphTester.java - privides the main method and runs methods to start the graph
## next level packages
generation - contains random edge generator application code
quicktest - contains testing code
## inside generation package - implemented by Joshua and Tim
EdgeGenerator - implements an edge/vertex random generator. Takes 2 comman line parametres to run. First parameter is a double and represents the desired density of the output. Second parameter takes an integer and represents the number of vertices to generate.
## inside quicktest - contains all test files and test scripts. Is also the parant of the testscripts package
## inside testscript - contains all course provided original test scrips and files and the python run script




