package paint.serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import paint.mvc.Controller;
import paint.mvc.Model;

public class PaintStrategy implements Strategy{
	
	private Model model;
	private Controller controller;
	public  PaintStrategy(Model model, Controller controller) {
	
		this.model=model;
		this.controller=controller;
	}
	

	@Override
	public void save(String path) {
		
		 ObjectOutputStream out= null;
		
		try {
			 out =  new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
			 out.writeObject(model);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(out!=null)
			{
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		
	}

	@Override
	public void load(String path) {
		
		ObjectInputStream input =null;
		
		
		try {
			 input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
			Model loadModel= (Model) input.readObject();
			controller.setModel(loadModel);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(input!=null)
			{
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
