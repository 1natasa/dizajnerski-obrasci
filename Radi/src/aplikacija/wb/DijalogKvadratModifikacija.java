package aplikacija.wb;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometrija.Kvadrat;
import geometrija.Tacka;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DijalogKvadratModifikacija extends JDialog {

	private final JPanel pnlKomande = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtDuzinaStranice;
	private int x;
	private int y;
	private int duzinaStranice;
	JButton btnBojaKonture;
	JButton btnBojaUnutrasnjosti;
	Kvadrat k1=null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DijalogKvadratModifikacija dialog = new DijalogKvadratModifikacija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DijalogKvadratModifikacija() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlKomande.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlKomande, BorderLayout.CENTER);
		pnlKomande.setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][]"));
		{
			JLabel lblX = new JLabel("X koordinata tacke gore levo:");
			pnlKomande.add(lblX, "cell 3 2,alignx trailing");
		}
		{
			txtX = new JTextField();
			pnlKomande.add(txtX, "cell 4 2,alignx center");
			txtX.setColumns(10);
			txtX.setText("" + ((Kvadrat)GuiCrtanje.getSelektovan()).getGoreLevo().getX());
		}
		{
			JLabel lblY = new JLabel("Y koordinata tacke gore levo:");
			pnlKomande.add(lblY, "cell 3 3,alignx trailing");
		}
		{
			txtY = new JTextField();
			pnlKomande.add(txtY, "cell 4 3,alignx center");
			txtY.setColumns(10);
			txtY.setText("" + ((Kvadrat)GuiCrtanje.getSelektovan()).getGoreLevo().getY());
		}
		{
			JLabel lblDuzinaStranice = new JLabel("Duzina stranice:");
			pnlKomande.add(lblDuzinaStranice, "cell 3 4,alignx left");
		}
		{
			txtDuzinaStranice = new JTextField();
			pnlKomande.add(txtDuzinaStranice, "cell 4 4,alignx center");
			txtDuzinaStranice.setColumns(10);
			txtDuzinaStranice.setText(""+((Kvadrat)GuiCrtanje.getSelektovan()).getDuzinaStranica());
		}
		{
			JLabel lblBojaKonture = new JLabel("Boja konture:");
			pnlKomande.add(lblBojaKonture, "cell 3 5");
		}
		{
			 btnBojaKonture = new JButton("...");
			 btnBojaKonture.setBackground(((Kvadrat)GuiCrtanje.getSelektovan()).getBoja());
			 btnBojaKonture.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Izaberite boju za konturu", Color.BLACK);
					btnBojaKonture.setBackground(izborBoje);
			 	}
			 });
			 
			 
			//btnBojaKonture.setBackground(Color.BLACK);
			pnlKomande.add(btnBojaKonture, "cell 4 5,alignx center");
		}
		{
			JLabel lblBojaUnutrasnjosti = new JLabel("Boja unutrasnjosti:");
			pnlKomande.add(lblBojaUnutrasnjosti, "cell 3 6");
		}
		{
			 btnBojaUnutrasnjosti = new JButton("...");
			 btnBojaUnutrasnjosti.setBackground(((Kvadrat)GuiCrtanje.getSelektovan()).getBojaUnutrasnjosti());
			 btnBojaUnutrasnjosti.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Izaberite boju za konturu", Color.WHITE);
					btnBojaUnutrasnjosti.setBackground(izborBoje);
			 	}
			 });
			//btnBojaUnutrasnjosti.setBackground(Color.WHITE);
		//	btnBojaUnutrasnjosti.setForeground(Color.BLACK);
			pnlKomande.add(btnBojaUnutrasnjosti, "cell 4 6,alignx center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Potvrdi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try{
							
							x=Integer.parseInt(txtX.getText());
							y=Integer.parseInt(txtY.getText());
							duzinaStranice=Integer.parseInt(txtDuzinaStranice.getText());
							
							if (x<=0 || y<=0 || duzinaStranice<=0 )
							{
								System.out.println("greska pri unosu, negativni brojevi!!");
								JOptionPane.showMessageDialog(null, "Greska, brojevi moraju biti pozitivni!!");
							}
							else
							{
								setVisible(false);
								k1= new Kvadrat(new Tacka(x,y), duzinaStranice, btnBojaKonture.getBackground(), btnBojaUnutrasnjosti.getBackground());
							}
							
						} catch (Exception e) {
							
							System.out.println("greska pri unosu, nije unet broj");
							JOptionPane.showMessageDialog(null, "Greska, nije unet broj");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public Kvadrat getPodaci()
	{
		return k1;
		
	}

}
