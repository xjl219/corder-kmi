/*

QUERYING THE DATABASE

*/

package ontology;

import java.sql.*;
import java.util.*;
import read.Keyboard;

public class BuildData {

	//Connection fields:
	
	private static String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	private String url; //= "jdbc:odbc:ontology";
	private String pwd; //= "camilo";
	private String user; //="1026";
	
	//Connection method:
	
	public Data[] connect() throws Exception {
		
		//a.We set the connection variables:
		
		System.out.println("Type the name of the MSAccess database" +
				"(such as required by the algorithm):");
		String s1 = Keyboard.read_string();
		url = "jdbc:odbc:"+s1;
		
		System.out.println("Type your login name:");
		String s2 = Keyboard.read_string();
		pwd = s2;
		
		System.out.println("Type your password:");
		String s3 = Keyboard.read_string();
		user = s3;
		
		//b.We open a connection to the DB:
		
		Class.forName(driver).newInstance();
		Connection con = DriverManager.getConnection(url,user,pwd);
		
		//c.We transform the DB into a Data array:
		
		//Query 1: We create a bound and a Data array:
		
		Statement st1 = con.createStatement();
		String c1 = "SELECT ID from AreaOntology";
		ResultSet rs1 = st1.executeQuery(c1);
		int com = 0;
		HashSet h = new HashSet();
		while(rs1.next()){
			int n = rs1.getInt("ID");
			h.add(""+n);
		}
		Object[] o = h.toArray();
		com = o.length;//this is the bound!
		Data [] d = new Data[com];
		
		//Query 2: We stock the entities:
		
		Statement st2 = con.createStatement();
		String[] ent = new String[com];
		for (int i=0;i<com;i++){
			String c2 = "SELECT Entity from AreaOntology WHERE ID="+i;
			ResultSet rs2 = st2.executeQuery(c2);
			while(rs2.next()){
				String e = rs2.getString("Entity");
				ent[i]=e;
			}
		}
		
		//Query 3: We stock the related entities:
		
		Statement st3 = con.createStatement();
		String[][] areas = new String[com][10];
		for(int i=0;i<com;i++){
			for(int j=3;j<=12;j++){
	            	String c3 = "SELECT * from AreaOntology WHERE ID="
	            		+ i;
	            	ResultSet rs3 = st3.executeQuery(c3);
	            	while(rs3.next()){
	         			String ra = rs3.getString(j);
	         			areas[i][j-3] = ra;
	            	}
	        }
		}
		
		//We fill the Data array:
		
		for(int i=0;i<com;i++){
			d[i] = new Data(ent[i],areas[i]);
		}

		//d.We close the connection to the DB:
		
		st1.close();
		st2.close();
		st3.close();
		con.close();
		
		//e.We output a 1-d array of Data:
		
		return d;
	}
	
}
