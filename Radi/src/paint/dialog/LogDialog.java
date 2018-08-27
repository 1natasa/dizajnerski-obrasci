package paint.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import paint.mvc.PaintFrame;
import paint.serialization.LogStrategy;
import paint.serialization.Strategy;

public class LogDialog extends JDialog{
	
	private JPanel commandPanel = new JPanel();
	private JPanel logPanel = new JPanel();
	private LogStrategy strategy;
	private PaintFrame frame;
	private JScrollPane scrollPanel ;
	
	
	
	public LogDialog(LogStrategy strategy, PaintFrame frame) {
		this.strategy=strategy;
		this.frame=frame;
		
		setTitle("Input log");
		setModal(true); 
		setBounds(100, 100, 403, 286);
		getContentPane().setLayout(new BorderLayout());
		commandPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(commandPanel, BorderLayout.CENTER);
		commandPanel.setLayout(new MigLayout("", "[92.00][58px][86px]", "[][20px][]"));
		JTextArea text = new JTextArea();
		logPanel.add(text);
		text.setPreferredSize(new Dimension(500, 350));
		
		scrollPanel = new JScrollPane(logPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		logPanel.setPreferredSize(new Dimension(500, 350));
		logPanel.setLayout(new FlowLayout(FlowLayout.CENTER) );
		logPanel.setBackground(Color.WHITE);
		commandPanel.add(scrollPanel, "cell 0 0 3 2");
		
			JButton btnNext = new JButton("Next");
			JButton btnAll = new JButton("All");
			btnNext.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String line =strategy.executeOneLine();
					text.setText(text.getText()+line + '\n');
					frame.getLogTextArea().setText(frame.getLogTextArea().getText() + line + '\n');
					frame.repaintView();
					if(!strategy.hasNextLine())
					{
						btnNext.setEnabled(false);
						btnAll.setEnabled(false);
					}
					
				}
			});
			btnNext.setPreferredSize(new Dimension(50, 50));
			commandPanel.add(btnNext, "cell 1 2,alignx center,aligny center");
		
		
			
			btnAll.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					while(strategy.hasNextLine())
					{
						String line =strategy.executeOneLine();
						text.setText(text.getText()+line + '\n');
						frame.getLogTextArea().setText(frame.getLogTextArea().getText() + line + '\n');
						frame.repaintView();
					}
					
						btnNext.setEnabled(false);
						btnAll.setEnabled(false);
					
					
				}
			});
			btnAll.setPreferredSize(new Dimension(50, 50));
			commandPanel.add(btnAll, "cell 2 2,alignx center,aligny center");
		
		
	}

}
