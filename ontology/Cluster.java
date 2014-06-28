/*

CLUSTERS

This class implements the cluster class

*/

package ontology;

import java.util.*;

public class Cluster{
	
	Data[] m;
	List l;
	
	//Constructor: a cluster is both an
	//array and a list of Data.
	
	public Cluster(Data[] m){
		this.m=m;
		this.l=buildCluster();
	}
	
	//Distance of clusters is the minimal
	//cosinus distance between the input 
	//research areas:
	
	public static float distCos(Cluster c1, Cluster c2){
		int n = c1.size();
		int m = c2.size();
		float[][] t = new float[n][m];
		List l = new ArrayList();
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				Data d1 = c1.get(i);
				Data d2 = c2.get(j);
				t[i][j] = Valid2.distanceCos(d1,d2);
				l.add(new Float(t[i][j]));
			}
		}
		Collections.sort(l);
		String s = l.get(0).toString();
		float f = Float.parseFloat(s);
		return f;
	}
	
	//Distance of clusters can be too the minimal
	//fibonacci distance between the input 
	//research areas:
	
	public static float distFib(Cluster c1, Cluster c2){
		float[][] t = new float[c1.m.length][c2.m.length];
		List l = new ArrayList();
		for(int i=0;i<c1.m.length;i++){
			for(int j=0;j<c1.m.length;j++){
				t[i][j] = Valid.distanceFib(c1.m[i],c2.m[j]);
				l.add(new Float(t[i][j]));
			}
		}
		Collections.sort(l);
		String s = l.get(0).toString();
		float f = Float.parseFloat(s);
		return f;
	}
	
	//Builds a list of input names:
	
	public List buildCluster(){
		List l = new ArrayList();
		for(int i=0;i<m.length;i++){
			l.add(m[i].name);
		}
		return l;
	}
	
	//Retrieving an element of the cluster:
	
	public Data get(int n){
		Data o = m[n];
		return o;
	}
	
	//Retrieving the size of a cluster:
	
	public int size(){
		return m.length;
	}
	
	//Merging two clusters:
	
	public static Cluster mergeC(Cluster c1, Cluster c2){
		int n1 = c1.m.length;
		int n2 = c2.m.length;
		Data[] d = new Data[n1+n2];
		for(int i=0;i<n1;i++){
			d[i] = c1.m[i];
		}
		for(int j=0;j<n2;j++){
			d[n1+j] = c2.m[j];
		}
		Cluster c = new Cluster(d);
		return c;
	}
	
	//Merging two lists of input names:
	
	public static List mergeC2(Cluster c1, Cluster c2){
		c1.l.addAll(c2.l);
		return c1.l;
	}
	
}
