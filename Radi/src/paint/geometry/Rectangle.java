package paint.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangle extends Square implements Serializable{
	private int width;


	public Rectangle(){

	}
	public Rectangle(Point topLeftPoint, int width, int height){
		super(topLeftPoint, height);
		this.width = width;
	}
	public Rectangle(Point topLeftPoint, int width, int height, Color color){
		this(topLeftPoint, width, height);
		setColor(color);
	}

	public Rectangle(Point topLeftPoint, int width, int height, Color contourColor, Color insideColor){
		this(topLeftPoint, width, height, contourColor);
		setInsideColor(insideColor);
	}

	public Line diagonal(){
		return new Line(topLeftPoint, new Point(topLeftPoint.getX() + width,topLeftPoint.getY() + sideLength));
	}
	
	public String toString(){
		return  "Rectangle,"  + topLeftPoint.getX() + "," + topLeftPoint.getY() + "," + width + ","+getHeight() +","+ getColor().getRed() + ","  + getColor().getGreen() + "," + getColor().getBlue() + "," + getInsideColor().getRed() + "," + getInsideColor().getGreen() + "," + getInsideColor().getBlue() ;
	}

	public void moveTo(int x, int y) {
		topLeftPoint.setX(x);
		topLeftPoint.setY(y);
	}
	public void moveBy(int x, int y) {
		topLeftPoint.setX(topLeftPoint.getX()+x);
		topLeftPoint.setY(topLeftPoint.getY()+y);

	}

	public Point centerOfRectangle(){
		return diagonal().middleOfLine();
	}


 
	
	
	public boolean equals(Object obj){
		if(obj instanceof Rectangle){
			Rectangle extra=(Rectangle) obj;
			if(topLeftPoint.getX()==extra.getTopLeftPoint().getX() && topLeftPoint.getY()==extra.getTopLeftPoint().getY()&& this.sideLength==extra.sideLength && this.width==extra.width && getColor().equals(extra.getColor()) && getInsideColor().equals(extra.getInsideColor()))
				return true;
			else
				return false;
		}
		else 
			return false;
	}


	public int area(){
		return sideLength * width;
	}
	public int perimeter(){
		return  2 * sideLength + 2 * width;
	}
	public boolean contains(int x, int y) {
		if(this.getTopLeftPoint().getX()<=x 
				&& x<=(this.getTopLeftPoint().getX() + width)
				&& this.getTopLeftPoint().getY()<=y 
				&& y<=(this.getTopLeftPoint().getY() + sideLength))
			return true;
		else 
			return false;

	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		new Line(getTopLeftPoint(), new Point(getTopLeftPoint().getX()+width, getTopLeftPoint().getY())).selected(g);
		new Line(getTopLeftPoint(), new Point(getTopLeftPoint().getX(), getTopLeftPoint().getY()+sideLength)).selected(g);
		new Line(new Point(getTopLeftPoint().getX()+width, getTopLeftPoint().getY()), diagonal().getEnd()).selected(g);
		new Line(new Point(getTopLeftPoint().getX(), getTopLeftPoint().getY()+sideLength), diagonal().getEnd()).selected(g);
	}
	public void drawShape(Graphics g){
		g.setColor(getColor());
		g.drawRect(topLeftPoint.getX(), topLeftPoint.getY(), width, sideLength);
		fill(g);
		if(isSelected())
			selected(g);
	}
	public void fill(Graphics g){
		g.setColor(getInsideColor());
		g.fillRect(topLeftPoint.getX()+1, topLeftPoint.getY()+1, width-1, sideLength-1);
	}

	public int getHeight() {
		return sideLength;
	}
	public int getWidth() {
		return width;
	}

	public void setHeight(int height){
		
			this.sideLength = height;
	}
	public void setWidth(int width) {

			this.width = width;
		
	}
	
	

}
