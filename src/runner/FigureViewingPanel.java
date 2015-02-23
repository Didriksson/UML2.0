package runner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class FigureViewingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
   
	private Resizable res, res1;
	private JTextArea textArea1,textArea2;
	private JScrollPane scrollPane1, scrollPane2;
	private JTextField classNameField;
	
	private JPanel umlPanel, classPanel, classPanel1;

	public FigureViewingPanel() {
		this.setLayout(null);
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		createComponents();
	}
	
	
	private void createComponents() {	
		umlPanel = new JPanel(null);
		classPanel = new JPanel(new MigLayout("fill","grow","grow"));
		classPanel1 = new JPanel(new MigLayout("fill","grow","grow"));
		
		res = new Resizable(classPanel);
		res1 = new Resizable(classPanel1);		
		textArea1 = new JTextArea();
		textArea2 = new JTextArea();
		scrollPane1 = new JScrollPane(textArea1);
		scrollPane2 = new JScrollPane(textArea2);		
		
		classNameField = new JTextField("ClassName");	

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                requestFocus();
                res.repaint();
                res1.repaint();
            }
        });
		
		setComponents();
	}

	private void setComponents() {
		res.setBounds(50, 50, 200, 200);
		res1.setBounds(100, 100, 200, 200);
		
		textArea1.setBackground(Color.LIGHT_GRAY);
		textArea2.setBackground(Color.LIGHT_GRAY);
			
		classPanel.add(classNameField, "dock north");
		classPanel.add(scrollPane1, "dock center");
		classPanel.add(scrollPane2, "dock south");
		
		this.add(res);
		this.add(res1);
		this.add(new ToolbarUML(), BorderLayout.WEST);
       
	}
		

	public void paintComponent(final Graphics g) {
	    super.paintComponent(g);
	    if (Constants.FIGURE_VIEWER_BACKGROUND != null)
	        g.drawImage(Constants.FIGURE_VIEWER_BACKGROUND, 0, 0, getWidth(), getHeight(), this);
  
	}
}
