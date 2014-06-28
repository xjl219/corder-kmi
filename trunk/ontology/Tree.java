package ontology;

import java.awt.*;
import javax.swing.*;


class Panel extends JPanel{
	
	private float k1;
	private float k2;
	
	Panel(float k1, float k2){
		this.k1 = k1;
		this.k2 = k2;
		this.setSize((int)k1,(int)k2);
		this.setBackground(Color.lightGray);
		//this.draw();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.drawline(x1,y1,x1,y2);//h1
		//g.drawline(x2,y1,x1,y1);//h2
		//g.drawline(x1,y2,x2,y2);//h3
	}
	
}


public class Tree extends Frame{

	private float k1 = 400;
	private float k2 = 400;
	private Panel pan;
	
	Tree(){
	
	int k = (int)k1;
	int kk = (int)k2;
	setTitle("DENDOGRAM");
	setBounds(10,10,k,kk);
	Panel pan = new Panel(k1,k2);
	
	}
}
