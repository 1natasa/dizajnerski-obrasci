package paint.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape implements Moveable,Serializable{

	private Hexagon hexagon;
	
	
	public HexagonAdapter(Point p, int r, Color contourColor,Color insideColor ) {
		hexagon= new Hexagon (p.getX(), p.getY(),r);
		hexagon.setBorderColor(contourColor); //setBorderColor je funckija iz Hexagona
		hexagon.setAreaColor(insideColor); //setAreaColor je funkcija iz Hexagona
		setColor(contourColor);
		setInsideColor(insideColor);
		
		
	}
	

	@Override
	public void fill(Graphics g) {
		
	}
	
	public String toString(){
		return  "Hexagon," + hexagon.getX() + "," + hexagon.getY() + "," + hexagon.getR() + "," + hexagon.getBorderColor().getRed() + ","  + hexagon.getBorderColor().getGreen() + "," + hexagon.getBorderColor().getBlue() + "," + hexagon.getAreaColor().getRed() + "," + hexagon.getAreaColor().getGreen() + "," + hexagon.getAreaColor().getBlue() ;
	}

	@Override
	public void drawShape(Graphics g) {
		
		hexagon.paint(g);
		if(isSelected())
		{
			selected(g);
		}
		
	}

	@Override
	public void selected(Graphics g) {
		//hexagon.setSelected(true);
		new Line(new Point(hexagon.getX() + hexagon.getR()/2, hexagon.getY() - (int) (hexagon.getR()*1.74/2) ), new Point(hexagon.getX() - hexagon.getR()/2, hexagon.getY() + (int) (hexagon.getR()*1.74/2))).selected(g);
		new Line(new Point(hexagon.getX() - hexagon.getR()/2, hexagon.getY() - (int) (hexagon.getR()*1.74/2) ), new Point(hexagon.getX() + hexagon.getR()/2, hexagon.getY() + (int) (hexagon.getR()*1.74/2))).selected(g);
		new Line(new Point(hexagon.getX()-hexagon.getR(), hexagon.getY()), new Point(hexagon.getX()+hexagon.getR(), hexagon.getY())).selected(g);
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


	public boolean equals(Object obj){
		if(obj instanceof HexagonAdapter){
			HexagonAdapter extra=(HexagonAdapter) obj;
			if(this.hexagon.getX()==extra.getX()&& this.hexagon.getY()==extra.getY() && this.hexagon.getR()==extra.getR() && 
					this.hexagon.getBorderColor().getRed()==extra.getColor().getRed() && this.hexagon.getBorderColor().getGreen()==extra.getColor().getGreen() 
					&&this.hexagon.getBorderColor().getBlue()==extra.getColor().getBlue() && this.hexagon.getAreaColor().getRed()==extra.getInsideColor().getRed() 
					&& this.hexagon.getAreaColor().getGreen()==extra.getInsideColor().getGreen() && this.hexagon.getAreaColor().getBlue()==extra.getInsideColor().getBlue())
				return true;
			else
				return false;
		}
		else 
			return false;
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
