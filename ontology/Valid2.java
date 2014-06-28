/*

DISTANCE MATRIX ALGORITHM2.

INPUT : a set A
of k research 
area feature vectors.

OUTPUT : a distance matrix
M[k,k] -- i.e. a square simmetric 
matrix (2-d matrix of 
order k*k)
of real numbers.

*/

package ontology;

import java.util.*;
import rw.Write;

public class Valid2 implements Matrix{

	//Fields:
	
	private Data[] in;//input set A of areas!
	
	//Methods:
	
	//Constructor:
	
	public Valid2(Data[] in){
		this.in=in;
		this.printMatrix2();
	}
	
	//Characteristic (char) function:
	
	//N.B. We need it in order to simulate
	//the new weighing function.
	
	public static int carac(Object s, Data d){
		int res = 0;
		for(int i=0;i<d.area.length;i++){
			if(s.equals(d.area[i])){
				res = res + 1;
			}
			else{
				res = res + 0;
			}
		}
		return res;
	}
	
	//Numerator:
	
	public static float numerCos(Data d1,Data d2){
		float res = 0;
		float sum1 = 0;
		float sum2 = 0;
		HashSet coll = Valid.reun(d1,d2);
		Object[] arr = coll.toArray();
		for(int i=0; i<arr.length;i++){
			int n = carac(arr[i],d1);
			int m = carac(arr[i],d2);
			int k = (m*n);//conjunction of char functions
			for(int j=0;j<d1.area.length;j++){
				if(arr[i].equals(d1.area[j])){
					sum1 = (d1.weight[j]*k);	
				}
				if(arr[i].equals(d2.area[j])){
					sum2 = (d2.weight[j]*k);
				}
			}
			res = res + (sum1*sum2);
		}
		return res;
	}
	
	//Denominator:
	
	public static float denomCos(Data d1,Data d2){
		double res = 0;
		double sum1 = 0;
		double sum2 = 0;
		HashSet coll = Valid.reun(d1,d2);
		Object[] arr = coll.toArray();
		for(int i=0; i<arr.length;i++){
			int n = carac(arr[i],d1);
			int m = carac(arr[i],d2);
			double sum11 = 0;
			double sum21 = 0;
			int k = ((m+n)-(m*n));//disjunction of char functions
			for(int j=0;j<d1.area.length;j++){
				if(arr[i].equals(d1.area[j])){
					sum11 = Math.pow((d1.weight[j]*k),2);
				}
				if(arr[i].equals(d2.area[j])){
					sum21 = Math.pow((d2.weight[j]*k),2);
				}
			}
			sum1 = sum1 + sum11;
			sum2 = sum2 + sum21;
			}
		res = Math.sqrt(sum1)*Math.sqrt(sum2);
		return (float)res;
	}
	
	//Cosinus distance:
	
	//N.B. We don't follow
	//line by line the specification of the
	//function. However, behavioural
	//equivalence trivially holds. The idea is
	//to divide the shared weights of two
	//feature vectors by their overall weights.
	
	public static float distanceCos(Data d1, Data d2){
		double res = 0;
		float n = numerCos(d1,d2);
		float m = denomCos(d1,d2);
		res = 1-(n/m);
		return (float)res;
	}
	
	//Distance matrix function:
	
	public float[][] distMatrix(){
		float[][] di = new float[in.length][in.length];
		for(int i=0;i<di.length;i++){
			for (int j=0;j<di.length;j++){
				di[i][j] = distanceCos(in[i],in[j]);
			}
		}		
		return di;
	}
	
	//A procedure to print in a file
	//the preceding matrix:
	
	public void printMatrix2(){
		String a = "";
		float[][] di = distMatrix();
		for(int i=0;i<di.length;i++){
			for(int j=0;j<di[i].length;j++){
				a = a + (di[i][j] + "  ");
			}
			a = a + "\n";
		}
		a = a + "\n";
		Write w = new Write(a);
	}
	
}
