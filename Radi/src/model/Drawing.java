package model;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class Drawing extends JPanel{
	//public Stack<Oblik> stek = new Stack<Oblik>();
	int x;
	int y;
	int i;
	public Drawing() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});
	}
	
	public static void main(String[] args){
		JFrame window = new JFrame("Crtanje");
		window.setSize(800, 600);
		Drawing c = new Drawing();
		window.getContentPane().add(c);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g){
		/*Tacka t1 = new Tacka(x,y);
		t1.crtajSe(g);
		repaint();*/
		/*super.paint(g);
		for (Oblik o : stek)
		{
			o.crtajSe(g);
		}*/
	
	}
}
