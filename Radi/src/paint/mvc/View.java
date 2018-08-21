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
		
		setBackground(Color.RED);
		setMinimumSize(new Dimension(800, 600));
	}
	
	@Override
	public void paint(Graphics g) {
		for (Shape s : model.getShapes())
		{
			s.drawShape(g);
			if (s instanceof SurfaceShape)
			{
				((SurfaceShape)s).fill(g);
			}
		}
	}
	
	
}
