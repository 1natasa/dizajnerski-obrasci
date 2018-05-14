package model;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape{

	private Hexagon hexagon;
	
	public HexagonAdapter(Point p, int r, Color contourColor,Color insideColor ) {
		hexagon= new Hexagon (p.getX(), p.getY(),r);
		hexagon.setBorderColor(contourColor); //setBorderColor je funckija iz Hexagona
		hexagon.setAreaColor(insideColor); //setAreaColor je funkcija iz Hexagona
		
		
	}
	
	//povrsina heksagona
	//moramo da konvertujemo u int zato sto math.sqrt vraca decimalan broj
	public int area()
	{
		return (int)((6*hexagon.getR()*hexagon.getR()*Math.sqrt(3))/4);
	}
	
	//obim heksagona
	
	public int parimeter()
	{
		return 6* hexagon.getR();
	}
	
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		//extraHexagon je dodatni heksagon, pomocni
		if(o instanceof Hexagon) {
			HexagonAdapter extraHexagon = (HexagonAdapter) o;
			return (int)(this.area() - extraHexagon.area());
		}
		else
			return 0;
		
	}

	@Override
	public void fill(Graphics g) {
		
	}

	@Override
	public void drawShape(Graphics g) {
		hexagon.paint(g);
		
	}

	@Override
	public void selected(Graphics g) {
		hexagon.setSelected(true);
	}

	@Override
	public boolean contains(int x, int y) {
		 return hexagon.doesContain(x, y);
	
	}

	@Override
	public void moveTo(int x, int y) {
		
		hexagon.setX(x);
		hexagon.setY(y);
		
	}

	@Override
	public void moveBy(int x, int y) {
		
		hexagon.setX(hexagon.getX() + x);
		hexagon.setY(hexagon.getY()+y);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	
	
	

}
