package paint.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

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

import paint.commands.Command;
import paint.commands.CommandManager;


@SuppressWarnings("serial")
public class PaintFrame extends JFrame implements Observer {
	//frame sluzi samo da prikazuje nesto, ne cuva podatke, sluzi samo da prikaze model
	//TO JE OBSERVER


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
		JPanel southPanel = new JPanel();
		scrollPanel = new JScrollPane(southPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setBackground(Color.RED);
		logTextArea = new JTextArea();	
		southPanel.add(logTextArea);
		
		//logTextArea.setBackground(Color.LIGHT_GRAY);
		
		scrollPanel.setMinimumSize(new Dimension(200, 200));
		logTextArea.setPreferredSize(new Dimension(1200, 200));
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
		
	
		btnInsideColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.openInsideColorChooser();
			}
		});
		
		btnOutsideColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
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
	
		btnBringToFront.setEnabled(false);
		btnBringToBack.setEnabled(false);
		btnToBack.setEnabled(false);
		btnToFront.setEnabled(false);
		btnModification.setEnabled(false);
		btnDelete.setEnabled(false);
		
		commandsPanel.add(btnBringToBack);
		commandsPanel.add(btnBringToFront);
		commandsPanel.add(btnToBack);
		commandsPanel.add(btnToFront);
		commandsPanel.add(btnDelete);
		commandsPanel.add(btnModification);
		
		btnBringToBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.bringToBack();
				
			}
		});
		
		btnBringToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				controller.bringToFront();
				
			}
		});
		
		btnToBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.toBack();
				
			}
		});
		
		btnToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.toFront();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteShapes();
				
			}
		});
		
		btnModification.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.modificationShapes();
				
			}
		});
		
		itemLoadDrawing = new JMenuItem("Load Drawing");
		itemSaveDrawing =  new JMenuItem("Save Drawing");
		itemUndo = new JMenuItem("Undo");
		itemRedo =  new JMenuItem("Redo");
		itemLoadLog = new JMenuItem("Load Log");
		itemSaveLog = new JMenuItem("Save log");
		
		itemRedo.setEnabled(false);
		itemUndo.setEnabled(false);
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
		
		itemUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.undo();
			}
		});
		
		itemRedo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.redo();
			}
		});
		
		itemSaveDrawing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			controller.saveDrawing();
				
			}
		});
		
		itemLoadDrawing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			controller.loadDrawing();
				
			}
		});
		
		itemSaveLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.saveLog();
			}
		});

		itemLoadLog.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.loadLog();
			}
		});

		
	}
	
	
	
	public JTextArea getLogTextArea() {
		return logTextArea;
	}


	public void setLogTextArea(JTextArea logTextArea) {
		this.logTextArea = logTextArea;
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
	
	public JMenuItem getItemRedo() {
		return itemRedo;
	}


	public JMenuItem getItemUndo() {
		return itemUndo;
	}



	@Override
	public void update(Observable o, Object arg) {
		//logika kad se pale kad se gase dugmici
		//svaki put ce se pozvati kad dodje do notify 
		if(o instanceof Model)
		{
			
			
			Model model = (Model) o;
			int selectedNumber=model.getSelectedShapes().size();
			
			if(selectedNumber==1)
			{
				btnDelete.setEnabled(true);
				btnModification.setEnabled(true);
				int index=model.getIndexOfShape(model.getSelectedShapes().get(0));
				if (index ==0)
				{
					btnToBack.setEnabled(false);
					btnBringToBack.setEnabled(false);
					btnToFront.setEnabled(true);
					btnBringToFront.setEnabled(true);
				} else if (index==model.getShapes().size()-1)
				{
					btnToBack.setEnabled(true);
					btnBringToBack.setEnabled(true);
					btnToFront.setEnabled(false);
					btnBringToFront.setEnabled(false);
				} else
				{
					btnToBack.setEnabled(true);
					btnBringToBack.setEnabled(true);
					btnToFront.setEnabled(true);
					btnBringToFront.setEnabled(true);
				}
				
			} else if (selectedNumber > 1)
			{
				btnModification.setEnabled(false);
				btnDelete.setEnabled(true);
				btnBringToBack.setEnabled(false);
				btnBringToFront.setEnabled(false);
				btnToBack.setEnabled(false);
				btnToFront.setEnabled(false);
			} else 
			{
				btnBringToFront.setEnabled(false);
				btnBringToBack.setEnabled(false);
				btnToBack.setEnabled(false);
				btnToFront.setEnabled(false);
				btnModification.setEnabled(false);
				btnDelete.setEnabled(false);
			}
		} else 
		{
			CommandManager commandManager = (CommandManager) o;
			
			if(commandManager.canUndo())
			{
				itemUndo.setEnabled(true);			
			} else
			{
				itemUndo.setEnabled(false);
			}
			if(commandManager.canRedo())
			{
				itemRedo.setEnabled(true);			
			} else
			{
				itemRedo.setEnabled(false);
			}
		}
		
	}

	public void setModelForView(Model model)
	{
		this.view.setModel(model);
	}


	
	
}
