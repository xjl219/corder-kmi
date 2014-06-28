/*

VARIA

This class contains some useful static and public
mathods for the whole package.

*/

package ontology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Varia {
	
	//This one we don't use -- it is based on the
	//fibonacci distance:
	
	public static float[][] distMatrix2(List l){
		float[][] t = new float[l.size()][l.size()];
		for(int i=0;i<l.size();i++){
			for(int j=0;j<l.size();j++){
				Cluster c1 = (Cluster)l.get(i);
				Cluster c2 = (Cluster)l.get(j);
				t[i][j] = Cluster.distFib(c1,c2);
			}
		}
		return t;
	}
	
	//We compute the pairwise minimal distance
	//of the input list of partitions by sorting the
	//matrix:
	
	public static float minDist1(List l){
		float[][] t = Hierar2.distMatrix1(l);//cosinus measure!
		List l1 = new ArrayList();
		for(int i=0;i<t.length;i++){
			for(int j=(i+1);j<t[i].length;j++){
				l1.add(new Float(t[i][j]));
			}
		}
		Collections.sort(l1);
		Float f = (Float)l1.get(0);
		float min = f.floatValue();
		return min;
	}
	
	
	//We retrieve the pair of partitions
	//with minimum distance and merge them 
	//in a cluster:
	
	public static Cluster arg1MinDist(List l){
		Cluster c = null;
		float[][] t = Hierar2.distMatrix1(l);
		float min = minDist1(l);
		for(int i=0;i<l.size();i++){
			for(int j=(i+1);j<l.size();j++){
				if(min == t[i][j]){
					Cluster c1 = (Cluster)l.get(i);
					Cluster c2 = (Cluster)l.get(j);
					c = Cluster.mergeC(c1,c2);
				}
			}
		}
		return c;
	}
	
	//We retrieve the pair of partitions
	//with minimum distance and merge 
	//them in a list:
	
	public static List arg2MinDist(List l){
		float[][] t = Hierar2.distMatrix1(l);
		float min = minDist1(l);
		List res = new ArrayList(); 
		for(int i=0;i<l.size();i++){
			for(int j=0;j<l.size();j++){
				if(min == t[i][j]){
					Cluster c1 = (Cluster)l.get(i);
					Cluster c2 = (Cluster)l.get(j);
					List l1 = Cluster.mergeC2(c1,c2);
					res.addAll(l1);
				}
			}
		}
		return res;
	}
	
	//Printing a distance matrix on the console
	
	public static void prMatrix(float[][] di){
		for(int i=0;i<di.length;i++){
			for(int j=0;j<di[i].length;j++){
				System.out.print(di[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	//We print any set(list) of clusters:
	
	public static void prData(List l){
		List l1 = new ArrayList();
		for(int i=0;i<l.size();i++){
			Object o = l.get(i);
			Cluster c = (Cluster)o;
			List l2 = c.buildCluster();
			l1.add(l2);
			System.out.println(l1.get(i));
			}
	}
	
	//We compute the average (mean) distance for a 
	//given distance matrix:
	
	public static float aver1(float[] t){
		float res = 0;
		for(int i=1; i<t.length;i++){
			res = res + t[i];
		}
		return res/t.length;
	}
	
	public static float aver2(float[][] t){
		float res = 0;
		for(int i=1;i<t.length;i++){
			res = res + aver1(t[i]);
		}
		return res/t.length;
	}
	
}
