package paint.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class DialogCircleDrawing extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private int radius;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogCircleDrawing dialog = new DialogCircleDrawing();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogCircleDrawing() {
		setModal(true); 
		setBounds(100, 100, 403, 165);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[92.00][58px][86px]", "[][20px]"));
		{
			JLabel lblRadius = new JLabel("Radius:");
			contentPanel.add(lblRadius, "cell 1 1,alignx center,aligny center");
		}
		{
			txtRadius = new JTextField();
			contentPanel.add(txtRadius, "cell 2 1,alignx left,aligny center");
			txtRadius.setColumns(10);
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
							
							radius=Integer.parseInt(txtRadius.getText());
							System.out.println(radius);
							if (radius <= 0)
							{
								//System.out.println("Poluprecnik je negativan, greska");
								JOptionPane.showMessageDialog(null, "Radius must be positive!");
							}
							else
							{
								
								setVisible(false);
							}
							
						} catch (Exception e1) {
							
							//System.out.println("nije unet broj!!!");
							JOptionPane.showMessageDialog(null, "Wrong entry, must be a number!");
						
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public int getRadius()
	{
		
		return radius;
	}

}
