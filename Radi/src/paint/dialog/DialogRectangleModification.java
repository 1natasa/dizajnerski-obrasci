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
import paint.geometry.Rectangle;
import paint.mvc.PaintFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogRectangleModification extends JDialog {

	private final JPanel pnlCommands = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private JButton btnOutsideColor;
	private JButton btnInsideColor;
	private int x;
	private int y;
	private int height;
	private int width;
	private Rectangle newRectangle;
	

	public DialogRectangleModification(Rectangle rectangle) {
		setModal(true);
		setBounds(100, 100, 358, 264);
		getContentPane().setLayout(new BorderLayout());
		pnlCommands.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCommands, BorderLayout.CENTER);
		pnlCommands.setLayout(new MigLayout("", "[170.00][166.00,grow]", "[][][][][12.00][17.00]"));
		{
			JLabel lblX = new JLabel("X coordinate point up to the left:");
			pnlCommands.add(lblX, "cell 0 0,alignx trailing");
		}
		{
			txtX = new JTextField();
			pnlCommands.add(txtX, "cell 1 0,alignx center");
			txtX.setColumns(10);
			txtX.setText("" + rectangle.getTopLeftPoint().getX());
		}
		{
			JLabel lblY = new JLabel("Y coordinate point up to the left:");
			pnlCommands.add(lblY, "cell 0 1,alignx trailing");
		}
		{
			txtY = new JTextField();
			pnlCommands.add(txtY, "cell 1 1,alignx center");
			txtY.setColumns(10);
			txtY.setText(""+ rectangle.getTopLeftPoint().getY());
		}
		{
			JLabel lblVisina = new JLabel("Height:");
			pnlCommands.add(lblVisina, "cell 0 2,alignx right");
		}
		{
			txtHeight = new JTextField();
			pnlCommands.add(txtHeight, "cell 1 2,alignx center");
			txtHeight.setColumns(10);
			txtHeight.setText("" + rectangle.getHeight());
		}
		{
			JLabel lblSirina = new JLabel("Width:");
			pnlCommands.add(lblSirina, "cell 0 3,alignx right");
		}
		{
			txtWidth = new JTextField();
			pnlCommands.add(txtWidth, "cell 1 3,alignx center");
			txtWidth.setColumns(10);
			txtWidth.setText("" + rectangle.getWidth());
		}
		{
			JLabel lblBojaKonture = new JLabel("Outside color:");
			pnlCommands.add(lblBojaKonture, "cell 0 4,alignx right");
		}
		{
			 btnOutsideColor = new JButton("");
			 btnOutsideColor.setBackground(rectangle.getColor());
			btnOutsideColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Choose outside color", Color.BLACK);
					btnOutsideColor.setBackground(izborBoje);
				}
			});
			//btnBojaKonture.setBackground(Color.BLACK);
			pnlCommands.add(btnOutsideColor, "cell 1 4,alignx center,growy");
		}
		{
			JLabel lblBojaUnutrasnjosti = new JLabel("Inside color:");
			pnlCommands.add(lblBojaUnutrasnjosti, "cell 0 5,alignx right");
		}
		{
			 btnInsideColor = new JButton("");
			 btnInsideColor.setBackground(rectangle.getInsideColor());
			btnInsideColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Choose inside color", Color.BLACK);
					btnInsideColor.setBackground(izborBoje);
				}
			});
			//btnBojaUnutrasnjosti.setBackground(Color.WHITE);
			pnlCommands.add(btnInsideColor, "cell 1 5,alignx center,growy");
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
							height=Integer.parseInt(txtHeight.getText());
							width=Integer.parseInt(txtWidth.getText());
							
							if (x<=0 || y<=0 || height<=0 || width<=0)
							{
								//System.out.println("Greska, broj mora biti pozitivan");
								JOptionPane.showMessageDialog(null, "Wrong entry, number must be positive");
							}
							
							else {
								setVisible(false);
								newRectangle= new Rectangle(new Point(x,y), width, height, btnOutsideColor.getBackground(), btnInsideColor.getBackground());
							}
							
						} catch (Exception e1) {
							
							//System.out.println("greska pri unosu, nije unet broj");
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
	
	public Rectangle getData()
	{
		
		return newRectangle;
	}

}
