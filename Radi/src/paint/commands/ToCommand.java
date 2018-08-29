package paint.commands;

import javax.swing.JOptionPane;

import paint.geometry.Circle;
import paint.geometry.HexagonAdapter;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.geometry.Rectangle;
import paint.geometry.Shape;
import paint.geometry.Square;
import paint.mvc.Model;

public class ToCommand implements Command{
	
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
		model.addShapeOnIndex(endIndex, startShape);
		model.deleteShape(endShape);
		model.deleteShape(startShape);
		model.addShapeOnIndex(startIndex, endShape);
		
		
		
	}
	

	public String getDescription()
	{
		
		int startIndex =  model.getIndexOfShape(startShape);
		int endIndex = model.getIndexOfShape(endShape);
		if(startIndex>endIndex)
		{
			//toFront
			return "ToFrontCommand," + startShape;
		} else
			
		{
			return "ToBackCommand," + startShape;
		}
		
		
		
	}
	


}
