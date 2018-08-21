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

public class DijalogKrugCrtanje extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPoluprecnik;
	private int poluprecnik;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DijalogKrugCrtanje dialog = new DijalogKrugCrtanje();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DijalogKrugCrtanje() {
		setModal(true); 
		setBounds(100, 100, 403, 165);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[92.00][58px][86px]", "[][20px]"));
		{
			JLabel lblPoluprecnik = new JLabel("Poluprecnik:");
			contentPanel.add(lblPoluprecnik, "cell 1 1,alignx center,aligny center");
		}
		{
			txtPoluprecnik = new JTextField();
			contentPanel.add(txtPoluprecnik, "cell 2 1,alignx left,aligny center");
			txtPoluprecnik.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Potvrdi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try{
							
							poluprecnik=Integer.parseInt(txtPoluprecnik.getText());
							System.out.println(poluprecnik);
							if (poluprecnik <= 0)
							{
								System.out.println("Poluprecnik je negativan, greska");
								JOptionPane.showMessageDialog(null, "Poluprecnik ne moze da bude negativan!");
							}
							else
							{
								
								setVisible(false);
							}
							
						} catch (Exception e1) {
							
							System.out.println("nije unet broj!!!");
							JOptionPane.showMessageDialog(null, "Greska u unosu, nije unet broj!");
						
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public int getPoluprecnik()
	{
		System.out.println("vracam " + poluprecnik);
		return poluprecnik;
	}

}
