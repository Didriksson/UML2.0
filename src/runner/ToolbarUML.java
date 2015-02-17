package runner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolbarUML extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	public ToolbarUML() {
		
		super("Toolbar", JToolBar.VERTICAL);
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		this.setFloatable(false);
		
		addButtonsToToolbar(this);
	}
	

	public void addButtonsToToolbar(JToolBar jtbToolBar) {
		JButton jbnToolbarButtons = null;

	
		
		jbnToolbarButtons = buttonSettings(Constants.OPEN_FILE_ICON, "Open");
		jbnToolbarButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});		
		jtbToolBar.add(jbnToolbarButtons);
		
		
		
		jbnToolbarButtons = buttonSettings(Constants.SAVE_FILE_ICON, "Save");
		jbnToolbarButtons.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
			
			}
		});		
		jtbToolBar.add(jbnToolbarButtons);
	
		
		
		jbnToolbarButtons = buttonSettings(Constants.CLASS_ICON, "Class");
		jbnToolbarButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});		
		jtbToolBar.add(jbnToolbarButtons);
		
		
		
		jbnToolbarButtons = buttonSettings(Constants.ASSOCIATION_ICON, "Association");
		jbnToolbarButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});		
		jtbToolBar.add(jbnToolbarButtons);
		
		
		
		jbnToolbarButtons = buttonSettings(Constants.DIRECT_ASSOCIATION_ICON, "Direct Association");
		jbnToolbarButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});		
		jtbToolBar.add(jbnToolbarButtons);
		
		
		
		jbnToolbarButtons = buttonSettings(Constants.INHERITANCE_ICON, "Inheritance");
		jbnToolbarButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});		
		jtbToolBar.add(jbnToolbarButtons);
		
		
		
		jbnToolbarButtons = buttonSettings(Constants.DEPENDENCY_ICON, "Dependency");
		jbnToolbarButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});		
		jtbToolBar.add(jbnToolbarButtons);
		
		
		
		jbnToolbarButtons = buttonSettings(Constants.REALIZATION_ICON, "Realisation");
		jbnToolbarButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});		
		jtbToolBar.add(jbnToolbarButtons);
		
		
		
		jbnToolbarButtons = buttonSettings(Constants.AGGREGATION_ICON, "Aggregation");
		jbnToolbarButtons.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
			
			}
		});	
		jtbToolBar.add(jbnToolbarButtons);
		

		
		jbnToolbarButtons = buttonSettings(Constants.COMPOSITION_ICON, "Composition");
		jbnToolbarButtons.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
			
			}
		});	
		jtbToolBar.add(jbnToolbarButtons);		
	}

	
	private JButton buttonSettings(ImageIcon icon, String tipText) {
		JButton jbnToolbarButtons;
		jbnToolbarButtons = new JButton(icon);
		jbnToolbarButtons.setFocusPainted(false);
		jbnToolbarButtons.setToolTipText(tipText);
		return jbnToolbarButtons;
	}
	
	
}
