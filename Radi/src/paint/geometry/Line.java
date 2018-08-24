package paint.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Line extends Shape implements Moveable,Serializable{
	private Point tStart;
	private Point tEnd;

	public Line(){

	}
	public Line(Point tStart, Point tEnd){
		this.tStart = tStart;
		this.tEnd = tEnd;
	}

	public Line(Point tStart, Point tEnd, Color color){
		this(tStart, tEnd);
		setColor(color);
	}

	public Point middleOfLine(){
		int middleX = (tStart.getX() + tEnd.getX()) / 2;
		int middleY = (tStart.getY() + tEnd.getY()) / 2;
		return new Point(middleX, middleY);
	}

	public boolean equals(Object obj){
		if(obj instanceof Line){
			Line extraLine=(Line) obj;
			if(tStart.getX() == extraLine.getStart().getX() && tStart.getY()==extraLine.getStart().getY() && tEnd.getX()==extraLine.getEnd().getX()&&tEnd.getY()==extraLine.getEnd().getY()&&  this.getColor().equals(extraLine.getColor()))
				return true;
			else
				return false;
		}
		else 
			return false;
	}


	public double lenght(){
		return tStart.distance(tEnd);
	}

	public void moveBy(int x, int y){
		tStart.setX(tStart.getX()+x);
		tStart.setY(tStart.getY()+y);
		tEnd.setX(tEnd.getX()+x);
		tEnd.setY(tEnd.getY()+y);

	}
	
	public void moveTo(int x, int y){
		int rx = tStart.getX() - tEnd.getX();
		int ry = tStart.getY() - tEnd.getY();
		tStart.setX(x);
		tStart.setY(y);
		tEnd.setX(x+rx);
		tEnd.setY(y+ry);
		
		
	}
	
	public String toString(){
		return "Line," + tStart.getX()+"," +tStart.getY()+"," + tEnd.getX()+","+ tEnd.getY() +","+ getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
	}
	
	public boolean contains(int x, int y){
		Point placeOfClick = new Point(x, y);
		if(placeOfClick.distance(tStart)+placeOfClick.distance(tEnd)-this.lenght()<=0.5)
			return true;
		 else 
			return false;		
	}
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		tStart.selected(g);
		tEnd.selected(g);
		middleOfLine().selected(g);
	}
	public void drawShape(Graphics g){
		g.setColor(getColor());
		g.drawLine(tStart.getX(), tStart.getY(), tEnd.getX(), tEnd.getY());
		if(isSelected())
			selected(g);
	}

	
	public Point getStart(){
		return tStart;
	}
	public Point getEnd(){
		return tEnd;
	}
	public void setStart(Point tStart){
		this.tStart = tStart;
	}
	public void setEnd(Point tEnd){
		this.tEnd = tEnd;
	}
	

	

}
