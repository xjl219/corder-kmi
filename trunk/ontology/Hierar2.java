/*

HIERARCHICAL CLUSTERING ALGORITHM

This class implements Ward's aglomerative
hierarchical clustering algorithm using
the minimum linkage criterion. Its
complexity (on paper!) is roughly
O(n^3), where n is the
length of the input feature vectors, if
we put aside the complexity of the
sorting sub-procedures. We only
use the cosinus distance (and
not the fibonacci set distance).

INPUT: a set A of areas.

OUTPUT: a set C of clusters and a graph G 
(dendogram).

*/

package ontology;

import java.util.*;
import read.*;

public class Hierar2{

	//1.Fields:
	
	Data[] in;
	Cluster[] clusters;
	List input;
	List output;
	
	//2.Methods:
	
	//a.Constructor:
	
	public Hierar2(Data[] in){
		this.in=in;
		this.prePro();
		this.cluster();
	}
		
	//b.Procedures:
	
	//We create a distance matrix for the
	//partitions:
	
	public static float[][] distMatrix1(List l){
		System.out.println("Select the distance" +
				"(type 'fib' or 'cos'):");
		String s = Keyboard.read_string();
		float[][] t = new float[l.size()][l.size()];
		for(int i=0;i<l.size();i++){
			for(int j=0;j<l.size();j++){
				Cluster c1 = (Cluster)l.get(i);
				Cluster c2 = (Cluster)l.get(j);
				if(s.equals("fib")){
					t[i][j] = Cluster.distCos(c1,c2);
				}
				if(s.equals("cos")){
					t[i][j] = Cluster.distFib(c1,c2);
				}
			}
		}
		return t;
	}
		
	//This function computes the min of
	//a matrix of reals:
	
	public static float min(float[][] m){
		List l1 = new ArrayList();
		for(int i=0;i<m.length;i++){
			for(int j=(i+1);j<m[i].length;j++){
				l1.add(new Float(m[i][j]));
			}
		}
		Collections.sort(l1);
		Float f = (Float)l1.get(0);
		float min = f.floatValue();
		return min;
	}
		
	//We preprocess the lists (to
	//intialize the clustering 
	//variables:
	
	public void prePro(){
		
		//A previous array of singletons:
		
		clusters = new Cluster[in.length];
		for(int i=0; i<in.length; i++){
			Data[] d = {in[i]};
			Cluster c = new Cluster(d);
			clusters[i] = c;
		}
		
		//List of singleton partitions:
		
		input = new ArrayList();
		for(int i=0; i<in.length; i++){
			Data[] d = {in[i]};
			Cluster c = new Cluster(d);
			input.add(c);
		}
		
		//List of outputs:
		
		output = new ArrayList();
		for(int i=0; i<in.length; i++){
			Data[] d = {in[i]};
			Cluster c = new Cluster(d);
			output.add(c);
		}
		
	}
	
	//Clustering (unbounded while loop):
	
	public void cluster(){
		int it = 0;//iteration counter
		List begin = input;//input list of clusters (vertex)
		List U = new ArrayList();//list of edges
		while(begin.size() > 1){//the loop begins
			List l = begin;//local list	
			float[][] dist = distMatrix1(l);//list distance matrix
			float min = min(dist);//its min value
			for(int i=0;i<l.size();i++){
				for(int j=(i+1);j<l.size();j++){				
					int flag = 0;
					if((min == dist[i][j])&&(flag == 0)){
						
						Cluster c1 = (Cluster)l.get(i);
						Cluster c2 = (Cluster)l.get(j);
						Cluster c = Cluster.mergeC(c1,c2);
						
						output.add(c);//we update output
						
						Edge2 u1 = new Edge2(c1,c,min);
						Edge2 u2 = new Edge2(c2,c,min);
						
						U.add(u1);//we update U
						U.add(u2);
						
						l.remove(c1);
						l.remove(c2);
						l.add(c);
						
						flag = flag + 1;
					}
				}
			}
			begin = l;//the input list is set to the local list.
			it = it+1;
		}
		
		Graph2 g = new Graph2(output,U);//we build the output graph
		
		//float[][] matrix = g.AdjMatrix2();//we stock its adjacency matrix
		
		//Tests:
		
		//g.printGraph2();
		//Varia.prMatrix(matrix);
	}

}

