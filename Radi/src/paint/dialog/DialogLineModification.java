package paint.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import paint.geometry.Line;
import paint.geometry.Point;
import paint.mvc.PaintFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogLineModification extends JDialog {

	private final JPanel pnlCommands = new JPanel();
	private JTextField txtX1;
	private JTextField txtY1;
	private JTextField txtX2;
	private JTextField txtY2;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Line newLine;
	private JButton btnOutsideColor;
	

	
	
	public DialogLineModification(Line line) {
		setTitle("Line modification");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCommands.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCommands, BorderLayout.CENTER);
		pnlCommands.setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][]"));
		{
			JLabel lblX1 = new JLabel("X coordinate of first point:");
			pnlCommands.add(lblX1, "cell 3 2,alignx left");
		}
		{
			txtX1 = new JTextField();
			pnlCommands.add(txtX1, "cell 4 2,alignx center");
			txtX1.setColumns(10);
			txtX1.setText("" + line.getStart().getX());
		}
		{
			JLabel lblY1 = new JLabel("Y coordinate of first point:");
			pnlCommands.add(lblY1, "cell 3 3,alignx left");
		}
		{
			txtY1 = new JTextField();
			pnlCommands.add(txtY1, "cell 4 3,alignx center");
			txtY1.setColumns(10);
			txtY1.setText(""  + line.getStart().getY());
		}
		{
			JLabel lblX2 = new JLabel("X coordinate of second point:");
			pnlCommands.add(lblX2, "cell 3 4,alignx left");
		}
		{
			txtX2 = new JTextField();
			pnlCommands.add(txtX2, "cell 4 4,alignx center");
			txtX2.setColumns(10);
			txtX2.setText("" + line.getEnd().getX());
		}
		{
			JLabel lblY2 = new JLabel("Y coordinate of second point:");
			pnlCommands.add(lblY2, "cell 3 5,alignx left");
		}
		{
			txtY2 = new JTextField();
			pnlCommands.add(txtY2, "cell 4 5,alignx center");
			txtY2.setColumns(10);
			txtY2.setText("" +line.getEnd().getY());
		}
		{
			JLabel lblColor = new JLabel("Color:");
			pnlCommands.add(lblColor, "cell 3 6,alignx left");
		}
		{
			btnOutsideColor = new JButton("");
			btnOutsideColor.setBackground(Color.BLACK);
		
			btnOutsideColor.setBackground(line.getColor());

			btnOutsideColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Choose outside color", Color.BLACK);
					btnOutsideColor.setBackground(izborBoje);
				}
			});
			pnlCommands.add(btnOutsideColor, "cell 4 6,alignx center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirm");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try{
							x1=Integer.parseInt(txtX1.getText());
							y1=Integer.parseInt(txtY1.getText());
							x2=Integer.parseInt(txtX2.getText());
							y2=Integer.parseInt(txtY2.getText());
							
							if (x1<=0 || y1<=0 || x2<=0 || y2<=0)
							{
								//System.out.println("Greska pri unosu, negativan broj");
								JOptionPane.showMessageDialog(null, "Wrong entry, number must be positive");
							}
							else
							{
								newLine=new Line (new Point (x1,y1), new Point (x2,y2), btnOutsideColor.getBackground());
								setVisible(false);
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

	public Line getData()
	{
		return newLine;
	}
}
