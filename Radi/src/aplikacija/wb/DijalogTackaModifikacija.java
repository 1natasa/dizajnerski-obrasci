package aplikacija.wb;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import aplikacija.wb.GuiCrtanje;
import model.Point;


public class DijalogTackaModifikacija extends JDialog {

	private final JPanel pnlDugmici = new JPanel();
	private JTextField txtX;
	private JTextField txtY;


	private int x;
	private int y;
	Color bojaKonture;
	Point tacka ;
	Point t1= new Point();
	JButton btnBojaKonture;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DijalogTackaModifikacija dialog = new DijalogTackaModifikacija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DijalogTackaModifikacija() {
		
		setModal(true);
		
	
		
		setBounds(100, 100, 379, 226);
		getContentPane().setLayout(new BorderLayout());
		pnlDugmici.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlDugmici, BorderLayout.CENTER);
		pnlDugmici.setLayout(new MigLayout("", "[][][][103px][86px]", "[][][20px][20px][]"));
		{
			JLabel lblX = new JLabel("Unesite x koordinatu:");
			pnlDugmici.add(lblX, "cell 3 2,alignx right,growy");
		}
		{
			txtX = new JTextField();
			pnlDugmici.add(txtX, "cell 4 2,alignx left,aligny center");
			txtX.setColumns(10);
			
			txtX.setText("" + ((Point) GuiCrtanje.getSelektovan()).getX());
		
			
		
		}
		{
			JLabel lblY = new JLabel("Unesite y koordinatu:");
			pnlDugmici.add(lblY, "cell 3 3,alignx right,aligny center");
		}
		{
			txtY = new JTextField();
			pnlDugmici.add(txtY, "cell 4 3,alignx left,aligny center");
			txtY.setColumns(10);
			txtY.setText("" + ((Point) GuiCrtanje.getSelektovan()).getY());
		}
		{
			JLabel lblBojaKonture = new JLabel("Boja konture:");
			pnlDugmici.add(lblBojaKonture, "cell 3 4");
		}
		{
			btnBojaKonture = new JButton("");
			System.out.println(((Point) GuiCrtanje.getSelektovan()).getColor());
			btnBojaKonture.setBackground(((Point) GuiCrtanje.getSelektovan()).getColor());
			btnBojaKonture.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Izaberite boju za konturu", Color.BLACK);
					btnBojaKonture.setBackground(izborBoje);
				}
			});
			//btnBojaKonture.setBackground(Color.BLACK);
			pnlDugmici.add(btnBojaKonture, "cell 4 4,alignx center");
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
							
							
							
							if (x <= 0 || y<=0 )
							{
								JOptionPane.showMessageDialog(null, "Koordinate moraju da budu pozitivne");
								System.out.println("Pogresan unos, koordinate negativne");
							}
							
							else {
								tacka= new Point (x,y,btnBojaKonture.getBackground());
								setVisible(false);
							}
						} catch (Exception e1) {
							
							
							System.out.println("greska pri unosu nije unet broj");
							JOptionPane.showMessageDialog(null, "Greska pri unosu nije unet broj");
							
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public Point getPodaci()
	{
		return tacka;
	}

	


}
