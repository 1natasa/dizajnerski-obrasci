package aplikacija.wb;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import geometrija.Kvadrat;

import java.util.Stack;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.List;
public class GuiStek extends JFrame {
	 private Stack <Kvadrat> StekKvadrat = new Stack<Kvadrat>();
	Kvadrat kv1 = new Kvadrat();

	private JPanel pnlGlavni;
	List list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiStek frame = new GuiStek();
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
	public GuiStek() {
		setTitle("Stek-Natasa Bosnjak IT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pnlGlavni = new JPanel();
		pnlGlavni.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlGlavni);
		pnlGlavni.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		pnlGlavni.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{97, 99, 0};
		gbl_panel.rowHeights = new int[]{0, 201, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnDodajNaStek = new JButton("Dodaj na stek");
		GridBagConstraints gbc_btnDodajNaStek = new GridBagConstraints();
		gbc_btnDodajNaStek.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDodajNaStek.insets = new Insets(0, 0, 5, 5);
		gbc_btnDodajNaStek.anchor = GridBagConstraints.NORTH;
		gbc_btnDodajNaStek.gridx = 0;
		gbc_btnDodajNaStek.gridy = 0;
		panel.add(btnDodajNaStek, gbc_btnDodajNaStek);
		btnDodajNaStek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DijalogKvadrat dk = new DijalogKvadrat();
				dk.show();
				System.out.println("sledeca naredba");
				Kvadrat k = dk.getPodaci();
				
				list.add(k.toString(), 0);
			
			}
		});
		
		JButton btnSkiniSaSteka = new JButton("Skini sa steka");
		GridBagConstraints gbc_btnSkiniSaSteka = new GridBagConstraints();
		gbc_btnSkiniSaSteka.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSkiniSaSteka.insets = new Insets(0, 0, 5, 0);
		gbc_btnSkiniSaSteka.gridx = 1;
		gbc_btnSkiniSaSteka.gridy = 0;
		panel.add(btnSkiniSaSteka, gbc_btnSkiniSaSteka);
		btnSkiniSaSteka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getItemCount() <= 0)
					System.out.println("stek je prazan!");
				else
					list.remove(0);
			}
		});
		
		list = new List();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridwidth = 2;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);
	}
}
