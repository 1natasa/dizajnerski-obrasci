package paint.commands;

import paint.geometry.Shape;
import paint.mvc.Model;

public class BringToFrontCommand implements Command{
	
	private Model model;
	private Shape shape;
	int index;
	
	 public BringToFrontCommand(Model model, Shape shape) {
		// TODO Auto-generated constructor stub
		this.model=model;
		this.shape=shape;
		this.index=model.getIndexOfShape(shape);
	}
	

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.deleteShape(shape);
		
		model.addShape(shape);
		
		
		
	}

	@Override
	public void unexecute() {
		model.deleteShape(shape);
		model.addShapeOnIndex(index, shape);
		
	}

}
