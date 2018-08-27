package paint.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class SurfaceShape extends Shape implements Serializable{
	private Color insideColor = Color.WHITE;
	
	public abstract void fill(Graphics g);

	
	public Color getInsideColor() {
		return insideColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}
	

}
