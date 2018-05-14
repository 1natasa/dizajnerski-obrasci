package aplikacija.wb;

import java.awt.BorderLayout;



import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import aplikacija.wb.PanelZaCrtanje;
import model.Circle;
import model.Square;
import model.Line;
import model.Shape;
import model.Pravougaonik;
import model.Point;

public class GuiCrtanje extends JFrame {
	
	private int xTacka;
	private int yTacka;
	Point t1;
	Line l1;
	Square kv1;
	Pravougaonik pr1;
	Circle kr1;
	
	
	private PanelZaCrtanje crtanje;
	private JButton btnOdabranoDugme;
	private Shape oblik=null;
	private JButton btnObrisi;
	static Shape selektovan = null;
	DijalogTackaModifikacija dt;
	 private Stack<Shape>stek = new Stack<Shape>();
	
	
	int klik = 1;

	private JPanel pnlGlavni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiCrtanje frame = new GuiCrtanje();
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
	public GuiCrtanje() {
		
		
		crtanje=new PanelZaCrtanje();
		crtanje.setBackground(Color.white);
		
		setTitle("Natasa Bosnjak IT7/15");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 400);
		pnlGlavni = new JPanel();
		pnlGlavni.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlGlavni.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlGlavni);
		
		JPanel pnlKomande = new JPanel();
		pnlGlavni.add(pnlKomande, BorderLayout.NORTH);
		pnlKomande.setLayout(new MigLayout("", "[97px][5px][71px][17.00][62px][53px][19.00][95px]", "[14px][23px][23px][23px]"));
		pnlGlavni.add(crtanje, BorderLayout.CENTER);
		
		JLabel lblOblici = new JLabel("Oblici");
		pnlKomande.add(lblOblici, "cell 0 0 3 1,alignx center,aligny center");
		
		JButton btnTacka = new JButton("Tacka");
		btnTacka.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "Kliknite na ekran gde zelite da se nacrta tacka");
				
		
				
				
				btnOdabranoDugme=btnTacka;
				
				
			}
		});
		
		JLabel lblBoje = new JLabel("Boje");
		pnlKomande.add(lblBoje, "cell 4 0,alignx center,growy");
		pnlKomande.add(btnTacka, "cell 0 1,grow");
		
		JButton btnLinija = new JButton("Linija");
		btnLinija.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Kliknite na dva mesta na ekranu, izmedju kojih zelite da se nacrta linija");
				btnOdabranoDugme=btnLinija;
			}
		});
		pnlKomande.add(btnLinija, "cell 2 1,grow");
		
		JButton btnPravougaonik = new JButton("Pravougaonik");
		btnPravougaonik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOdabranoDugme=btnPravougaonik;
				JOptionPane.showMessageDialog(null, "Kliknite na ekran gde zelite da se nacrta pravougaonik");
				
				
			}
		});
		
		JSeparator separator = new JSeparator();
		pnlKomande.add(separator, "cell 3 0 1 4");
		
		JLabel lblBojaIvice = new JLabel("Kontura");
		pnlKomande.add(lblBojaIvice, "cell 4 1,alignx center,aligny center");
		
		JButton btnKontura = new JButton("");
		btnKontura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
				Color izborBoje= jcc.showDialog(null, "izaberite boju za konturu", Color.black);
				btnKontura.setBackground(izborBoje);
			}
		});
		
		
		btnKontura.setBackground(Color.BLACK);
		btnKontura.setForeground(Color.BLACK);
		pnlKomande.add(btnKontura, "cell 5 1,grow");
		
		JButton btnSelektovanje = new JButton("Selektovanje");
		btnSelektovanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnOdabranoDugme=btnSelektovanje;
				
			}
		});
	
		
		JSeparator separator_1 = new JSeparator();
		pnlKomande.add(separator_1, "cell 6 0 1 4");
		pnlKomande.add(btnSelektovanje, "cell 7 1,alignx right,aligny center");
		pnlKomande.add(btnPravougaonik, "cell 0 2,grow");
		
		JButton btnKvadrat = new JButton("Kvadrat");
		btnKvadrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnOdabranoDugme=btnKvadrat;
				JOptionPane.showMessageDialog(null, "Kliknite na ekran gde zelite da se nacrta kvadrat");
			
				
			
				
				
			}
		});
		pnlKomande.add(btnKvadrat, "cell 2 2,grow");
		
		JLabel lblBojaUnutrasnjosti = new JLabel("Unutrasnjost");
		pnlKomande.add(lblBojaUnutrasnjosti, "cell 4 2,alignx center,aligny center");
		
		JButton btnUnutrasnjost = new JButton("");
		btnUnutrasnjost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
				Color izborBoje= jcc.showDialog(null, "izaberite boju za unutrasnjost", Color.black);
				btnUnutrasnjost.setBackground(izborBoje);
				
				
			}
		});
		
		
		btnUnutrasnjost.setBackground(Color.WHITE);
		pnlKomande.add(btnUnutrasnjost, "cell 5 2,grow");
		
		JButton btnKrug = new JButton("Krug");
		btnKrug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnOdabranoDugme=btnKrug;
				
				
				JOptionPane.showMessageDialog(null, "Kliknite na mesto na kome zelite da se nacrta krug");
			}
		});
		
		JButton btnModifikacija = new JButton("Modifikacija");
		btnModifikacija.setEnabled(false);
		
		
		pnlKomande.add(btnModifikacija, "cell 7 2,growx");
		pnlKomande.add(btnKrug, "cell 0 3,grow");
		
		 btnObrisi = new JButton("Obrisi");
		 btnObrisi.setEnabled(false);
			btnObrisi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Brisanje b= new Brisanje();
					b.setVisible(true);
					
					if (b.getOpcije()==1)
					{
						stek.remove(selektovan);
						crtanje.remove(selektovan);
						//ovo sad radi 
						//ponovoCrtaNakonIzmena();
						repaint();
						selektovan=null;
					}
				
					
					btnModifikacija.setEnabled(false);
					btnObrisi.setEnabled(false);				
					
				}
			});
	
		pnlKomande.add(btnObrisi, "cell 7 3,growx");
	
		
		crtanje.addMouseListener(new MouseAdapter() {
		
		
			public void mousePressed(MouseEvent e) {
				
				//System.out.println("nije proslo if");
				
				if (btnOdabranoDugme==btnTacka)
				{
					xTacka=e.getX();
					yTacka=e.getY();
					t1 = new Point (xTacka, yTacka, btnKontura.getBackground());
					t1.drawShape(crtanje.getGraphics());
					crtanje.add(t1);
					stek.push(t1);
					//System.out.println("uso u if");
					
					
					
				}
				else if (btnOdabranoDugme==btnLinija)
				{
					if(klik == 1){
						 xTacka=e.getX();
						 yTacka=e.getY();
						 klik++;
					}else{
						 int xTacka1=e.getX();
						 int yTacka1=e.getY();
						 
						 System.out.println(btnKontura.getBackground());
						 l1 = new Line( new Point(xTacka,yTacka), new Point(xTacka1,yTacka1),btnKontura.getBackground());
						 l1.drawShape(crtanje.getGraphics());
						 crtanje.add(l1);
						 stek.push(l1);
						 
						 klik = 1;
					}
				  
				  
				
				}
				
				else if (btnOdabranoDugme==btnKvadrat)
				{
					xTacka=e.getX();
					yTacka=e.getY();
					DijalogKvadrataCrtanje dk = new DijalogKvadrataCrtanje();
					dk.setVisible(true);
					kv1=new Square(new Point(xTacka, yTacka), dk.getDuzinaStranice(), btnKontura.getBackground(), btnUnutrasnjost.getBackground());
					
					kv1.drawShape(crtanje.getGraphics());
					crtanje.add(kv1);
					stek.push(kv1);
				}
				else if (btnOdabranoDugme==btnPravougaonik)
				{
					
						xTacka=e.getX();
						yTacka=e.getY();
						
						DijalogPravougaonikCrtanje dijalogPravougaonik = new DijalogPravougaonikCrtanje();
						dijalogPravougaonik.setVisible(true);
						pr1= new Pravougaonik(new Point(xTacka, yTacka), dijalogPravougaonik.getSirina(),dijalogPravougaonik.getDuzina(), btnKontura.getBackground(), btnUnutrasnjost.getBackground());
						pr1.drawShape(crtanje.getGraphics());
						crtanje.add(pr1);
						stek.push(pr1);
					
				}
				else if (btnOdabranoDugme==btnKrug)
				{
					xTacka=e.getX();
					yTacka=e.getY();
					//repaint();
					DijalogKrugCrtanje dijalogKrug= new DijalogKrugCrtanje();
					dijalogKrug.setVisible(true);
					kr1= new Circle(new Point(xTacka, yTacka), dijalogKrug.getPoluprecnik(), btnKontura.getBackground(), btnUnutrasnjost.getBackground());
					System.out.println(dijalogKrug.getPoluprecnik());
					kr1.drawShape(crtanje.getGraphics());
					crtanje.add(kr1);
					stek.push(kr1);		
					
				}
				else if (btnOdabranoDugme==btnSelektovanje)
				{
					btnModifikacija.setEnabled(false);
					btnObrisi.setEnabled(false);
					if(selektovan!= null){
						selektovan.setSelected(false);
						//selektovan=null;
						repaint();
					}
					selektovan = null;
					xTacka=e.getX();
					yTacka=e.getY();
					
					
				
					for(int i = stek.size() - 1; i >= 0  ; i--)
					{
						if(stek.elementAt(i).contains(xTacka, yTacka)){
							
							selektovan = stek.elementAt(i);
							
							selektovan.setSelected(true);
							
							//repaint();
							
							btnModifikacija.setEnabled(true);
							btnObrisi.setEnabled(true);
							
							
							repaint();
							return;
						}
						
					}
					
				}
				
				
				
				System.out.println("Trenutno stanje na steku");
				
				for(int i = stek.size() - 1; i >= 0  ; i--)
				{
					System.out.println(stek.elementAt(i));
				}
				
				
			}
		});
		
		
		btnModifikacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selektovan instanceof Point)
				{
					
					
					
					 dt = new DijalogTackaModifikacija();
					
				     dt.setVisible(true);
				     
				     if (dt.getPodaci()==null)
				     {
				    	 
				     }else {
					
					
					stek.removeElement(selektovan);
					crtanje.remove(selektovan);
					t1=dt.getPodaci();
					repaint();
					t1.drawShape(crtanje.getGraphics());
					crtanje.add(t1);
					stek.push(t1);
					selektovan=null;
				     }
					
				}
				else if (selektovan instanceof Line)
				{
					
					DijalogLinija dl = new DijalogLinija();
					
					dl.setVisible(true);
					
					if (dl.getPodaci()==null)
					{
						
					} else {
					stek.removeElement(selektovan);
					crtanje.remove(selektovan);
					l1=dl.getPodaci();
					
				//	ponovoCrtaNakonIzmena();
					crtanje.repaint();
					
					l1.drawShape(crtanje.getGraphics());
					crtanje.add(l1);
					stek.push(l1);
					
					selektovan=null;
					}
					
					
				} else if (selektovan instanceof Pravougaonik) {
					
					DijalogPravougaonikModifikacija dpp= new DijalogPravougaonikModifikacija();
					dpp.setVisible(true);
					
					if (dpp.getPodaci()==null)
					{
						
					} else {
					stek.removeElement(selektovan);
					crtanje.remove(selektovan);
					pr1=dpp.getPodaci();
					//ponovoCrtaNakonIzmena();
					repaint();
					pr1.drawShape(crtanje.getGraphics());
					crtanje.add(pr1);
					stek.push(pr1);
					selektovan=null;
					}
					
				}
				
				
				else if (selektovan instanceof Square) {
					
					
					
					
					DijalogKvadratModifikacija dk = new DijalogKvadratModifikacija();
					
					
					dk.setVisible(true);
					if (dk.getPodaci()==null)
					{
						
					} else{
					
					stek.removeElement(selektovan);
					crtanje.remove(selektovan);
					kv1=dk.getPodaci();
					//ponovoCrtaNakonIzmena();
					repaint();
					kv1.drawShape(crtanje.getGraphics());
					crtanje.add(kv1);
					stek.push(kv1);
					selektovan=null;
					}
					
				}  else if (selektovan instanceof Circle)
					
				{
					DijalogKrugModifikacija dk= new DijalogKrugModifikacija();
					
					dk.setVisible(true);
					
					if (dk.getPodaci()==null)
					{
						
					} else {
					
					stek.removeElement(selektovan);
					crtanje.remove(selektovan);
					kr1=dk.getPodaci();
				//	ponovoCrtaNakonIzmena();
					repaint();
					kr1.drawShape(crtanje.getGraphics());
					crtanje.add(kr1);
					stek.push(kr1);
					selektovan=null;
					
					}	
						
					
					
				}
				
				
				btnModifikacija.setEnabled(false);
				btnObrisi.setEnabled(false);
			}
			
		});
		
		
		
	}
	
	static Shape getSelektovan(){
		return selektovan;
	}
	
	


	

	
}
