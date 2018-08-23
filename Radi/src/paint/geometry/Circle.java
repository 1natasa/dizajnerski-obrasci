package paint.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.security.GeneralSecurityException;

public class Circle extends SurfaceShape implements Moveable{
	private Point center;
	private int r;


	public Circle(){

	}
	public Circle(Point center, int r){
		this.center = center;
		this.r = r;
	}
	public Circle(Point center, int r, Color color){
		this(center, r);
		setColor(color);
	}
	
	public Circle(Point center, int r, Color contourColor, Color insideColor){
		this(center, r, contourColor);
		setInsideColor(insideColor);
	}

	public String toString(){
		return  "Circle" + "," + center.getX() + "," + center.getY() + "," + r+","+ getColor().getRed() + ","  + getColor().getGreen() + "," + getColor().getBlue() + "," + getInsideColor().getRed() + "," + getInsideColor().getGreen() + "," + getInsideColor().getBlue() ;
	}

	public void moveTo(int x, int y) {
		center.setX(x);
		center.setY(y);
	}
	public void moveBy(int x, int y) {
		center.setX(center.getX()+x);
		center.setY(center.getY()+y);
	}
	public double area(){
		return r * r * Math.PI;
	}
	public double perimeter(){
		return 2 * r * Math.PI;
	}
	public boolean contains(int x, int y){
		Point pointOfClick = new Point(x, y);
		if(pointOfClick.distance(center)<=r)
			return true;
		else
			return false;
		
	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		new Line(new Point(center.getX(), center.getY()-r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX()-r, center.getY()), new Point(center.getX()+r, center.getY())).selected(g);
	}
	public void drawShape(Graphics g){
		g.setColor(getColor());
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, r*2);
		if(isSelected())
			selected(g);
	}
	public void fill(Graphics g){
		g.setColor(getInsideColor());
		g.fillOval(center.getX()-r, center.getY()-r, 2*r, r*2);
	}

	
	public Point getCentar() {
		return center;
	}
	public int getR() {
		return r;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	




}
