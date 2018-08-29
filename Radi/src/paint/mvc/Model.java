package paint.mvc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import paint.geometry.Shape;

public class Model extends Observable implements Serializable{
	
	private List<Shape> shapes;
	private String command;
	
	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}


	
	public Model()
	{
		shapes = new ArrayList<Shape>();
	}
	
	
	public void addShape (Shape s)
	{
		shapes.add(s);
		setChanged();
		notifyObservers();
	}
	
	public void addShapeOnIndex (int index, Shape s)
	{
		shapes.add(index, s);
		setChanged();
		notifyObservers();
	}
	
	public void deleteShape(Shape s)
	{
		for(int i=0; i<shapes.size(); i++)
		{
			if(s.equals(shapes.get(i)))
			{
				shapes.remove(i);			
				setChanged();
				notifyObservers();
				break;
			}
		}
		
		
	}
	
	
	public List<Shape> getShapes()
	{
		return shapes;
	}
	
	public int getIndexOfShape(Shape shape)
	{
		for (int i=0; i<shapes.size(); i++)
		{
			if (shapes.get(i).equals(shape))
			{
				return i;
			}
		}
		return -1;
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

	
	
	public List<Shape> unselectShapes()
	{
		List<Shape> unselectedShapes=  new ArrayList<Shape>();
		for (Shape shape : getSelectedShapes())
		{
			if(shape.isSelected())
			{
				shape.setSelected(false);
				unselectedShapes.add(shape);
			}
			
		}
		
		setChanged();
		notifyObservers();
		
		return unselectedShapes;
	}
	
	
	public Shape getShapeOnIndex(int index)
	{
		return shapes.get(index);
	}
	
	public void setSelection (Shape shape, boolean selected)
	{
		for(Shape s : shapes)
		{
			if(s.equals(shape))
			{
				s.setSelected(selected);
				setChanged();
				notifyObservers();
				break;
			}
			
		}
		
	}
	
	public void clear()
	{
		shapes.clear();
	}
	
	
}
