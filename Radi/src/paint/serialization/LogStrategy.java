package paint.serialization;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.Finishings;

import paint.commands.AddCommand;
import paint.commands.BringToBackCommand;
import paint.commands.BringToFrontCommand;
import paint.commands.Command;
import paint.commands.CommandManager;
import paint.commands.DeleteCommand;
import paint.commands.ModificationCommand;
import paint.commands.ToCommand;
import paint.geometry.Circle;
import paint.geometry.HexagonAdapter;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.geometry.Rectangle;
import paint.geometry.Shape;
import paint.geometry.Square;
import paint.mvc.Controller;
import paint.mvc.Model;
import paint.mvc.PaintFrame;

public class LogStrategy implements Strategy {

	private String log;
	private List<String> logForExecute;
	private int index=0;
	private Model model;
	private CommandManager commandManager;
	
	public  LogStrategy(String log, Model model, CommandManager  commandManager) {
		this.log=log;
		this.model=model;
		this.commandManager=commandManager;
		logForExecute = new ArrayList<>();
	}
	
	@Override
	public void save(String path) {
		PrintWriter pw =null;
		try {
			pw = new PrintWriter(path);
			pw.write(log);
		
		} catch (FileNotFoundException e) {
			System.out.println("Error");
		} finally {
			if (pw!=null)
			{
				pw.close();
			}
		}
		
	}

	@Override
	public void load(String path) {
		Scanner scanner=null;
		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scanner.hasNextLine())
		{
			
			logForExecute.add(scanner.nextLine());
		}
		
	}
	
	public boolean hasNextLine()
	{
		return index < logForExecute.size();
	}
	
	public String executeOneLine()
	{
		String line = logForExecute.get(index);
		index++;
		String [] result =line.split(",");
		String command= result[0];
		if (command.equals("AddCommand"))
		{
			Shape shape = parseShape(result);
			AddCommand  addCommand = new AddCommand(shape, model);
			commandManager.addCommand(addCommand);
			addCommand.execute();
			
		} else if (command.equals("DeleteCommand"))
		{
			Shape shape = parseShape(result);
			DeleteCommand  deleteCommand = new DeleteCommand(model.getSelectedShapes(), model);
			commandManager.addCommand(deleteCommand);
			deleteCommand.execute();
		} else if (command.equals("ModificationCommand"))
		{
			
			Shape oldShape = parseShape(result);
			int i;
			String str;
			for (i=0; i<result.length; i++)
			{
				if (result[i].equals("-->"))
				{
					break;
				}
			}
			int j=0;
			
			String [] secondResult = new String[result.length-i];
			for(;i<result.length; i++)
			{
				secondResult[j]=result[i];
				j++;
			}
			
			Shape newShape = parseShape(secondResult);
			ModificationCommand modificationCommand= new ModificationCommand(oldShape, newShape, model);
			commandManager.addCommand(modificationCommand);
			modificationCommand.execute();
			
			
		} else if (command.equals("BringToBackCommand"))
		{
			Shape shape = parseShape(result);
			BringToBackCommand bringToback = new BringToBackCommand(model, shape);
			commandManager.addCommand(bringToback);
			bringToback.execute();
		} else if (command.equals("BringToFrontCommand"))
		{
			Shape shape = parseShape(result);
			BringToFrontCommand bringToFront = new BringToFrontCommand(model, shape);
			commandManager.addCommand(bringToFront);
			bringToFront.execute();
		} else if (command.equals("ToFrontCommand"))
		{
			Shape startShape = parseShape(result);
			int secondShapeIndex= model.getIndexOfShape(startShape)+1;
			
			ToCommand toFront = new ToCommand(model, startShape, model.getShapeOnIndex(secondShapeIndex));
			commandManager.addCommand(toFront);
			toFront.execute();
		} else if (command.equals("ToBackCommand"))
		{
			Shape startShape = parseShape(result);
			int secondShapeIndex= model.getIndexOfShape(startShape)-1;
			
			ToCommand toBack = new ToCommand(model, startShape, model.getShapeOnIndex(secondShapeIndex));
			commandManager.addCommand(toBack);
			toBack.execute();
		} else if(command.equals("Select"))
		{
			Shape shape = parseShape(result);
			
			model.setSelection(shape, true);
		} else if (command.equals("Unselect"))
		{
			Shape shape = parseShape(result);
			model.setSelection(shape, false);
		} else if (command.equals("Undo"))
		{
			Command commandUndo= commandManager.getCommandForUndo();
			commandUndo.unexecute();
			commandManager.decrementIndex();
			
			
		} else if (command.equals("Redo"))
		{

			Command commandRedo= commandManager.getCommandForRedo();
			commandRedo.execute();
			commandManager.incrementIndex();
		}
		return line;
	}
	
	private Shape parseShape(String[] result)
	{
		Shape shape=null;
		String strShape = result[1];
		
		if(strShape.equals("Point"))
		{
			int x =Integer.parseInt(result[2]) ;
			int y= Integer.parseInt(result[3]);
			int r=Integer.parseInt(result[4]);
			int g= Integer.parseInt(result[5]);
			int b= Integer.parseInt(result[6]);
			
			shape= new Point(x,y,new Color(r,g,b));
			
		} else if (strShape.equals("Line"))
		{
			int x1 =Integer.parseInt(result[2]) ;
			int y1= Integer.parseInt(result[3]);
			int x2 =Integer.parseInt(result[4]) ;
			int y2= Integer.parseInt(result[5]);
			int r=Integer.parseInt(result[6]);
			int g= Integer.parseInt(result[7]);
			int b= Integer.parseInt(result[8]);
			shape = new Line(new Point(x1,x2), new Point(x2,y2), new Color(r, g, b));
			
		} else if (strShape.equals("Circle"))
		{
			
			int x =Integer.parseInt(result[2]) ;
			int y= Integer.parseInt(result[3]);
			int r =Integer.parseInt(result[4]) ;
			
			//outside
			int r1=Integer.parseInt(result[5]);
			int g1= Integer.parseInt(result[6]);
			int b1= Integer.parseInt(result[7]);
			//inside
			
			int r2=Integer.parseInt(result[8]);
			int g2= Integer.parseInt(result[9]);
			int b2= Integer.parseInt(result[10]);
			
			shape = new Circle(new Point(x,y), r, new Color(r1,g1,b1), new Color(r2, g2, b2));
			
		} else if (strShape.equals("Rectangle"))
		{
			int x =Integer.parseInt(result[2]) ;
			int y= Integer.parseInt(result[3]);
			int width =Integer.parseInt(result[4]) ;
			int height = Integer.parseInt(result[5]);
					
			//outside
			int r1=Integer.parseInt(result[6]);
			int g1= Integer.parseInt(result[7]);
			int b1= Integer.parseInt(result[8]);
			//inside
			
			int r2=Integer.parseInt(result[9]);
			int g2= Integer.parseInt(result[10]);
			int b2= Integer.parseInt(result[11]);
			shape =new Rectangle(new Point(x,y), width,height, new Color(r1, g1, b1), new Color(r2, g2, b2));
			
		} else if(strShape.equals("Square"))
		{
			int x =Integer.parseInt(result[2]) ;
			int y= Integer.parseInt(result[3]);
			int side =Integer.parseInt(result[4]) ;
			
			//outside
			int r1=Integer.parseInt(result[5]);
			int g1= Integer.parseInt(result[6]);
			int b1= Integer.parseInt(result[7]);
			//inside
			
			int r2=Integer.parseInt(result[8]);
			int g2= Integer.parseInt(result[9]);
			int b2= Integer.parseInt(result[10]);
			
			shape=  new Square(new Point(x,y), side, new Color(r1, g1, b1), new Color(r2, g2, b2));
			
		} else if (strShape.equals("Hexagon"))
		{
			int x =Integer.parseInt(result[2]) ;
			int y= Integer.parseInt(result[3]);
			int r =Integer.parseInt(result[4]) ;
			
			//outside
			int r1=Integer.parseInt(result[5]);
			int g1= Integer.parseInt(result[6]);
			int b1= Integer.parseInt(result[7]);
			//inside
			
			int r2=Integer.parseInt(result[8]);
			int g2= Integer.parseInt(result[9]);
			int b2= Integer.parseInt(result[10]);
			
			shape = new HexagonAdapter(new Point(x,y),r, new Color(r1, g1, b1), new Color(r2, g2, b2));
			
		}
		return shape;
	}
	
}
