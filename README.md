# algorithms_a1
Assignment 1 startup code for Algorithms and Aanalysis S1 2018  

## Compiling & Running
Compile Java code with : `javac -cp *:. *.java`  
Run Java with : `java -cp .:jopt-simple-5.0.2.jar GraphTester adjmat`  
Week one checkpoint: `python assign1TestScript.py -v ../javaSrc adjmat tests/test1.in`  

java -cp .:jopt-simple-5.0.2.jar GraphTester adjmat -f ./testing/test3LoadedGraph.txt test1-adjmat.vert.out test1-adjmat.edge.out test1-adjmat.neigh.out test1-adjmat.dist.out

java -cp .:jopt-simple-5.0.2.jar GraphTester indmat -f ./testing/D0.1-V50.txt test1-indmat.vert.out test1-indmat.edge.out test1-indmat.neigh.out test1-indmat.dist.out
