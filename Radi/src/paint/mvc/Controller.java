package paint.mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import paint.commands.AddCommand;
import paint.commands.BringToBack;
import paint.commands.BringToFrontCommand;
import paint.commands.ToCommand;
import paint.commands.Command;
import paint.commands.CommandManager;
import paint.commands.DeleteCommand;
import paint.commands.ModificationCommand;
import paint.dialog.DialogCircleDrawing;
import paint.dialog.DialogCircleModification;
import paint.dialog.DialogHexagonDrawing;
import paint.dialog.DialogHexagonModification;
import paint.dialog.DialogLineModification;
import paint.dialog.DialogPointModification;
import paint.dialog.DialogSquareDrawing;
import paint.dialog.DialogSquareModification;
import paint.dialog.DialogRectangleDrawing;
import paint.dialog.DialogRectangleModification;
import paint.geometry.Circle;
import paint.geometry.HexagonAdapter;
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
	private boolean buttonClicked;
	
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
			DialogSquareDrawing dialogSquare = new DialogSquareDrawing();
			dialogSquare.setVisible(true);
			shape =new Square(point, dialogSquare.getSideLength(), outsideColor,insideColor);
			
		}
		else if (frame.getBtnRectangle().isSelected())
		{
			
			Point point= new Point(e.getX(), e.getY());
			
			DialogRectangleDrawing dialogRectangle = new DialogRectangleDrawing();
			dialogRectangle.setVisible(true);
			shape= new Rectangle(point, dialogRectangle.getSirina(),dialogRectangle.getDuzina(), outsideColor,insideColor);
			
		}
		else if (frame.getBtnCircle().isSelected())
		{
			Point point= new Point(e.getX(), e.getY());
			DialogCircleDrawing dialogCircle= new DialogCircleDrawing();
			dialogCircle.setVisible(true);
			shape = new Circle(point, dialogCircle.getRadius(), outsideColor,insideColor);
				
			
		} else if (frame.getBtnHexagon().isSelected())
		{
			Point point = new Point(e.getX(),e.getY());
			DialogHexagonDrawing dialogHexagon = new DialogHexagonDrawing();
			dialogHexagon.setVisible(true);
			shape = new HexagonAdapter(point, dialogHexagon.getRadius(), outsideColor, insideColor);
			
		}
		else if (frame.getBtnSelect().isSelected())
		{
			
			boolean outOfShape =true;
			for(Shape s : model.getShapes())
			{
				if(s.contains(e.getX(),e.getY()))
				{
					//ako sadrzi tu tacku i ako je selektovan, odsekejtuj ga
					if(s.isSelected())
					{
						//s.setSelected(false);
						model.setSelection(s,false);
					}
					else
					{
						//ako sadrzi tacku i ako nije selektova, selektuj ga
						//s.setSelected(true);
						model.setSelection(s, true);
					}
					
					outOfShape=false;
				}
				
				
				
			}
			
			if (outOfShape)
			{
				model.unselectShapes();
			} 
			
			
		}
		
		if (shape!=null)
		{
			AddCommand addCommand = new AddCommand(shape, model);
			commandManager.addCommand(addCommand);
			addCommand.execute();
			
			model.unselectShapes();
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

	public void modificationShapes()
	{
		for (Shape shape : model.getSelectedShapes())
		{
			if (shape instanceof Point)
			{
				Point oldPoint = (Point) shape;
				DialogPointModification dialogPoint = new DialogPointModification(oldPoint);
				dialogPoint.setVisible(true);
				
				if(dialogPoint.getData()==null)
				{
					
				} else
				{
					Point newPoint = dialogPoint.getData();
					ModificationCommand modificatioCommand = new ModificationCommand(oldPoint, newPoint, model);
					commandManager.addCommand(modificatioCommand);
					modificatioCommand.execute();
					/*model.deleteSelectedShapes(extra);
					Point newPoint = dialogPoint.getData();
					model.addShape(newPoint);*/
					
					
				}
			}  else if (shape instanceof Line)
			{
				Line oldLine = (Line) shape;
				DialogLineModification dialogLine = new DialogLineModification(oldLine);
				dialogLine.setVisible(true);
				
				if(dialogLine.getData()==null)
				{
					
				} else {
					Line newLine = dialogLine.getData();
					ModificationCommand modificationCommand = new ModificationCommand(oldLine, newLine, model);
					commandManager.addCommand(modificationCommand);
					modificationCommand.execute();
				}
			} else if(shape instanceof Rectangle)
			{
				Rectangle oldRectangle = (Rectangle) shape;
				DialogRectangleModification dialogRectangle = new DialogRectangleModification(oldRectangle);
				dialogRectangle.setVisible(true);
				
				if(dialogRectangle.getData()==null)
				{
					
				} else
				{
					Rectangle newRectangle = dialogRectangle.getData();
					ModificationCommand modificationCommand = new ModificationCommand(oldRectangle, newRectangle, model);
					commandManager.addCommand(modificationCommand);
					modificationCommand.execute();
				}
			}else if (shape instanceof Square )
			{
				Square oldSquare =(Square) shape;
				DialogSquareModification dialogSquare = new DialogSquareModification(oldSquare);
				dialogSquare.setVisible(true);
				
				if(dialogSquare.getData()== null)
				{
					
				} else
				{
					Square newSquare = dialogSquare.getData();
					ModificationCommand modificationCommand = new ModificationCommand(oldSquare, newSquare, model);
					commandManager.addCommand(modificationCommand);
					modificationCommand.execute();
				}
			}  else if (shape instanceof Circle)
			{
				Circle oldCircle =(Circle) shape;
				DialogCircleModification dialogCircle = new DialogCircleModification(oldCircle);
				dialogCircle.setVisible(true);
				
				if(dialogCircle.getData()==null)
				{
					
				} else
				{
					Circle newCircle = dialogCircle.getData();
					ModificationCommand modificationCommand= new ModificationCommand(oldCircle, newCircle, model);
					commandManager.addCommand(modificationCommand);
					modificationCommand.execute();
				}
			} else if (shape instanceof HexagonAdapter)
			{
				HexagonAdapter oldHexagon = (HexagonAdapter) shape;
				DialogHexagonModification dialogHexagon = new DialogHexagonModification(oldHexagon);
				dialogHexagon.setVisible(true);
				
				if(dialogHexagon.getData()==null)
				{
					
				} else
				{
					HexagonAdapter newHexagon = dialogHexagon.getData();
					ModificationCommand modificationCommand = new ModificationCommand(oldHexagon, newHexagon, model);
					commandManager.addCommand(modificationCommand);
					modificationCommand.execute();
				}
			}
			
			
		}
		
		frame.repaintView();
		
	}
	
	public void toFront()
	{
		Shape selectedShape = model.getSelectedShapes().get(0);
		int indexOfSelectedShape = model.getIndexOfShape(selectedShape);
		Shape endShape = model.getShapeOnIndex(indexOfSelectedShape+1);
		ToCommand toCommand = new ToCommand(model, selectedShape, endShape);
		commandManager.addCommand(toCommand);
		toCommand.execute();
		frame.repaintView();
	}
	
	public void toBack()
	{
		Shape selectedShape = model.getSelectedShapes().get(0);
		int indexOfSelectedShape = model.getIndexOfShape(selectedShape);
		Shape endShape = model.getShapeOnIndex(indexOfSelectedShape-1);
		
		ToCommand toCommand = new ToCommand(model, selectedShape, endShape);
		commandManager.addCommand(toCommand);
		toCommand.execute();
		frame.repaintView();
	}
	
	public void bringToFront()
	{
		BringToFrontCommand bringToFront = new BringToFrontCommand(model, model.getSelectedShapes().get(0));
		commandManager.addCommand(bringToFront);
		bringToFront.execute();
		frame.repaintView();
		
	}
	
	public void bringToBack()
	{
		BringToBack bringToBack = new BringToBack(model, model.getSelectedShapes().get(0));
		commandManager.addCommand(bringToBack);
		bringToBack.execute();
		frame.repaintView();
				
	}
	
	public void undo()
	{
		Command command= commandManager.getCommandForUndo();
		command.unexecute();
		commandManager.decrementIndex();
		frame.repaint();
	}
	
	public void redo()
	{
		Command command= commandManager.getCommandForRedo();
		command.execute();
		commandManager.incrementIndex();
		frame.repaint();
	}
}
