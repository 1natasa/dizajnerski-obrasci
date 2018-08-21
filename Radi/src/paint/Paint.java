package paint;

import javax.swing.JFrame;

import paint.mvc.Controller;
import paint.mvc.Model;
import paint.mvc.PaintFrame;
import paint.mvc.View;

public class Paint {
	
	@SuppressWarnings("unused")
	public static void main(String [] args)
	{
		Model model = new Model();
		View view = new View(model);
		PaintFrame frame = new PaintFrame(view);
		
		Controller controller = new Controller(model, frame);
		frame.setController(controller);
		frame.setVisible(true);
		
		
	}

}
