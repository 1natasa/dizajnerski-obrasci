package paint.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends SurfaceShape implements Moveable{
	
	protected Point topLeftPoint;
	protected int sideLength;

	public Square(){

	}
	public Square(Point topLeftPoint, int sideLength){
		this.topLeftPoint = topLeftPoint;
		this.sideLength = sideLength;
	}
	public Square(Point topLeftPoint, int sideLength, Color color){
		setColor(color);
		this.topLeftPoint = topLeftPoint;
		this.sideLength = sideLength;
	}

	public Square(Point topLeftPoint, int sideLength, Color contourColor, Color insideColor){
		this(topLeftPoint,sideLength,contourColor);
		setInsideColor(insideColor);

	}
	

	public Line diagonal(){
		return new Line(topLeftPoint, new Point(topLeftPoint.getX() + sideLength,topLeftPoint.getY() + sideLength));
	}

	public Point centerOfSquare(){
		return diagonal().middleOfLine();
	}


	public void moveTo(int x, int y) {
		topLeftPoint.setX(x);
		topLeftPoint.setY(y);
	}

	public void moveBy(int x, int y) {
		topLeftPoint.setX(topLeftPoint.getX()+x);
		topLeftPoint.setY(topLeftPoint.getY()+y);
	}

	public int perimeter(){
		return 4 * sideLength;
	}
	public int area(){
		return sideLength * sideLength;
	}
	
	public boolean contains(int x, int y) {
		if(this.getTopLeftPoint().getX()<=x 
				&& x<=(this.getTopLeftPoint().getX() + sideLength)
				&& this.getTopLeftPoint().getY()<=y 
				&& y<=(this.getTopLeftPoint().getY() + sideLength))
			return true;
		else 
			return false;

	}
	
	public boolean equals(Object obj){
		if(obj instanceof Square){
			Square extra=(Square) obj;
			if(this.topLeftPoint.equals(extra.topLeftPoint) && this.sideLength==extra.sideLength)
				return true;
			else
				return false;
		}
		else 
			return false;
	}
	
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(getTopLeftPoint(), new Point(getTopLeftPoint().getX()+sideLength, getTopLeftPoint().getY())).selected(g);
		new Line(getTopLeftPoint(), new Point(getTopLeftPoint().getX(), getTopLeftPoint().getY()+sideLength)).selected(g);
		new Line(new Point(getTopLeftPoint().getX()+sideLength, getTopLeftPoint().getY()), diagonal().getEnd()).selected(g);
		new Line(new Point(getTopLeftPoint().getX(), getTopLeftPoint().getY()+sideLength), diagonal().getEnd()).selected(g);

	}
	public void drawShape(Graphics g){
		g.setColor(getColor());
		g.drawRect(topLeftPoint.getX(), topLeftPoint.getY(), sideLength, sideLength);
		fill(g);
		if(isSelected())
			selected(g);
	}
	public void fill(Graphics g){
		g.setColor(getInsideColor());
		g.fillRect(topLeftPoint.getX()+1, topLeftPoint.getY()+1, sideLength-1, sideLength-1);
	}

	public Point getTopLeftPoint() {
		return topLeftPoint;
	}
	public int getSideLength() {
		return sideLength;
	}
	public void setTopLeftPoint(Point topLeftPoint) {
		this.topLeftPoint = topLeftPoint;
	}
	
	public int setSideLength(int sideLength) {
		return this.sideLength=sideLength;
	}

	


}
