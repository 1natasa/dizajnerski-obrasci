package aplikacija.wb;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Shape;

public class PanelZaCrtanje extends JPanel {
	private List<Shape> oblici;
	
	public PanelZaCrtanje()
	{
		oblici=new ArrayList<Shape>();
	}
	
	public void add(Shape o){
		
		oblici.add(o);
		repaint();
	}
	
	public void remove(Shape o)
	{
		oblici.remove(o);
		repaint();
	}
	
	protected void paintComponent(Graphics g)
	{
		System.out.println("osvezavam");
		super.paintComponent(g);
		for (Shape o : oblici)
		{
			o.drawShape(g);
		}
	}
}
