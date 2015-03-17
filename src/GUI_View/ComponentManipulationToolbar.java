package GUI_View;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;

public class ComponentManipulationToolbar extends JPanel {
	private static final long serialVersionUID = 1L;

	private GUIComponent selectedComponent;
	private JTabbedPane tabbPane;

	public ComponentManipulationToolbar() {
		init();
	}

	private void init() {
		this.setLayout(new MigLayout("", "[grow, fill]", ""));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		tabbPane = new JTabbedPane();
	}

	public GUIComponent getSelectedComponent() {
		return selectedComponent;
	}

	public void setSelectedComponent(GUIComponent selectedComponent) {
		this.selectedComponent = selectedComponent;		
		if (selectedComponent instanceof ClassFigure) {
			setClassFigureState();		
		}
	}

	public void hideToolbar() {
		this.removeAll();
		this.revalidate();
	}
	
	private void setClassFigureState() {
		this.removeAll();
		tabbPane.removeAll();
		tabbPane.addTab("Method Settings", new MethodMenu(selectedComponent));
		tabbPane.addTab("Variable Settings", new VariableMenu(selectedComponent));
		this.add(tabbPane);
	}

	public void updateMenyToolbar(int index) {
		tabbPane.setSelectedIndex(index);
	}

}
