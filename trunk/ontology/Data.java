/*

THE DATA

The data is encoded as objects, their
fields being the researcher's names,
their 10 top-most research areas
and the fibonacci number of the
latter.

We define all the operations over this
type.

*/

package ontology;


public class Data {
	
	//Data is supposed to store the
	//input i.e. the feature 
	//vectors a = (w1,...,wm)^T
	//of the (term) space
	//representing each area A =
	//{a1,...,an}.

	//A.Fields:
	
	public String name;
	//Research area name.
	public String[] area;
	//Research areas a1,...,an of A sorted increasingly
	//w.r.t. their CORDER rank or strength.
	public int[] weight;
	//Fibonacci w_A(a1),...,w_A(an) weights.
	
	//B.Methods:
	
	//Input data constructor:
	
	public Data(String name, String[] area){
		this.name=name;
		this.area=area;
		this.fibo();
	}
	
	//Fibonacci function:
	
	public int fib(int n){
		if(n == 1 | n == 0)
		{return 1;}
		else
		{return fib(n-1) + fib(n-2);}
		}
	
	//Weight function:
	
	public void fibo(){
		weight = new int[area.length];
		for (int i=0;i<area.length;i++){
			weight[i]=fib(i);
		}
	}
		
}
