/* 

DISTANCE ALGORITHM1.

INPUT : a set A
of k research 
area sets of features.

OUTPUT : a distance matrix
M[k,k] -- i.e. a square simmetric 
matrix (2-d matrix of 
order k*k)
of real numbers.

*/

package ontology;

import java.util.*;
import rw.*;

public class Valid implements Matrix{	

	//Properties:
	
	private Data[] in;//input vector V of objects of type Data!
	
	//Methods:
	
	//Constructor:
	
	public Valid(Data[] in){
		this.in=in;
		this.printMatrix2();
	}
		
	//Intersection function:
	
	public static HashSet inter(Data d1, Data d2){
		HashSet coll = new HashSet();
		for(int i=0;i<d1.area.length;i++){
			for(int j=0;j<d2.area.length;j++){
				if(d1.area[i].equals(d2.area[j])){
					coll.add(d1.area[i]);
				}
			}
		}
		return coll;
	}
	
	//Reunion function:
	
	public static HashSet reun(Data d1, Data d2){
		HashSet coll = new HashSet();
		for(int i=0;i<d1.area.length;i++){
			coll.add(d1.area[i]);
		}
		for(int i=0;i<d2.area.length;i++){
			coll.add(d2.area[i]);
		}
		return coll;
	}

	//Numerator function:
	
	public static int numerFib(Data d1, Data d2){
		int res = 0;
		int sum1 = 0;
		int sum2 = 0;
		HashSet coll = inter(d1,d2);
		Object[] arr = coll.toArray();
		for(int i=0; i<arr.length;i++){
			for(int j=0; j<d1.area.length;j++){
				if(arr[i]==d1.area[j]){
					sum1=sum1 + d1.weight[j];
					}
				}
			}
		for(int i=0; i<arr.length;i++){
			for(int j=0; j<d2.area.length;j++){
				if(arr[i]==d2.area[j]){
					sum2=sum2 + d2.weight[j];
					}
				}
			}
		res = sum1 +  sum2;
		return res;	
	}
	
	//Denominator function:
	
	public static int denomFib(Data d1, Data d2){
		int sum1 = 0;
		int sum2 = 0;
		HashSet coll = reun(d1,d2);
		Object[] arr = coll.toArray();
		for(int i=0; i<arr.length;i++){
			for(int j=0; j<d1.area.length;j++){
				if(arr[i]==d1.area[j]){
					sum1=sum1 + d1.weight[j];
					}
				}
			}
		for(int i=0; i<arr.length;i++){
			for(int j=0; j<d2.area.length;j++){
				if(arr[i]==d2.area[j]){
					sum2=sum2 + d2.weight[j];
					}
				}
			}
		int res = sum1 +  sum2;
		return res;	
	}
	
	//Distance function:
	
	public static float distanceFib(Data d1, Data d2){
		float n = numerFib(d1,d2);//conversion to reals!
		float m = denomFib(d1,d2);//conversion to reals!
		float res = 1-(n/m);//division is defined only for reals (or rationals)!
		return res;//distances are reals!
	}
	
	//Distance matrix function:
	
	//N.B. This array computes an adjacency matrix A.
	//Each aij of A denotes the weight, similarity
	//or distance sim(vi,vj) affected to
	//the links (edges) between terms (nodes) vi, vj
	//of V, when these exist.
	
	public float[][] distMatrix(){
		float[][] di = new float[in.length][in.length];
		for(int i=0;i<di.length;i++){
			for (int j=0;j<di.length;j++){
				di[i][j] = distanceFib(in[i],in[j]);
			}
		}		
		return di;
	}
	
	//A function to print on a 
	//file the preceding matrix:
	
	public void printMatrix2(){
		String s = "";
		float[][] di = distMatrix();
		for(int i=0;i<di.length;i++){
			for(int j=0;j<di[i].length;j++){
				s = s + (di[i][j] + "  ");
			}
			s = s + "\n";
		}
		s = s + "\n";
		Write w = new Write(s);
	}
	
}
