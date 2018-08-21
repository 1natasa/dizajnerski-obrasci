package paint.dialog;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import paint.geometry.Point;
import paint.geometry.Square;
import paint.mvc.PaintFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogSquareModification extends JDialog {

	private final JPanel pnlCommands = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtSideLength;
	private int x;
	private int y;
	private int sideLength;
	JButton btnOutsideColor;
	JButton btnInsideColor;
	private Square newSqaure;
	
	public DialogSquareModification(Square square) {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCommands.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCommands, BorderLayout.CENTER);
		pnlCommands.setLayout(new MigLayout("", "[][][grow]", "[][][][][]"));
		{
			JLabel lblX = new JLabel("X coordinate point up to the left::");
			pnlCommands.add(lblX, "cell 1 0,alignx left");
		}
		{
			txtX = new JTextField();
			pnlCommands.add(txtX, "cell 2 0,alignx center");
			txtX.setColumns(10);
			txtX.setText("" + square.getTopLeftPoint().getX());
		}
		{
			JLabel lblY = new JLabel("Y coordinate point up to the left:");
			pnlCommands.add(lblY, "cell 1 1,alignx left");
		}
		{
			txtY = new JTextField();
			pnlCommands.add(txtY, "cell 2 1,alignx center");
			txtY.setColumns(10);
			txtY.setText("" + square.getTopLeftPoint().getY());
		}
		{
			JLabel lblSideLength = new JLabel("Side length:");
			pnlCommands.add(lblSideLength, "cell 1 2,alignx left");
		}
		{
			txtSideLength = new JTextField();
			pnlCommands.add(txtSideLength, "cell 2 2,alignx center");
			txtSideLength.setColumns(10);
			txtSideLength.setText(""+square.getSideLength());
		}
		{
			JLabel lblOutsideColor = new JLabel("Outside color:");
			pnlCommands.add(lblOutsideColor, "cell 1 3,alignx left");
		}
		{
			 btnOutsideColor = new JButton("");
			 btnOutsideColor.setBackground(square.getColor());
			 btnOutsideColor.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Choose outside color", Color.BLACK);
					btnOutsideColor.setBackground(izborBoje);
			 	}
			 });
			 
			 
			//btnBojaKonture.setBackground(Color.BLACK);
			pnlCommands.add(btnOutsideColor, "cell 2 3,alignx center");
		}
		{
			JLabel lblInsideColor = new JLabel("Inside color:");
			pnlCommands.add(lblInsideColor, "cell 1 4,alignx left");
		}
		{
			 btnInsideColor = new JButton("");
			 btnInsideColor.setBackground(square.getInsideColor());
			 btnInsideColor.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Choose inside color", Color.WHITE);
					btnInsideColor.setBackground(izborBoje);
			 	}
			 });
			//btnBojaUnutrasnjosti.setBackground(Color.WHITE);
		//	btnBojaUnutrasnjosti.setForeground(Color.BLACK);
			pnlCommands.add(btnInsideColor, "cell 2 4,alignx center");
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
							sideLength=Integer.parseInt(txtSideLength.getText());
							
							if (x<=0 || y<=0 || sideLength<=0 )
							{
								//System.out.println("greska pri unosu, negativni brojevi!!");
								JOptionPane.showMessageDialog(null, "Wrong entery, numbers must be positive");
							}
							else
							{
								setVisible(false);
								newSqaure= new Square(new Point(x,y), sideLength, btnOutsideColor.getBackground(), btnInsideColor.getBackground());
							}
							
						} catch (Exception e) {
							
							//System.out.println("greska pri unosu, nije unet broj");
							JOptionPane.showMessageDialog(null, "Wrong entery, must be a number");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public Square getData()
	{
		return newSqaure;
		
	}

}
