package paint.mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import paint.geometry.Shape;
import paint.geometry.SurfaceShape;

@SuppressWarnings("serial")
public class View extends JPanel {
	
	private Model model;
	
	public View(Model model)
	{
		this.model=model;
	
	}
	
	@Override
	public void paint(Graphics g) {	
		super.paint(g);
		for (Shape s : model.getShapes())
		{
			if (s instanceof SurfaceShape)
			{
				((SurfaceShape)s).fill(g);
			}
			s.drawShape(g);
			
		}
	}
	
	public void setModel(Model model)
	{
		this.model=model;
	}
	
}
