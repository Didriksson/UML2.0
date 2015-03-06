package GUI_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;

public class ComponentManipulationToolbar extends JPanel {
    private GUIComponent selectedComponent;
    private JButton addMethodButton;
    private JButton addVariableButton;

    public ComponentManipulationToolbar() {
	this.setBorder(Constants.RAISED_BEVEL_BORDER);
	this.setVisible(true);
	addMethodButton = new JButton("Add Method");
	addVariableButton = new JButton("Add field");
	addMethodButton.addActionListener(e -> newMethod());
	addVariableButton.addActionListener(e -> newVariable());

	this.add(addMethodButton);
	this.add(addVariableButton);
    }

    private void newVariable() {
	this.selectedComponent.getController().newVariable();
	((ClassFigure) selectedComponent).updatateList();

    }

    public GUIComponent getSelectedComponent() {
	return selectedComponent;
    }

    public void setSelectedComponent(GUIComponent selectedComponent) {
	this.selectedComponent = selectedComponent;
    }

    private void newMethod() {
	if (selectedComponent instanceof ClassFigure) {
	    selectedComponent.getController().newMethod();
	    ((ClassFigure) selectedComponent).updatateList();
	}
    }
}
