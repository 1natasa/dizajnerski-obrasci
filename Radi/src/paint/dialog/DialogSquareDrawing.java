package paint.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class DialogSquareDrawing extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSideLength;
	private int sideLength;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogSquareDrawing dialog = new DialogSquareDrawing();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setTitle("Drawing square");
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogSquareDrawing() {
		
		setTitle("Drawing square");
		setModal(true);
		setBounds(100, 100, 410, 126);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[120.00][73px][86px]", "[20px]"));
		{
			JLabel lblSideLength = new JLabel("Side length:");
			contentPanel.add(lblSideLength, "cell 1 0,alignx right,aligny bottom");
		}
		{
			txtSideLength = new JTextField();
			contentPanel.add(txtSideLength, "cell 2 0,alignx left,growy");
			txtSideLength.setColumns(10);
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
							sideLength=Integer.parseInt(txtSideLength.getText());
							if (sideLength <= 0)
							{
								System.out.println("Side length must be positive");
								JOptionPane.showMessageDialog(null, "Side length must be positive");
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
	public int getSideLength()
	{

		return sideLength;
	}

}


