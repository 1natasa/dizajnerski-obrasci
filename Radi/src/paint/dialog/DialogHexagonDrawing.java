package paint.dialog;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DialogHexagonDrawing extends JDialog {
	
	

	private final JPanel commandsPanel = new JPanel();
	private JTextField txtRadius;
	private int radius=-1;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogHexagonDrawing dialog = new DialogHexagonDrawing();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogHexagonDrawing() {
		
		setTitle("Drawing hexagon");
		setModal(true);
		setBounds(100, 100, 410, 126);
		getContentPane().setLayout(new BorderLayout());
		commandsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(commandsPanel, BorderLayout.CENTER);
		commandsPanel.setLayout(new MigLayout("", "[120.00][73px][86px]", "[20px]"));
		
		{
			JLabel lblRadius = new JLabel("Radius:");
			commandsPanel.add(lblRadius, "cell 1 0,alignx right,aligny bottom");
		}
		{
			txtRadius = new JTextField();
			commandsPanel.add(txtRadius, "cell 2 0,alignx left,growy");
			txtRadius.setColumns(10);
		}
		
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirm");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							radius=Integer.parseInt(txtRadius.getText());
							if (radius <= 0)
							{
								System.out.println("Radius must be positive");
								JOptionPane.showMessageDialog(null, "Radius must be positive");
							}
							else
							{
								setVisible(false);
							}
							
						} catch (Exception e1) {
							
							//System.out.println("Nije unet broj");
							JOptionPane.showMessageDialog(null, "Wrong entry, must be a number");
							
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPanel.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	

}
