package aplikacija.wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Line;
import model.Point;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DijalogLinija extends JDialog {

	private final JPanel pnlKomande = new JPanel();
	private JTextField txtX1;
	private JTextField txtY1;
	private JTextField txtX2;
	private JTextField txtY2;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	Line l1=null;
	JButton btnBojaLinije;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DijalogLinija dialog = new DijalogLinija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DijalogLinija() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlKomande.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlKomande, BorderLayout.CENTER);
		pnlKomande.setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][]"));
		{
			JLabel lblX1 = new JLabel("X koordinatu prve tacke:");
			pnlKomande.add(lblX1, "cell 3 2,alignx left");
		}
		{
			txtX1 = new JTextField();
			pnlKomande.add(txtX1, "cell 4 2,alignx center");
			txtX1.setColumns(10);
			txtX1.setText("" + ((Line)GuiCrtanje.getSelektovan()).getStart().getX());
		}
		{
			JLabel lblY1 = new JLabel("Y koordinatu prve tacke:");
			pnlKomande.add(lblY1, "cell 3 3,alignx left");
		}
		{
			txtY1 = new JTextField();
			pnlKomande.add(txtY1, "cell 4 3,alignx center");
			txtY1.setColumns(10);
			txtY1.setText(""  + ((Line)GuiCrtanje.getSelektovan()).getStart().getY());
		}
		{
			JLabel lblX2 = new JLabel("X koordinata druge tacke:");
			pnlKomande.add(lblX2, "cell 3 4,alignx left");
		}
		{
			txtX2 = new JTextField();
			pnlKomande.add(txtX2, "cell 4 4,alignx center");
			txtX2.setColumns(10);
			txtX2.setText("" + ((Line)GuiCrtanje.getSelektovan()).getEnd().getX());
		}
		{
			JLabel lblY2 = new JLabel("Y koordinata druge tacke:");
			pnlKomande.add(lblY2, "cell 3 5,alignx left");
		}
		{
			txtY2 = new JTextField();
			pnlKomande.add(txtY2, "cell 4 5,alignx center");
			txtY2.setColumns(10);
			txtY2.setText("" + ((Line)GuiCrtanje.getSelektovan()).getEnd().getY());
		}
		{
			JLabel lblBoja = new JLabel("Boja:");
			pnlKomande.add(lblBoja, "cell 3 6,alignx left");
		}
		{
			btnBojaLinije = new JButton("");
			btnBojaLinije.setBackground(Color.BLACK);
		
			btnBojaLinije.setBackground(((Line) GuiCrtanje.getSelektovan()).getColor());

			btnBojaLinije.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					JColorChooser jcc = new JColorChooser();
					Color izborBoje =jcc.showDialog(null, "Izaberite boju za konturu", Color.BLACK);
					btnBojaLinije.setBackground(izborBoje);
				}
			});
			pnlKomande.add(btnBojaLinije, "cell 4 6,alignx center");
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
							x1=Integer.parseInt(txtX1.getText());
							y1=Integer.parseInt(txtY1.getText());
							x2=Integer.parseInt(txtX2.getText());
							y2=Integer.parseInt(txtY2.getText());
							
							if (x1<=0 || y1<=0 || x2<=0 || y2<=0)
							{
								System.out.println("Greska pri unosu, negativan broj");
								JOptionPane.showMessageDialog(null, "Greska, broj je negativan");
							}
							else
							{
								l1=new Line (new Point (x1,y1), new Point (x2,y2), btnBojaLinije.getBackground());
								setVisible(false);
							}
							
							
							
							
						} catch (Exception e1) {
							
							System.out.println("greska pri unosu, nije unet broj");
							JOptionPane.showMessageDialog(null, "Greska, nije unet broj!!");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public Line getPodaci()
	{
		return l1;
	}
}
