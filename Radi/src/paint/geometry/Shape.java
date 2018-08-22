package paint.geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
	private Color color = Color.BLACK;
	private boolean selected=false;
	
	public abstract void drawShape(Graphics g);
	public abstract void selected(Graphics g);
	public abstract boolean contains(int x, int y);

	public Shape(){

	}
	public Shape(Color color){
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	

}
