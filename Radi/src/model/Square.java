package model;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends SurfaceShape implements Moveable{
	protected Point topLeftPoint;
	protected int sideLength;
	protected String contourColor;
	protected String insideColor;
	protected String color;
	


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
		//this(goreLevo, duzinaStranice);
		//this.boja = boja;
	}

	public Square(Point topLeftPoint, int sideLength, Color contourColor, Color insideColor){
		this(topLeftPoint,sideLength,contourColor);
		setInsideColor(insideColor);
		//this(goreLevo, duzinaStranice);
		//this.boja = boja;
	}
	
	
	
	public Square (Point topLeftPoint, int sideLenght, String contourColor)
	{
		this.topLeftPoint=topLeftPoint;
		this.sideLength=sideLenght;
		this.color=contourColor;
	}
	
	
	
	
	
	public Square(Point topLeftPoint, int sideLength, String contourColor, String insideColor){
		this(topLeftPoint,sideLength);
		setColorStr(contourColor);
		setInsideColorStr(insideColor);
		//this(goreLevo, duzinaStranice);
		//this.boja = boja;
	}


	public Line diagonal(){
		return new Line(topLeftPoint, new Point(topLeftPoint.getX() + sideLength,topLeftPoint.getY() + sideLength));
	}

	public Point centerOfSquare(){
		return diagonal().middleOfLine();
	}

	public String toString(){
		return "Tacka gore levo=("+topLeftPoint.getX()+","+topLeftPoint.getY()+"), stranica="+sideLength + ", boja ivice=" + contourColor+ ", boja unutrasnjosti=" + insideColor  ;
	}
	
	public String description(){
		return "Tacka gore levo=("+topLeftPoint.getX()+","+topLeftPoint.getY()+"), stranica="+sideLength + ", boja konture: \"" + getColorStr() + "\", boja unutrasnjosti: \"" + getInsideColorStr() + "\"";
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
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
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

	public int compareTo(Object o) {
		if(o instanceof Square){
			Square extra  = (Square) o;
			return this.area() - extra.area();
		}
		else 
			return 0;
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
		return sideLength=sideLength;
	}

	


}
