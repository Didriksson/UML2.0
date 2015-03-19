package GUI_View;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import junit.framework.TestFailure;
import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;
import Figures.ISelectable;
import Figures.Graphics.AssociationFigure;

public class ComponentManipulationToolbar extends JPanel {
	private static final long serialVersionUID = 1L;

	private ISelectable selectedComponent;
	private JTabbedPane tabbPane;

	
	public ComponentManipulationToolbar() {
		init();
	}

	private void init() {
		this.setLayout(new MigLayout("", "[grow, fill]", ""));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		tabbPane = new JTabbedPane();
	}

	public ISelectable getSelectedComponent() {
		return selectedComponent;
	}

	public void setSelectedComponent(ISelectable selectedFigure) {
		this.selectedComponent = selectedFigure;		
		if (selectedFigure instanceof ClassFigure) {
			setClassFigureState();		
		} else if(selectedFigure instanceof AssociationFigure)
			setAssociationState();
	}

	private void setAssociationState() {
		this.removeAll();
		this.add(new AssocationMenu((AssociationFigure)selectedComponent));
	}

	public void updateToolbar(ISelectable selectedFigure) {
		if(selectedFigure != null)
			setSelectedComponent(selectedFigure);
		else{
			this.removeAll();
			this.revalidate();
		}
	}
	
	private void setClassFigureState() {
		this.removeAll();
		tabbPane.removeAll();
		tabbPane.addTab("Method Settings", new MethodMenu((GUIComponent)selectedComponent));
		tabbPane.addTab("Variable Settings", new VariableMenu((GUIComponent)selectedComponent));
		this.add(tabbPane);
	}

	public void updateMenyToolbar(int index) {
		if (this.selectedComponent instanceof ClassFigure)
			tabbPane.setSelectedIndex(index);				
	}

}
