package aplikacija.wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometrija.Kvadrat;
import geometrija.Tacka;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DijalogKvadratStek extends JDialog {
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtDuzinaIviceKvadrata;
	public int x;
	public int y;
	public int duzinaIviceKvadrata;
	public String bojaIvice;
	public String bojaUnutrasnjosti;
	
	
	boolean dugmeOK=false;
	Kvadrat k = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DijalogKvadratStek dialog = new DijalogKvadratStek();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DijalogKvadratStek() {

		setModal(true);

		JLabel lblX = new JLabel("X koordinata tacke gore levo:");
		lblX.setBounds(30, 44, 162, 14);
		getContentPane().add(lblX);

		txtX = new JTextField();
		txtX.setBounds(208, 41, 86, 20);
		getContentPane().add(txtX);
		txtX.setColumns(10);

	
		JLabel lblY = new JLabel("Y koordinata tacke gore levo:");
		lblY.setBounds(30, 69, 162, 14);
		getContentPane().add(lblY);

		txtY = new JTextField();
		txtY.setBounds(208, 72, 86, 20);
		getContentPane().add(txtY);
		txtY.setColumns(10);

		JLabel lblBojaIvice = new JLabel("Boja ivice:");
		lblBojaIvice.setBounds(30, 148, 139, 14);
		getContentPane().add(lblBojaIvice);

		JLabel lblDuzinaIviceKvadrata = new JLabel("Duzina ivice kvadrata:");
		lblDuzinaIviceKvadrata.setBounds(33, 112, 139, 14);
		getContentPane().add(lblDuzinaIviceKvadrata);

		txtDuzinaIviceKvadrata = new JTextField();
		txtDuzinaIviceKvadrata.setBounds(208, 109, 86, 20);
		getContentPane().add(txtDuzinaIviceKvadrata);
		txtDuzinaIviceKvadrata.setColumns(10);
		
		JComboBox cbxBojaIvice = new JComboBox();
		cbxBojaIvice.setModel(new DefaultComboBoxModel(new String[] {"Crna", "Plava", "Zuta", "Ljubicasta", "Crvena", "Zelena", "Roza"}));
		cbxBojaIvice.setBounds(208, 145, 86, 20);
		getContentPane().add(cbxBojaIvice);
		
		JComboBox cbxBojaUnutrasnjosti = new JComboBox();
		cbxBojaUnutrasnjosti.setModel(new DefaultComboBoxModel(new String[] {"Bela", "Crna", "Plava", "Roza", "Ljubicasta", "Zuta", "Zelena"}));
		cbxBojaUnutrasnjosti.setBounds(208, 182, 86, 20);
		getContentPane().add(cbxBojaUnutrasnjosti);

		JLabel lblBojaUnutrasnjosti = new JLabel("Boja unutrasnjosti:");
		lblBojaUnutrasnjosti.setBounds(30, 185, 128, 14);
		getContentPane().add(lblBojaUnutrasnjosti);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Potvrdi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {


						try{
							
							x=Integer.parseInt(txtX.getText());
							y=Integer.parseInt(txtY.getText());
							duzinaIviceKvadrata=Integer.parseInt(txtDuzinaIviceKvadrata.getText());
							bojaIvice=(String)cbxBojaIvice.getSelectedItem();
							bojaUnutrasnjosti=(String)cbxBojaUnutrasnjosti.getSelectedItem();

							if(x <= 0 || y <= 0 || duzinaIviceKvadrata<= 0){
								System.out.println("Greska! broj mora biti pozitivan");
								JOptionPane.showMessageDialog(null, "Greska! broj mora biti pozitivan");
							}else{
								k = new Kvadrat(new Tacka(Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText())), Integer.parseInt(txtDuzinaIviceKvadrata.getText()));
								setVisible(false);
							}

							
							
						}catch(Exception e){ // u slucaju da ne prodje!
							System.out.println("Greska pri unosu! nije unet broj");
							JOptionPane.showMessageDialog(null, "Greska pri unosu! nije unet broj");
						}



					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);


			}
		}
		
		
		
		




	}
	
	public Kvadrat getPodaci(){

		System.out.println("Podaci");
		//Vraca prazan kvadrat ili pun u zavisnist kako je prosao uslove
		
		return k;
	}
}