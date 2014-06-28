/*

EDGES

Class implementing the edge datatype (for
clusters).

*/

package ontology;

import java.util.*;

public class Edge2 {

	//An edge is a couple (pair) of vertex
	//together with a weight.

	Cluster v1;
	Cluster v2;
	float k;
	List u;
		
	//Constructor:
	
	public Edge2(Cluster v1, Cluster v2, float k){
		this.v1=v1;
		this.v2=v2;
		this.k=k;//weight
		this.u=buildEdge();
	}
	
	//Accesors:
	
	public Cluster getI(){
		return v1;
	}
	
	public Cluster getT(){
		return v2;
	}
	
	public float getW(){
		return k;
	}
	
	public List getEdge(){
		return u;
	}
	
	public List buildEdge(){
		List l = new ArrayList();
		l.add(v1.buildCluster());
		l.add(v2.buildCluster());
		l.add(new Float(k));
		return l;
	}
	
}
