package paint.serialization;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.print.attribute.standard.Finishings;

import paint.commands.AddCommand;
import paint.commands.CommandManager;
import paint.commands.DeleteCommand;
import paint.geometry.Circle;
import paint.geometry.HexagonAdapter;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.geometry.Rectangle;
import paint.geometry.Shape;
import paint.geometry.Square;
import paint.mvc.Model;

public class LogStrategy implements Strategy {

	private String log;
	private Model model;
	private CommandManager commandManager;
	
	public  LogStrategy(String log, Model model, CommandManager  commandManager) {
		this.log=log;
		this.model=model;
		this.commandManager=commandManager;
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
		Scanner scanner = new Scanner(path);
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			String [] result =line.split(",");
			String command= result[0];
			if (command=="AddCommand")
			{
				Shape shape = parseShape(result);
				AddCommand  addCommand = new AddCommand(shape, model);
				commandManager.addCommand(addCommand);
				addCommand.execute();
				
			} else if (command=="DeleteCommand")
			{
				Shape shape = parseShape(result);
				DeleteCommand  deleteCommand = new DeleteCommand(shape, model);
				commandManager.addCommand(deleteCommand);
				deleteCommand.execute();
			}
			
		}
		
	}
	
	private Shape parseShape(String[] result)
	{
		Shape shape=null;
		String strShape = result[1];
		
		if(strShape=="Point")
		{
			int x =Integer.parseInt(result[2]) ;
			int y= Integer.parseInt(result[3]);
			int r=Integer.parseInt(result[4]);
			int g= Integer.parseInt(result[5]);
			int b= Integer.parseInt(result[6]);
			
			shape= new Point(x,y,new Color(r,g,b));
			
		} else if (strShape=="Line")
		{
			int x1 =Integer.parseInt(result[2]) ;
			int y1= Integer.parseInt(result[3]);
			int x2 =Integer.parseInt(result[4]) ;
			int y2= Integer.parseInt(result[5]);
			int r=Integer.parseInt(result[6]);
			int g= Integer.parseInt(result[7]);
			int b= Integer.parseInt(result[8]);
			shape = new Line(new Point(x1,x2), new Point(x2,y2), new Color(r, g, b));
			
		} else if (strShape=="Circle")
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
			
			shape = new Circle(new Point(x,y), r, new Color(r1,g2,b2), new Color(r2, g2, b2));
			
		} else if (strShape=="Rectangle")
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
			
		} else if(strShape=="Square")
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
			
		} else if (strShape=="Hexagon")
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
