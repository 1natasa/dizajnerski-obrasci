package paint.commands;

import paint.geometry.Circle;
import paint.geometry.HexagonAdapter;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.geometry.Rectangle;
import paint.geometry.Shape;
import paint.geometry.Square;
import paint.mvc.Model;

public class BringToBackCommand implements Command{
	
	private Shape shape;
	private Model model;
	private int index;

	public BringToBackCommand(Model model, Shape shape)
	{
		this.model=model;
		this.shape=shape;
		this.index=model.getIndexOfShape(shape);
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.deleteShape(shape);
		model.addShapeOnIndex(0, shape);
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.deleteShape(shape);
		model.addShapeOnIndex(index, shape);
		
	}
	
	
	public String getDescription()
	{
	
		
		return "BringToBackCommand," + shape;
	}
	 

}
