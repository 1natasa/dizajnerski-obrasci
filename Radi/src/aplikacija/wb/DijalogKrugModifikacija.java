package aplikacija.wb;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import aplikacija.wb.GuiCrtanje;
import model.Circle;
import model.Point;
public class DijalogKrugModifikacija extends JDialog {

	
	private final JPanel pnlKomande = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtPoluprecnik;
	private int x;
	private int y;
	private int poluprecnik;
	private JButton btnBojaKonture;
	private JButton btnBojaUnutrasnjosti;
	Circle k=null;
	 
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DijalogKrugModifikacija dialog = new DijalogKrugModifikacija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	/**
	 * Create the dialog.
	 */
	public DijalogKrugModifikacija() {
		setModal(true);
		
		setBounds(100, 100, 450, 300);
		
		getContentPane().setLayout(new BorderLayout());
		pnlKomande.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlKomande, BorderLayout.CENTER);
		pnlKomande.setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][]"));
		{
			JLabel lblX = new JLabel("X koordinata centra:");
			pnlKomande.add(lblX, "cell 3 2,alignx left");
		}
		{
			txtX = new JTextField();
			pnlKomande.add(txtX, "cell 4 2,alignx center");
			txtX.setColumns(10);
			txtX.setText(""+ ((Circle)GuiCrtanje.getSelektovan()).getCentar().getX());
		}
		{
			JLabel lblY = new JLabel("Y koordinata centra:");
			pnlKomande.add(lblY, "cell 3 3,alignx left");
		}
		{
			txtY = new JTextField();
			pnlKomande.add(txtY, "cell 4 3,alignx center");
			txtY.setColumns(10);
			txtY.setText("" + ((Circle)GuiCrtanje.getSelektovan()).getCentar().getY());
		}
		{
			JLabel lblPoluprecnik = new JLabel("Poluprecnik:");
			pnlKomande.add(lblPoluprecnik, "cell 3 4,alignx left");
		}
		{
			txtPoluprecnik = new JTextField();
			pnlKomande.add(txtPoluprecnik, "cell 4 4,alignx center");
			txtPoluprecnik.setColumns(10);
			txtPoluprecnik.setText("" + ((Circle)GuiCrtanje.getSelektovan()).getR());
		}
		{
			JLabel lblBojaKonture = new JLabel("Boja konture:");
			pnlKomande.add(lblBojaKonture, "cell 3 5");
		}
		{
			 btnBojaKonture = new JButton("");
			 btnBojaKonture.setBackground(((Circle)GuiCrtanje.getSelektovan()).getColor());
			 btnBojaKonture.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
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
			pnlKomande.add(lblBojaUnutrasnjosti, "cell 3 6,alignx left");
		}
		{
			 btnBojaUnutrasnjosti = new JButton("");
			 btnBojaUnutrasnjosti.setBackground(((Circle)GuiCrtanje.getSelektovan()).getInsideColor());
			 btnBojaUnutrasnjosti.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Izaberite boju za unutrasnjost", Color.BLACK);
					btnBojaUnutrasnjosti.setBackground(izborBoje);
			 	}
			 });
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
							poluprecnik=Integer.parseInt(txtPoluprecnik.getText());
							
							if (x <=0 || y<=0 || poluprecnik<=0)
							{
								System.out.println("Grska pri unosu, broj mora biti pozitivan");
								JOptionPane.showMessageDialog(null, "Greska, brojevi moraju biti pozitivni");
							}
							
							else{
								setVisible(false);
								k= new Circle(new Point (x,y), poluprecnik, btnBojaKonture.getBackground(), btnBojaUnutrasnjosti.getBackground());
							}
							
						} catch (Exception e) {
							
							System.out.println("greska, nije unet broj");
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
	
	public Circle getPodaci()
	{
		return k;
	}

	
}
