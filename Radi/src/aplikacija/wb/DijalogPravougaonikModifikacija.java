package aplikacija.wb;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometrija.Pravougaonik;
import geometrija.Tacka;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DijalogPravougaonikModifikacija extends JDialog {

	private final JPanel pnlKomande = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtVisina;
	private JTextField txtSirina;
	JButton btnBojaKonture;
	JButton btnBojaUnutrasnjosti;
	private int x;
	private int y;
	private int visina;
	private int sirina;
	Pravougaonik pr1=null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DijalogPravougaonikModifikacija dialog = new DijalogPravougaonikModifikacija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DijalogPravougaonikModifikacija() {
		setModal(true);
		setBounds(100, 100, 304, 257);
		getContentPane().setLayout(new BorderLayout());
		pnlKomande.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlKomande, BorderLayout.CENTER);
		pnlKomande.setLayout(new MigLayout("", "[170.00][154.00,grow]", "[][][][][30.00][30.00]"));
		{
			JLabel lblX = new JLabel("X koordinata tacke gore levo:");
			pnlKomande.add(lblX, "cell 0 0,alignx trailing");
		}
		{
			txtX = new JTextField();
			pnlKomande.add(txtX, "cell 1 0,growx");
			txtX.setColumns(10);
			txtX.setText("" + ((Pravougaonik)GuiCrtanje.getSelektovan()).getGoreLevo().getX());
		}
		{
			JLabel lblY = new JLabel("Y koordinata tacke gore levo:");
			pnlKomande.add(lblY, "cell 0 1,alignx trailing");
		}
		{
			txtY = new JTextField();
			pnlKomande.add(txtY, "cell 1 1,growx");
			txtY.setColumns(10);
			txtY.setText(""+ ((Pravougaonik)GuiCrtanje.getSelektovan()).getGoreLevo().getY());
		}
		{
			JLabel lblVisina = new JLabel("Visina:");
			pnlKomande.add(lblVisina, "cell 0 2,alignx right");
		}
		{
			txtVisina = new JTextField();
			pnlKomande.add(txtVisina, "cell 1 2,growx");
			txtVisina.setColumns(10);
			txtVisina.setText("" + ((Pravougaonik)GuiCrtanje.getSelektovan()).getVisina());
		}
		{
			JLabel lblSirina = new JLabel("Sirina:");
			pnlKomande.add(lblSirina, "cell 0 3,alignx right");
		}
		{
			txtSirina = new JTextField();
			pnlKomande.add(txtSirina, "cell 1 3,growx");
			txtSirina.setColumns(10);
			txtSirina.setText("" + ((Pravougaonik)GuiCrtanje.getSelektovan()).getSirina());
		}
		{
			JLabel lblBojaKonture = new JLabel("Boja konture:");
			pnlKomande.add(lblBojaKonture, "cell 0 4,alignx right");
		}
		{
			 btnBojaKonture = new JButton("");
			 btnBojaKonture.setBackground(((Pravougaonik)GuiCrtanje.getSelektovan()).getBoja());
			btnBojaKonture.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Izaberite boju za konturu", Color.BLACK);
					btnBojaKonture.setBackground(izborBoje);
				}
			});
			//btnBojaKonture.setBackground(Color.BLACK);
			pnlKomande.add(btnBojaKonture, "cell 1 4,grow");
		}
		{
			JLabel lblBojaUnutrasnjosti = new JLabel("Boja unutrasnjosti:");
			pnlKomande.add(lblBojaUnutrasnjosti, "cell 0 5,alignx right");
		}
		{
			 btnBojaUnutrasnjosti = new JButton("");
			 btnBojaUnutrasnjosti.setBackground(((Pravougaonik)GuiCrtanje.getSelektovan()).getBojaUnutrasnjosti());
			btnBojaUnutrasnjosti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Izaberite boju za konturu", Color.BLACK);
					btnBojaUnutrasnjosti.setBackground(izborBoje);
				}
			});
			//btnBojaUnutrasnjosti.setBackground(Color.WHITE);
			pnlKomande.add(btnBojaUnutrasnjosti, "cell 1 5,grow");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Potvrdi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							
							x=Integer.parseInt(txtX.getText());
							y=Integer.parseInt(txtY.getText());
							visina=Integer.parseInt(txtVisina.getText());
							sirina=Integer.parseInt(txtSirina.getText());
							
							if (x<=0 || y<=0 || visina<=0 || sirina<=0)
							{
								System.out.println("Greska, broj mora biti pozitivan");
								JOptionPane.showMessageDialog(null, "Greska pri unosu, broj mora biti pozitivan!");
							}
							
							else {
								setVisible(false);
								pr1= new Pravougaonik(new Tacka(x,y), visina, sirina, btnBojaKonture.getBackground(), btnBojaUnutrasnjosti.getBackground());
							}
							
						} catch (Exception e1) {
							
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
	
	public Pravougaonik getPodaci()
	{
		
		return pr1;
	}

}
