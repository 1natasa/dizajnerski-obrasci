package paint.mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import paint.dialog.DijalogKrugCrtanje;
import paint.dialog.DijalogKvadrataCrtanje;
import paint.dialog.DijalogPravougaonikCrtanje;
import paint.geometry.Circle;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.geometry.Rectangle;
import paint.geometry.Shape;
import paint.geometry.Square;

public class Controller {
	
	private Model model;
	private PaintFrame frame;
	
	private Color insideColor;
	private Color outsideColor;
	private Point firstPointOfLine;
	
	
	public Controller(Model model, PaintFrame frame)
	{
		this.model=model;
		this.frame=frame;
		
	}
	
	public void drawShape(MouseEvent e)
	{
		if (frame.getBtnPoint().isSelected())
		{
			int x=e.getX();
			int y=e.getY();
			Point point = new Point (x, y, outsideColor);
			
			model.addShape(point);
			
	
		}
		else if (frame.getBtnLine().isSelected())
		{
			if(firstPointOfLine == null){
				 firstPointOfLine = new Point(e.getX(),e.getY());
			}else{
				
				Point secondPointOfLine = new Point(e.getX(),e.getY());
				Line line  = new Line( firstPointOfLine,secondPointOfLine,outsideColor);
				model.addShape(line);
				firstPointOfLine= null;
			}
		    	
		}
		
		else if (frame.getBtnSqaure().isSelected())
		{
			Point point= new Point(e.getX(), e.getY());
			DijalogKvadrataCrtanje dk = new DijalogKvadrataCrtanje();
			dk.setVisible(true);
			Square square =new Square(point, dk.getDuzinaStranice(), outsideColor,insideColor);
			model.addShape(square);
		}
		else if (frame.getBtnRectangle().isSelected())
		{
			
			Point point= new Point(e.getX(), e.getY());
			
			DijalogPravougaonikCrtanje dijalogPravougaonik = new DijalogPravougaonikCrtanje();
			dijalogPravougaonik.setVisible(true);
			Rectangle rectangle= new Rectangle(point, dijalogPravougaonik.getSirina(),dijalogPravougaonik.getDuzina(), outsideColor,insideColor);
			model.addShape(rectangle);
		}
		else if (frame.getBtnCircle().isSelected())
		{
			Point point= new Point(e.getX(), e.getY());
			DijalogKrugCrtanje dijalogKrug= new DijalogKrugCrtanje();
			dijalogKrug.setVisible(true);
			Circle circle = new Circle(point, dijalogKrug.getPoluprecnik(), outsideColor,insideColor);
			model.addShape(circle);;		
			
		}
		else if (frame.getBtnSelect().isSelected())
		{
			for(Shape shape : model.getShapes())
			{
				if(shape.contains(e.getX(),e.getY()))
				{
					if(shape.isSelected())
					{
						shape.setSelected(false);
					}
					else
					{
						shape.setSelected(true);
					}
				}
			}
			
			
		}
		
		frame.repaintView();
		
		
	}
	

}
