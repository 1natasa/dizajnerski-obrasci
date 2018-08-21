package paint.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class PaintFrame extends JFrame {
	//frame sluzi samo da prikazuje nesto, ne cuva podatke, sluzi samo da prikaze model
	


	private View view;
	
	private JToggleButton btnPoint;
	private JToggleButton btnLine;
	private JToggleButton btnSqaure;
	private JToggleButton btnRectangle;
	private JToggleButton btnCircle;
	private JToggleButton btnHexagon;
	private JToggleButton btnSelect;
	private JButton btnModification;
	private JButton btnDelete;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnOutsideColor;
	private JButton btnInsideColor;
	private JMenu fileMenu;
	private JMenuBar menuBar;
	private JMenuItem itemLoadDrawing;
	private JMenuItem itemSaveDrawing;
	private JMenuItem itemLoadLog;
	private JMenuItem itemSaveLog;
	
	private JMenu editMenu;
	private JMenuItem itemRedo;
	private JMenuItem itemUndo;
	
	private JScrollPane scrollPanel;
	private JTextArea logTextArea;
	
	
	private JPanel mainPanel;

	private Controller controller;
	
	public PaintFrame(View view) {
		
		
		this.view=view;
		setTitle("Natasa Bosnjak IT7/15");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(mainPanel);
		
		
		JPanel shapeButtonsPanel = new JPanel();
		JPanel commandsPanel = new JPanel();
		commandsPanel.setLayout(new GridLayout(0, 1, 0, 0) );
		
		
		JPanel colorPanel = new JPanel();
		JPanel northPanel = new JPanel();
	
		scrollPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		logTextArea = new JTextArea();	
		scrollPanel.add(logTextArea);
		scrollPanel.setPreferredSize(new Dimension(200, 200));
		
		mainPanel.setLayout(new BorderLayout());
			
		
		
		this.view.setBackground(Color.WHITE);
		
		mainPanel.add(scrollPanel, BorderLayout.SOUTH);
		mainPanel.add(this.view, BorderLayout.CENTER);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(commandsPanel, BorderLayout.WEST);
		
		
		this.view.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				controller.drawShape(e);
			}
		});
				
		
		
		btnPoint = new JToggleButton("Point");
		
		btnLine = new JToggleButton("Line");
		
		btnCircle = new JToggleButton("Circle");
		
		btnSqaure = new JToggleButton("Sqaure");
		
		btnRectangle = new JToggleButton("Rectangle");

		btnHexagon = new JToggleButton("Hexagon");
		
		btnSelect = new JToggleButton("Select");
		
		
		shapeButtonsPanel.add(btnPoint);
		shapeButtonsPanel.add(btnLine);
		shapeButtonsPanel.add(btnCircle);
		shapeButtonsPanel.add(btnSqaure);
		shapeButtonsPanel.add(btnRectangle);
		shapeButtonsPanel.add(btnHexagon);
		shapeButtonsPanel.add(btnSelect);

		
		
		btnInsideColor = new JButton();
		
		btnOutsideColor = new JButton();
		btnInsideColor.setPreferredSize(new Dimension(50, 20));
		btnOutsideColor.setPreferredSize(new Dimension(50, 20));
		
		btnInsideColor.setBackground(Color.WHITE);
		btnOutsideColor.setBackground(Color.BLACK);
		
		btnInsideColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openInsideColorChooser();
			}
		});
		
		btnOutsideColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.openOutsideColorChooser();
			}
		});
		
		Label lblInsideColor = new Label("Inside color");
		
		Label lblOutsideColor = new Label("Outside color");
		
		colorPanel.add(lblInsideColor);
		colorPanel.add(btnInsideColor);
		colorPanel.add(lblOutsideColor);
		colorPanel.add(btnOutsideColor);
		
		
		
		northPanel.add(shapeButtonsPanel);
		northPanel.add(colorPanel);
		
		
		
		
		btnBringToBack = new JButton("Bring to Back");
		
		btnBringToFront = new JButton("Bring to Front");
		
		btnToBack = new JButton("To back");
		
		btnToFront = new JButton("To front");
		
		btnDelete = new JButton("Delete");
	
		btnModification = new JButton("Modification");
	
		
		
		commandsPanel.add(btnBringToBack);
		commandsPanel.add(btnBringToFront);
		commandsPanel.add(btnToBack);
		commandsPanel.add(btnToFront);
		commandsPanel.add(btnDelete);
		commandsPanel.add(btnModification);
		
		btnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			}
		});
		
		
		btnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			}
		});
		
		btnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			}
		});
		
		btnToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteShapes();
				
			}
		});
		
		btnModification.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			}
		});
		
		itemLoadDrawing = new JMenuItem("Load Drawing");
		itemSaveDrawing =  new JMenuItem("Save Drawing");
		itemUndo = new JMenuItem("Undo");
		itemRedo =  new JMenuItem("Redo");
		itemLoadLog = new JMenuItem("Load Log");
		itemSaveLog = new JMenuItem("Save log");
		
		fileMenu = new JMenu("File");
		fileMenu.add(itemSaveDrawing);
		fileMenu.add(itemLoadDrawing);
		
		fileMenu.addSeparator();
		
		fileMenu.add(itemSaveLog);
		fileMenu.add(itemLoadLog);
		
		
		editMenu = new JMenu("Edit");
		editMenu.add(itemUndo);
		editMenu.add(itemRedo);
		
		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		setJMenuBar(menuBar);	
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(btnPoint);
		btnGroup.add(btnLine);
		btnGroup.add(btnCircle);
		btnGroup.add(btnSqaure);
		btnGroup.add(btnRectangle);
		btnGroup.add(btnHexagon);
		btnGroup.add(btnSelect);

		
	}
	
	public void repaintView()
	{
		this.view.repaint();
	}

	public JToggleButton getBtnPoint() {
		return btnPoint;
	}

	public JToggleButton getBtnLine() {
		return btnLine;
	}

	public JToggleButton getBtnSqaure() {
		return btnSqaure;
	}

	public JToggleButton getBtnRectangle() {
		return btnRectangle;
	}

	public JToggleButton getBtnCircle() {
		return btnCircle;
	}

	public JToggleButton getBtnHexagon() {
		return btnHexagon;
	}

	public JToggleButton getBtnSelect() {
		return btnSelect;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public JButton getBtnOutsideColor() {
		return btnOutsideColor;
	}

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}
	
	
}
