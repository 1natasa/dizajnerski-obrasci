package paint.commands;

import paint.geometry.Circle;
import paint.geometry.HexagonAdapter;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.geometry.Rectangle;
import paint.geometry.Shape;
import paint.geometry.Square;
import paint.mvc.Model;
import paint.mvc.PaintFrame;

public class AddCommand implements Command{
	
	private Shape shape;
	private Model model;
	
	
	public AddCommand(Shape shape, Model model)
	{
		this.shape=shape;
		this.model=model;
	}

	@Override
	public void execute() {
		
		model.addShape(shape);
		
	}

	@Override
	public void unexecute() {
		model.deleteShape(shape);
	}

	
	public String getDescription()
	{
		
		return "AddCommand," + shape;
	}
	
}
