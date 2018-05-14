package aplikacija.wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Square;
import model.Point;

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
import net.miginfocom.swing.MigLayout;

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
	Square k = null;
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
		getContentPane().setLayout(new MigLayout("", "[192px][16px][226px]", "[20px][23px][20px][20px][20px][33px][][][]"));

		JLabel lblX = new JLabel("X koordinata tacke gore levo:");
		getContentPane().add(lblX, "cell 0 0,alignx right,aligny center");

		txtX = new JTextField();
		getContentPane().add(txtX, "cell 2 0,alignx center,aligny top");
		txtX.setColumns(10);

	
		JLabel lblY = new JLabel("Y koordinata tacke gore levo:");
		getContentPane().add(lblY, "cell 0 1,alignx right,aligny top");

		txtY = new JTextField();
		getContentPane().add(txtY, "cell 2 1,alignx center,aligny bottom");
		txtY.setColumns(10);

		JLabel lblBojaIvice = new JLabel("Boja ivice:");
		getContentPane().add(lblBojaIvice, "cell 0 3,alignx right,aligny center");

		JLabel lblDuzinaIviceKvadrata = new JLabel("Duzina ivice kvadrata:");
		getContentPane().add(lblDuzinaIviceKvadrata, "cell 0 2,alignx right,aligny center");

		txtDuzinaIviceKvadrata = new JTextField();
		getContentPane().add(txtDuzinaIviceKvadrata, "cell 2 2,alignx center,aligny top");
		txtDuzinaIviceKvadrata.setColumns(10);
		
		JComboBox cbxBojaIvice = new JComboBox();
		cbxBojaIvice.setModel(new DefaultComboBoxModel(new String[] {"Crna", "Plava", "Zuta", "Ljubicasta", "Crvena", "Zelena", "Roza"}));
		getContentPane().add(cbxBojaIvice, "cell 2 3,alignx center,aligny top");
		
		JComboBox cbxBojaUnutrasnjosti = new JComboBox();
		cbxBojaUnutrasnjosti.setModel(new DefaultComboBoxModel(new String[] {"Bela", "Crna", "Plava", "Roza", "Ljubicasta", "Zuta", "Zelena"}));
		getContentPane().add(cbxBojaUnutrasnjosti, "cell 2 4,alignx center,aligny top");

		JLabel lblBojaUnutrasnjosti = new JLabel("Boja unutrasnjosti:");
		getContentPane().add(lblBojaUnutrasnjosti, "cell 0 4,alignx right,aligny center");
		setBounds(100, 100, 450, 300);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, "cell 0 5 3 1,growx,aligny top");
		}
		{
			JButton okButton = new JButton("Potvrdi");
			getContentPane().add(okButton, "cell 2 7,alignx right");
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
							System.out.println(bojaIvice);
							k = new Square(new Point(Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText())), Integer.parseInt(txtDuzinaIviceKvadrata.getText()),bojaIvice,bojaUnutrasnjosti);
							setVisible(false);
						}

						
						
					}catch(Exception e){ 
						System.out.println("Greska pri unosu! nije unet broj");
						JOptionPane.showMessageDialog(null, "Greska pri unosu! nije unet broj");
					}



				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);


		}
		
		
		
		




	}
	
	public Square getPodaci(){

		System.out.println("Podaci");
	
		
		return k;
	}
}
