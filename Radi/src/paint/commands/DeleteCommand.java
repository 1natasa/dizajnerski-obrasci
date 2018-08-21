package paint.commands;

import paint.geometry.Shape;
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
	
	

}
