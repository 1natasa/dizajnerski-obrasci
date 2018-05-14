package aplikacija.wb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GuiSortiranje extends JFrame {

	private JPanel pnlGlavni;
	List lista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiSortiranje frame = new GuiSortiranje();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiSortiranje() {
		setTitle("Natasa Bosnjak IT7/15");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pnlGlavni = new JPanel();
		pnlGlavni.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlGlavni.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlGlavni);
		
		JPanel panel = new JPanel();
		pnlGlavni.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblRandomLista = new JLabel("Nesortirana lista");
		GridBagConstraints gbc_lblRandomLista = new GridBagConstraints();
		gbc_lblRandomLista.insets = new Insets(0, 0, 5, 5);
		gbc_lblRandomLista.gridx = 0;
		gbc_lblRandomLista.gridy = 1;
		panel.add(lblRandomLista, gbc_lblRandomLista);
		
		JLabel lblSortiranaLista = new JLabel("Sortirana lista");
		GridBagConstraints gbc_lblSortiranaLista = new GridBagConstraints();
		gbc_lblSortiranaLista.insets = new Insets(0, 0, 5, 0);
		gbc_lblSortiranaLista.gridx = 1;
		gbc_lblSortiranaLista.gridy = 1;
		panel.add(lblSortiranaLista, gbc_lblSortiranaLista);
		
		List listNesortirana = new List();
		GridBagConstraints gbc_listNesortirana = new GridBagConstraints();
		gbc_listNesortirana.fill = GridBagConstraints.BOTH;
		gbc_listNesortirana.insets = new Insets(0, 0, 0, 5);
		gbc_listNesortirana.gridx = 0;
		gbc_listNesortirana.gridy = 2;
		panel.add(listNesortirana, gbc_listNesortirana);
		
		List listSortirana = new List();
		GridBagConstraints gbc_listSortirana = new GridBagConstraints();
		gbc_listSortirana.fill = GridBagConstraints.BOTH;
		gbc_listSortirana.gridx = 1;
		gbc_listSortirana.gridy = 2;
		panel.add(listSortirana, gbc_listSortirana);
		
		ArrayList<Square> lista = new ArrayList<Square>();
		
		JButton btnDodajUListu = new JButton("Dodaj u listu");
		btnDodajUListu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DijalogKvadratStek dk = new DijalogKvadratStek();
				dk.setVisible(true);
				Square k = dk.getPodaci();
				if (k != null)
				{
				listNesortirana.add(k.description());
				}
				
				lista.add(dk.getPodaci());
				lista.sort(null);
				
				listSortirana.removeAll();
				
				for(Square k1 : lista){
					listSortirana.add(k1.description());
				}
				
			}
		});
		
		
	
		
		GridBagConstraints gbc_btnDodajUListu = new GridBagConstraints();
		gbc_btnDodajUListu.gridwidth = 2;
		gbc_btnDodajUListu.insets = new Insets(0, 0, 5, 0);
		gbc_btnDodajUListu.gridx = 0;
		gbc_btnDodajUListu.gridy = 0;
		panel.add(btnDodajUListu, gbc_btnDodajUListu);
		
		
		
		
	}

}
