/*

GRAPHS

This class provides a most simple implementation of
the graph datatype (undirected graphs) relatively
to clusters. This implematation is relative to the
package.

*/

package ontology;

import java.util.*;
import rw.Write;

//A graph is a set of vertex and edges.

public class Graph2 {

	List X;//list of vertex clusters
	List U;//list of edges among clusters
	float[][] a;
	
	public Graph2(List X, List U){
		this.X=X;
		this.U=U;
		this.printGraph2();
	}
	
	//Adjacency matrix for a 1-graph:
	
	public float[][] AdjMatrix2(){
		float[][] a = new float[X.size()][X.size()];
		for(int i=0;i<X.size();i++){
			for(int j=(i+1);j<X.size();j++){
				for(int p=0;p<U.size();p++){
					Cluster x1 = (Cluster)X.get(i);
					Cluster x2 = (Cluster)X.get(j);
					Edge2 u = (Edge2)U.get(p);
					if((x1.equals(u.v1))&&(x2.equals(u.v2))){
						a[i][j] = u.k;
					}
				}
			}
		}
		return a;
	}
	
	//Size:
	
	public int size(){
		return X.size();
	}
	
	//Order:
	
	public int order(){
		return U.size();
	}
	
	//Printing the graph:
	
	//On the console:
	
	public void printGraph(){
		for(int i=0;i<X.size();i++){
			Cluster x = (Cluster)X.get(i);
			System.out.println(x.buildCluster()+
					" : vertex(output cluster) "+(i+1));
		}
		for(int j=0;j<U.size();j++){
			Edge2 u = (Edge2)U.get(j);
			System.out.println(u.buildEdge()+
					" : edge "+(j+1));				
		}
	}
	
	//In a file:
	
	public void printGraph2(){
		String s1 = "";
		String s2 = "";
		String s3 = "\n" + "Graph size = " 
			+ (X.size()) + "\n";
		String s4 = "Graph order = " 
			+ (U.size()) + "\n";
		String s5 = "Remark: the 'weight' is the " +
				"clustering distance and " +
				"not the weight of the edge!!!";
		for(int i=0;i<X.size();i++){
			Cluster x = (Cluster)X.get(i);
			s1 = s1 + (x.buildCluster()) + 
			" : vertex(output cluster) " + 
			(i+1) + "\n";
		}
		for(int j=0;j<U.size();j++){
			Edge2 u = (Edge2)U.get(j);
			s2 = s2 + (u.buildEdge()) + 
			" :edge " + (j+1) + "\n";				
		}
		Write w = new Write(s1+s2+s3+s4+s5);
	}
	
}

