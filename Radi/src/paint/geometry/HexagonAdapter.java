package paint.geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape implements Moveable{

	private Hexagon hexagon;
	
	
	public HexagonAdapter(Point p, int r, Color contourColor,Color insideColor ) {
		hexagon= new Hexagon (p.getX(), p.getY(),r);
		hexagon.setBorderColor(contourColor); //setBorderColor je funckija iz Hexagona
		hexagon.setAreaColor(insideColor); //setAreaColor je funkcija iz Hexagona
		
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

	
	public int getR ()
	{
		return hexagon.getR();
	}
	
	
	public void setR(int r)
	{
		hexagon.setR(r);
	}
	
	
	public int getX ()
	{
		return hexagon.getX();
	}
	

	public void setX(int x)
	{
		hexagon.setX(x);
	}
	
	public int getY ()
	{
		return hexagon.getY();
	}
	

	public void setY(int y)
	{
		hexagon.setY(y);
	}
}
