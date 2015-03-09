package GUI_View;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;

public class ComponentManipulationToolbar extends JPanel {
	private static final long serialVersionUID = 1L;

	private GUIComponent selectedComponent;

	public ComponentManipulationToolbar() {
		init();
	}

	private void init() {
		this.setLayout(new MigLayout("", "[grow, fill]", ""));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
	}

	public void updateListInClassComponent() {
		((ClassFigure) selectedComponent).updatateList();
	}

	public GUIComponent getSelectedComponent() {
		return selectedComponent;
	}

	public void setSelectedComponent(GUIComponent selectedComponent) {
		this.selectedComponent = selectedComponent;		
		if (selectedComponent instanceof ClassFigure) 
			setClassFigureState();
	}

	public void hideToolbar() {
		this.removeAll();
		this.revalidate();
	}
	
	private void setClassFigureState() {
		this.removeAll();
		this.add(new MethodMenu(selectedComponent), "grow");
		this.add(new VariableMenu(selectedComponent), "grow");
	}

}
