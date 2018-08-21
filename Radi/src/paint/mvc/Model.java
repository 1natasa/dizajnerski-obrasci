package paint.mvc;

import java.util.ArrayList;
import java.util.List;

import paint.geometry.Shape;

public class Model {
	
	private List<Shape> shapes;
	//skup svih objekata, model ne moze da postoji bez geometrije
	
	public Model()
	{
		shapes = new ArrayList<Shape>();
	}
	
	
	public void addShape (Shape s)
	{
		shapes.add(s);
	}
	
	public void deleteShape(Shape s)
	{
		shapes.remove(s);
	}

	public List<Shape> getSelectedShapes()
	
	{
		List<Shape> selectedShapes = new ArrayList<Shape>();
		for(Shape s : shapes)
		{
			if(s.isSelected())
			{
				selectedShapes.add(s);
			}
		}
		
		return selectedShapes;
	}
	
	public List<Shape> getShapes()
	{
		return shapes;
	}
}
