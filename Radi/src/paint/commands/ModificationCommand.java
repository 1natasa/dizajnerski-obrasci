package paint.commands;

import paint.geometry.Shape;
import paint.mvc.Model;

public class ModificationCommand implements Command {
	
	private Model model;
	private Shape newShape;
	private Shape oldShape;
	
	public ModificationCommand(Shape oldShape, Shape newShape, Model model)
	{
		this.oldShape=oldShape;
		this.newShape=newShape;
		this.model=model;
	}
	

	@Override
	public void execute() {
		
		int index = model.getIndexOfShape(oldShape);
		model.deleteShape(oldShape);
		model.addShapeOnIndex(index, newShape);
		model.setSelection(newShape, true);
		
	}

	@Override
	public void unexecute() {
		
		int index= model.getIndexOfShape(newShape);
		model.deleteShape(newShape);
		model.addShapeOnIndex(index,oldShape);
		
		
		
	}

}
