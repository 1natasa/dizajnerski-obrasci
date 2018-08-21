package paint.commands;

import paint.geometry.Shape;
import paint.mvc.Model;

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

}
