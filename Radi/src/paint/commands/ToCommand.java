package paint.commands;

import paint.geometry.Shape;
import paint.mvc.Model;

public class ToCommand implements Command{

	
	//oblik, pozicija pocetna, i krajnja
	
	private Model model;
	private Shape startShape;
	private Shape endShape;
	
	public  ToCommand(Model model ,Shape startShape, Shape endShape) {
		this.model=model;
		this.startShape=startShape;
		this.endShape=endShape;
	}
	
	
	@Override
	public void execute() {
		
		int startIndex = model.getIndexOfShape(startShape);
		int endIndex =model.getIndexOfShape(endShape);
		Shape temp = startShape;
		model.addShapeOnIndex(startIndex, endShape);
		model.deleteShape(startShape);
		
		model.deleteShape(endShape);
		
		
		model.addShapeOnIndex(endIndex, startShape);
		
	}

	@Override
	public void unexecute() {
		
		int startIndex =  model.getIndexOfShape(startShape);
		int endIndex = model.getIndexOfShape(endShape);
		Shape temp= endShape;
		model.deleteShape(endShape);
		model.deleteShape(startShape);
		model.addShapeOnIndex(endIndex, startShape);		
		model.addShapeOnIndex(startIndex, endShape);
		
	}

}
