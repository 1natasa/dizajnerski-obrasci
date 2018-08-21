package paint.mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import paint.commands.AddCommand;
import paint.commands.Command;
import paint.commands.CommandManager;
import paint.commands.DeleteCommand;
import paint.dialog.DialogCircleDrawing;
import paint.dialog.DialogSquareDrawing;
import paint.dialog.DialogRectangleDrawing;
import paint.geometry.Circle;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.geometry.Rectangle;
import paint.geometry.Shape;
import paint.geometry.Square;

public class Controller {
	
	private CommandManager commandManager;
	private Model model;
	private PaintFrame frame;
	
	private Color insideColor;
	private Color outsideColor;
	private Point firstPointOfLine;
	
	
	public Controller(Model model, PaintFrame frame)
	{
		this.model=model;
		this.frame=frame;
		this.insideColor=frame.getBtnInsideColor().getBackground();
		this.outsideColor=frame.getBtnOutsideColor().getBackground();
		this.commandManager = new CommandManager();
		
	}
	
	public void drawShape(MouseEvent e)
	{
		
		 Shape shape = null ;
		if (frame.getBtnPoint().isSelected())
		{
			int x=e.getX();
			int y=e.getY();
			shape = new Point (x, y, outsideColor);
			
			
			
			
			
	
		}
		else if (frame.getBtnLine().isSelected())
		{
			if(firstPointOfLine == null){
				 firstPointOfLine = new Point(e.getX(),e.getY());
			}else{
				
				Point secondPointOfLine = new Point(e.getX(),e.getY());
				shape  = new Line( firstPointOfLine,secondPointOfLine,outsideColor);
				
				firstPointOfLine= null;
			}
		    	
		}
		
		else if (frame.getBtnSqaure().isSelected())
		{
			Point point= new Point(e.getX(), e.getY());
			DialogSquareDrawing dk = new DialogSquareDrawing();
			dk.setVisible(true);
			shape =new Square(point, dk.getSideLength(), outsideColor,insideColor);
			
		}
		else if (frame.getBtnRectangle().isSelected())
		{
			
			Point point= new Point(e.getX(), e.getY());
			
			DialogRectangleDrawing dijalogPravougaonik = new DialogRectangleDrawing();
			dijalogPravougaonik.setVisible(true);
			shape= new Rectangle(point, dijalogPravougaonik.getSirina(),dijalogPravougaonik.getDuzina(), outsideColor,insideColor);
			
		}
		else if (frame.getBtnCircle().isSelected())
		{
			Point point= new Point(e.getX(), e.getY());
			DialogCircleDrawing dijalogKrug= new DialogCircleDrawing();
			dijalogKrug.setVisible(true);
			shape = new Circle(point, dijalogKrug.getRadius(), outsideColor,insideColor);
					
			
		}
		else if (frame.getBtnSelect().isSelected())
		{
			for(Shape s : model.getShapes())
			{
				if(s.contains(e.getX(),e.getY()))
				{
					if(s.isSelected())
					{
						s.setSelected(false);
					}
					else
					{
						s.setSelected(true);
					}
				}
			}
			
			
		}
		
		if (shape!=null)
		{
			AddCommand addCommand = new AddCommand(shape, model);
			commandManager.addCommand(addCommand);
			addCommand.execute();
		}
		frame.repaintView();
		
		
	}
	
	

	public void openInsideColorChooser() {
		// TODO Auto-generated method stub
		JColorChooser jcc = new JColorChooser();
		Color chooseColor =jcc.showDialog(null, "Choose inside color", Color.WHITE);
		frame.getBtnInsideColor().setBackground(chooseColor);
		insideColor=frame.getBtnInsideColor().getBackground();
	}
	
	public void openOutsideColorChooser()
	{
		JColorChooser jcc = new JColorChooser();
		Color choseColor = jcc.showDialog(null, "Chose inside color", Color.BLACK);
		frame.getBtnOutsideColor().setBackground(choseColor);
		outsideColor=frame.getBtnOutsideColor().getBackground();
	}
	
	
	public void deleteShapes()
	{
		int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure to delete selected shapes?");
		if (confirm != JOptionPane.YES_OPTION)
		{
			return;
		}
		for(Shape shape : model.getSelectedShapes())
		{
			
			DeleteCommand deleteCommand = new DeleteCommand(shape, model);
			commandManager.addCommand(deleteCommand);
			deleteCommand.execute();
		}
		
		frame.repaintView();
		
	}

}
