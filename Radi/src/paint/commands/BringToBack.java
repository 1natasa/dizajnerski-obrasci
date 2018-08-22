package paint.commands;

import paint.geometry.Shape;
import paint.mvc.Model;

public class BringToBack implements Command{
	
	private Shape shape;
	private Model model;
	private int index;

	public BringToBack(Model model, Shape shape)
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
	
	 

}
