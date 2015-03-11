package GUI_View;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;

public class VariableMenu extends JPanel implements IVisability{

	private GUIComponent selectedComponent;
	
	private JPanel variablePanel;
	
	private JButton addVariableButton, updateVariableButton;

	private JTextField variableNameField;
	private TitledBorder titledBorder;
	
	private String visabilityIdentyfier = "";

	private boolean isSelected;

	public VariableMenu(GUIComponent selectedComponent) {
		this.selectedComponent = selectedComponent;
		this.selectedComponent.getController().setVariableMenu(this);
		init();
	}

	private void init() {
		this.setLayout(new MigLayout("", "[grow, fill]", ""));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		createComponents();
	}

	private void createComponents() {

		variablePanel = panelSetup("Variable Name:");
		
		variableNameField = textFieldSetup("Variable Name:");

		addVariableButton = buttonSetup("Add field");
		updateVariableButton = buttonSetup("Update field");

		manipulateComponents();
	}

	private void manipulateComponents() {
		
		variablePanel.add(variableNameField);
		
		this.add(addVariableButton, "wrap");
		this.add(variablePanel, "wrap");
		this.add(new RadioButtonVisability(this), "wrap");
		this.add(updateVariableButton);
		
		addVariableButton.addActionListener(e -> newVariable());
		updateVariableButton.addActionListener(e -> updateVariable());
	}



	public String setVisabilityIdentyfier(String title) {
		return visabilityIdentyfier = title;
	}
	
	private JPanel panelSetup(String title) {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow, fill]", "grow"));
		titledBorder = BorderFactory.createTitledBorder(title);
		panel.setBorder(titledBorder);	
		return panel;	
	}

	private JTextField textFieldSetup(String title) {
		JTextField textfield = new JTextField();
		return textfield;
	}

	private JButton buttonSetup(String buttonText) {
		JButton jbnToolbarButtons = new JButton(buttonText);
		jbnToolbarButtons.setFocusPainted(false);
		return jbnToolbarButtons;
	}

	private void updateVariable() {
		if (selectedComponent instanceof ClassFigure) {
			int index = selectedComponent.getController().getIndexOfVariableList();
			
			if(index >= 0 && isSelected) {
				selectedComponent.getController().getVariables().get(index).setvariableName(variableNameField.getText());
				selectedComponent.getController().getVariables().get(index).setScopeModifier(visabilityIdentyfier);
			}
			((ClassFigure) selectedComponent).updatateList();
		}
		variableNameField.setText("");
	}
	
	private void newVariable() {
		if (selectedComponent instanceof ClassFigure) {
			selectedComponent.getController().newVariable();
			((ClassFigure) selectedComponent).updatateList();
		}
	}

	public void updateTextField(String nameInField) {
		variableNameField.setText(nameInField);		
	}

	@Override
	public void updateVisability(String returnType) {
		visabilityIdentyfier = returnType;	
	}

	public void setIsSelectedInList(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public boolean getIfSelectedInList() {
		return this.isSelected;
	}
}
