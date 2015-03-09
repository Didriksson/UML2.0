package GUI_View;

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
	
	private JButton addVariableButton, updateVariableButton;

	private JTextField variableNameField;
	private TitledBorder titledBorder;
	
	private String visabilityIdentyfier = "";

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

		variableNameField = textFieldSetup("Variable Name:");


		addVariableButton = buttonSetup("Add field");
		updateVariableButton = buttonSetup("Update field");

		manipulateComponents();
	}

	private void manipulateComponents() {
		
		this.add(addVariableButton, "wrap");
		this.add(variableNameField, "span 2 1");
		this.add(updateVariableButton, "wrap");
		this.add(new RadioButtonVisability(this));
		
		
		addVariableButton.addActionListener(e -> newVariable());
		updateVariableButton.addActionListener(e -> updateVariable());
	}



	public String setVisabilityIdentyfier(String title) {
		return visabilityIdentyfier = title;
	}


	private JTextField textFieldSetup(String title) {
		JTextField textfield = new JTextField();
//		titledBorder = BorderFactory.createTitledBorder(title);
//		textfield.setBorder(titledBorder);
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
			
			if(index >= 0) {
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
}
