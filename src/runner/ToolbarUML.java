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
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.OPENFILE_ENUM));	
		jtbToolBar.add(jbnToolbarButtons);
		
				
		jbnToolbarButtons = buttonSettings(Constants.SAVE_FILE_ICON, "Save");
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.SAVEFILE_ENUM));		
		jtbToolBar.add(jbnToolbarButtons);
	
			
		jbnToolbarButtons = buttonSettings(Constants.CLASS_ICON, "Class");
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.CLASS_ENUM));	
		jtbToolBar.add(jbnToolbarButtons);
		
			
		jbnToolbarButtons = buttonSettings(Constants.ASSOCIATION_ICON, "Association");
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.ASSOCIATION_ENUM));	
		jtbToolBar.add(jbnToolbarButtons);
		
		
		jbnToolbarButtons = buttonSettings(Constants.DIRECT_ASSOCIATION_ICON, "Direct Association");
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.DIRECT_ASSOCIATION_ENUM));	
		jtbToolBar.add(jbnToolbarButtons);
		
		
		jbnToolbarButtons = buttonSettings(Constants.INHERITANCE_ICON, "Inheritance");
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.INHERITANCE_ENUM));	
		jtbToolBar.add(jbnToolbarButtons);
		
		
		jbnToolbarButtons = buttonSettings(Constants.DEPENDENCY_ICON, "Dependency");
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.DEPENDENCY_ENUM));	
		jtbToolBar.add(jbnToolbarButtons);
				
		
		jbnToolbarButtons = buttonSettings(Constants.REALIZATION_ICON, "Realisation");
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.REALIZATION_ENUM));		
		jtbToolBar.add(jbnToolbarButtons);
			
		
		jbnToolbarButtons = buttonSettings(Constants.AGGREGATION_ICON, "Aggregation");
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.AGGREGATION_ENUM));
		jtbToolBar.add(jbnToolbarButtons);		

		
		jbnToolbarButtons = buttonSettings(Constants.COMPOSITION_ICON, "Composition");
		jbnToolbarButtons.addActionListener(new MyListerner(Enums.COMPOSITION_ENUM)); 	
		jtbToolBar.add(jbnToolbarButtons);		
	}
	
	private JButton buttonSettings(ImageIcon icon, String tipText) {
		JButton jbnToolbarButtons;
		jbnToolbarButtons = new JButton(icon);
		jbnToolbarButtons.setFocusPainted(false);
		jbnToolbarButtons.setToolTipText(tipText);
		
		return jbnToolbarButtons;
	}
	
	private class MyListerner implements ActionListener {

		private Enums enums;
		
		public MyListerner(Enums enums) {
			this.enums = enums;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(enums) {
				case OPENFILE_ENUM:
				
					break;
					
				case SAVEFILE_ENUM:
					
					break;
					
				case CLASS_ENUM:
					
					break;
					
				case ASSOCIATION_ENUM:
					
					break;
					
				case DIRECT_ASSOCIATION_ENUM:
					
					break;
					
				case INHERITANCE_ENUM:
					
					break;
		
				case DEPENDENCY_ENUM:
					
					break;
					
				case REALIZATION_ENUM:
					
					break;
					
				case AGGREGATION_ENUM:
					
					break;

				case COMPOSITION_ENUM:
					
					break;
			
			}
		}
	}
}
