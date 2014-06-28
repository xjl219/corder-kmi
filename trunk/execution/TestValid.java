/*

EXECUTION CLASS.

In this class, we create some objects
and apply the methods to them. We
pick 10 samples.

*/

package execution;

import ontology.*;

public class TestValid{
	
	public static void main(String[] args){
		
		//This procedure builds the similarity/distance
		//matrix of order n*n where n denotes
		//the length of the input data set of
		//of research areas retrieved form the database.
		//It then proceeds to perform a
		//hierarchical aglomerative clustering.
		//Outputs are stored in .txt files.
		
		BuildData b = new BuildData();
		try{
			
			//0.Building the data set:
			
			System.out.println("***********  HIERARCHICAL CLUSTERING  " +
					"*********");
			
			System.out.println("\n1.Processing the dataset...\n");
			Data[] in = b.connect();
			
			//1.Fibonacci set distance matrix:
			
			System.out.println("\n2.Building the set distance matrix...");
			//Valid matrix = 
			new Valid(in);
			
			//2.Cosinus distance matrix:
			
			System.out.println("\n3.Building the cosine distance matrix...");
			//Valid2 
			//matrix2 = 
			new Valid2(in);
			
			//3.Clustering with cosinus distance matrix:
			
			System.out.println("\n4.Clustering the data objects...");
			//Hierar2 h = 
			new Hierar2(in);
			
			System.out.println("\n*********  The program has ended!  " +
					"*********");
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
