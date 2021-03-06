package paint.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import net.miginfocom.swing.MigLayout;
import paint.geometry.Circle;
import paint.geometry.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import paint.mvc.PaintFrame;
public class DialogCircleModification extends JDialog {

	
	private JPanel pnlCommands = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private int x;
	private int y;
	private int radius;
	private JButton btnOutsideColor;
	private JButton btnInsideColor;
	private Circle newCircle;
	
	

	public DialogCircleModification(Circle circle) {
		
		setTitle("Circle modification");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCommands.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCommands, BorderLayout.CENTER);
		pnlCommands.setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][]"));
		{
			JLabel lblX = new JLabel("X coordinate of center:");
			pnlCommands.add(lblX, "cell 3 2,alignx left");
		}
		{
			txtX = new JTextField();
			pnlCommands.add(txtX, "cell 4 2,alignx center");
			txtX.setColumns(10);
			txtX.setText(""+ circle.getCentar().getX());
		}
		{
			JLabel lblY = new JLabel("Y coordinate of center:");
			pnlCommands.add(lblY, "cell 3 3,alignx left");
		}
		{
			txtY = new JTextField();
			pnlCommands.add(txtY, "cell 4 3,alignx center");
			txtY.setColumns(10);
			txtY.setText("" + circle.getCentar().getY());
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			pnlCommands.add(lblRadius, "cell 3 4,alignx left");
		}
		{
			txtRadius = new JTextField();
			pnlCommands.add(txtRadius, "cell 4 4,alignx center");
			txtRadius.setColumns(10);
			txtRadius.setText("" + circle.getR());
		}
		{
			JLabel lblOutsideColor = new JLabel("Outside color:");
			pnlCommands.add(lblOutsideColor, "cell 3 5");
		}
		{
			 btnOutsideColor = new JButton("");
			 btnOutsideColor.setBackground(circle.getColor());
			 btnOutsideColor.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
			 		JColorChooser jcc = new JColorChooser();
					Color chooseColor =jcc.showDialog(null, "Choose outside color", Color.BLACK);
					btnOutsideColor.setBackground(chooseColor);
			 	}
			 });
			//btnBojaKonture.setBackground(Color.BLACK);
			pnlCommands.add(btnOutsideColor, "cell 4 5,alignx center");
		}
		{
			JLabel lblInsideColor = new JLabel("Inside color:");
			pnlCommands.add(lblInsideColor, "cell 3 6,alignx left");
		}
		{
			 btnInsideColor = new JButton("");
			 btnInsideColor.setBackground(circle.getInsideColor());
			 btnInsideColor.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		JColorChooser jcc = new JColorChooser();
					Color chooseColor =jcc.showDialog(null, "Choose inside color", Color.BLACK);
					btnInsideColor.setBackground(chooseColor);
			 	}
			 });
			pnlCommands.add(btnInsideColor, "cell 4 6,alignx center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirm");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try{
							x=Integer.parseInt(txtX.getText());
							y=Integer.parseInt(txtY.getText());
							radius=Integer.parseInt(txtRadius.getText());
							
							if (x <=0 || y<=0 || radius<=0)
							{
								
								JOptionPane.showMessageDialog(null, "Wrong entry, numbers must be positive");
							}
							
							else{
								setVisible(false);
								newCircle= new Circle(new Point (x,y), radius, btnOutsideColor.getBackground(), btnInsideColor.getBackground());
							}
							
						} catch (Exception e) {
							
							
							JOptionPane.showMessageDialog(null, "Wrong entry, must be a number");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public Circle getData()
	{
		return newCircle;
	}

	
}
