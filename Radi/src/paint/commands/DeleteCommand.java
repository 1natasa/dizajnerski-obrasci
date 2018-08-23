package paint.commands;

import paint.geometry.Circle;
import paint.geometry.HexagonAdapter;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.geometry.Rectangle;
import paint.geometry.Shape;
import paint.geometry.Square;
import paint.mvc.Model;

public class DeleteCommand implements Command{
	
	private Shape shape;
	private Model model;
	
	
	public DeleteCommand(Shape shape, Model model)
	{
		this.shape=shape;
		this.model=model;
	}
	

	@Override
	public void execute() {
		
		model.deleteShape(shape);
	}

	@Override
	public void unexecute() {
		
		model.addShape(shape);
	}
	
	public String getDescription()
	{
		
		return "DeleteCommand,"+shape ;
	}

}
