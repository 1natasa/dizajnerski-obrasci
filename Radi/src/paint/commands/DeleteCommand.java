package paint.commands;

import java.util.ArrayList;
import java.util.List;

import paint.geometry.Circle;
import paint.geometry.HexagonAdapter;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.geometry.Rectangle;
import paint.geometry.Shape;
import paint.geometry.Square;
import paint.mvc.Model;

public class DeleteCommand implements Command{
	
	private List<Shape> shapes;
	private Model model;
	
	
	public DeleteCommand(List<Shape> shapes, Model model)
	{
		this.shapes=new ArrayList<>(shapes);
		this.model=model;
	}
	

	@Override
	public void execute() {
		
		for(Shape s : shapes)
		{
			model.deleteShape(s);
		}
		
	}

	@Override
	public void unexecute() {
		for(Shape s : shapes)
		{
		model.addShape(s);
		}
	}
	
	public String getDescription()
	{
		
		return "DeleteCommand,"+shapes ;
	}

}
