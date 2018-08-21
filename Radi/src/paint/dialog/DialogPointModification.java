package paint.dialog;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import paint.geometry.Point;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import paint.mvc.PaintFrame;


public class DialogPointModification extends JDialog {

	private final JPanel pnlButtons = new JPanel();
	private JTextField txtX;
	private JTextField txtY;


	private int x;
	private int y;
	private Color outsideColor;
	private Point newPoint;
	private JButton btnOutsideColor;
	
	public DialogPointModification(Point point) {
		
		setModal(true);
		
	
		
		setBounds(100, 100, 379, 226);
		getContentPane().setLayout(new BorderLayout());
		pnlButtons.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlButtons, BorderLayout.CENTER);
		pnlButtons.setLayout(new MigLayout("", "[][][][103px][86px]", "[][][20px][20px][]"));
		{
			JLabel lblX = new JLabel("Enter x:");
			pnlButtons.add(lblX, "cell 3 2,alignx right,growy");
		}
		{
			txtX = new JTextField();
			pnlButtons.add(txtX, "cell 4 2,alignx left,aligny center");
			txtX.setColumns(10);
			
			txtX.setText("" + point.getX());
		
			
		
		}
		{
			JLabel lblY = new JLabel("Enter y:");
			pnlButtons.add(lblY, "cell 3 3,alignx right,aligny center");
		}
		{
			txtY = new JTextField();
			pnlButtons.add(txtY, "cell 4 3,alignx left,aligny center");
			txtY.setColumns(10);
			txtY.setText("" + point.getY());
		}
		{
			JLabel lblOutsideColor = new JLabel("Outside color:");
			pnlButtons.add(lblOutsideColor, "cell 3 4");
		}
		{
			btnOutsideColor = new JButton("");
			btnOutsideColor.setBackground(point.getColor());
			btnOutsideColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JColorChooser jcc = new JColorChooser();
					Color chooseColor =jcc.showDialog(null, "Outside color", Color.BLACK);
					btnOutsideColor.setBackground(chooseColor);
				}
			});
			//btnBojaKonture.setBackground(Color.BLACK);
			pnlButtons.add(btnOutsideColor, "cell 4 4,alignx center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirm");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						try {
							x=Integer.parseInt(txtX.getText());
							y=Integer.parseInt(txtY.getText());
							
							
							
							if (x <= 0 || y<=0 )
							{
								JOptionPane.showMessageDialog(null, "Coordinates must be positive");
								//System.out.println("Wrong entery, the coordinates are negative");
							}
							
							else {
								newPoint= new Point (x,y,btnOutsideColor.getBackground());
								setVisible(false);
							}
						} catch (Exception e1) {
							
							
							//System.out.println("W");
							JOptionPane.showMessageDialog(null, "Wrong enetry, must be a number");
							
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public Point getData()
	{
		return newPoint;
	}

	


}
