package paint.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paint.geometry.Point;
import paint.geometry.Rectangle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogRectangleDrawing extends JDialog {

	private final JPanel pnlDialog = new JPanel();
	private JTextField txtHeight;
	private JTextField txtWidth;
	private int x;
	private int y;
	protected int width;
	protected int height;
	 Rectangle rectangle=null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogRectangleDrawing dialog = new DialogRectangleDrawing();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogRectangleDrawing() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlDialog.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlDialog, BorderLayout.CENTER);
		pnlDialog.setLayout(null);
		{
			JLabel lblHeight = new JLabel("Height:");
			lblHeight.setBounds(97, 68, 69, 14);
			pnlDialog.add(lblHeight);
		}
		{
			txtHeight = new JTextField();
			txtHeight.setBounds(229, 65, 86, 20);
			pnlDialog.add(txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			lblWidth.setBounds(97, 93, 69, 14);
			pnlDialog.add(lblWidth);
		}
		{
			txtWidth = new JTextField();
			txtWidth.setBounds(229, 96, 86, 20);
			pnlDialog.add(txtWidth);
			txtWidth.setColumns(10);
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
							
							
							
							height=Integer.parseInt(txtHeight.getText());
							width=Integer.parseInt(txtWidth.getText());
					

						if ( height <= 0 || width <=0 )
								
						{
							//System.out.println("greska br mora biti pozitivan!!");
							JOptionPane.showMessageDialog(null, "Wrong entry, number must be positive");
						}
						else
						{
							
							
							setVisible(false);
						}
						
							
						} catch (Exception e1) {
							
							//System.out.println("greska pri unosu nije unet broj");
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
	public int  getSirina ()
	{
		return width;
	}
	public int getDuzina()
	{
		return height;
	}

}
