package GUI_View;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;

public class VariableMenu extends JPanel implements IVisability{

	private GUIComponent selectedComponent;
	
	private JPanel variablePanel, variableReturnTypePanel;
	
	private JButton addVariableButton, deleteVariableButton, updateVariableButton;

	private JTextField variableNameField;
	private JTextField returnTypeVariableField;
	private TitledBorder titledBorder;
	
	private String visabilityIdentyfier = "";

	private boolean isSelected;

	private ButtonGroup buttonGroupVariables;
	private JRadioButton returnTypeListVariablesRButton;
	private JRadioButton returnTypeFieldVariablesRButton;

	private JLabel variableNameLabel;
	private JLabel returnTypeVariableListLabel;
	private JLabel returnTypeVariableFieldLabel;

	private Vector<String> returnTypeVectorList;
	private JComboBox<String> returnTypelistVariable;

	

	public VariableMenu(GUIComponent selectedComponent) {
		this.selectedComponent = selectedComponent;
		this.selectedComponent.getController().setVariableMenu(this);
		init();
	}

	private void init() {
		this.setLayout(new MigLayout("", "[grow, fill]", "grow"));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		createComponents();
	}

	private void createComponents() {

		variablePanel = panelSetup("Variable Settings:");
		variableReturnTypePanel = panelSetup("Return Type");
		
		variableNameField = textFieldSetup();
		returnTypeVariableField = textFieldSetup();
		
		variableNameLabel = labelSetup("Variable Name:");
		returnTypeVariableListLabel = labelSetup("Return Type");
		returnTypeVariableFieldLabel = labelSetup("Return Type");

		addVariableButton = buttonSetup("Add field");
		deleteVariableButton = buttonSetup("Delete field");
		updateVariableButton = buttonSetup("Update field");
		
		buttonGroupVariables = new ButtonGroup();
		returnTypeListVariablesRButton = radioButtonSetup();
		returnTypeFieldVariablesRButton = radioButtonSetup();

		returnTypeVectorList = new Vector<String>(selectedComponent
				.getController().getParameterList());
		
		returnTypelistVariable = new JComboBox<String>(returnTypeVectorList);
		
		manipulateComponents();
	}

	private void manipulateComponents() {
		
		variableReturnTypePanel.add(returnTypeVariableListLabel, "wrap");
		variableReturnTypePanel.add(returnTypeListVariablesRButton);
		variableReturnTypePanel.add(returnTypelistVariable, "wrap");
		variableReturnTypePanel.add(returnTypeVariableFieldLabel, "wrap");
		variableReturnTypePanel.add(returnTypeFieldVariablesRButton);
		variableReturnTypePanel.add(returnTypeVariableField);
			
		variablePanel.add(addVariableButton);
		variablePanel.add(deleteVariableButton, "wrap");
		variablePanel.add(variableNameLabel, "span 2 1, wrap");
		variablePanel.add(variableNameField, "span 2 1, wrap");
		variablePanel.add(new RadioButtonVisability(this), "span 2 1, wrap");
		variablePanel.add(updateVariableButton, "span 2 1");
		
		
		
		this.add(variablePanel);
		this.add(variableReturnTypePanel);
		
			
		
		addVariableButton.addActionListener(e -> newVariable());
		deleteVariableButton.addActionListener(e -> deleteVariable());
		updateVariableButton.addActionListener(e -> updateVariable());
		
		returnTypeListVariablesRButton.doClick();
		returnTypeListVariablesRButton.addActionListener(e -> {
			returnTypelistVariable.setEnabled(true);
			returnTypeVariableField.setEnabled(false);
		});

		returnTypeVariableField.setEnabled(false);
		returnTypeFieldVariablesRButton.addActionListener(e -> {
			returnTypeVariableField.setEnabled(true);
			returnTypelistVariable.setEnabled(false);
		});
		
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

	private JTextField textFieldSetup() {
		JTextField textfield = new JTextField();
		return textfield;
	}

	private JButton buttonSetup(String buttonText) {
		JButton jbnToolbarButtons = new JButton(buttonText);
		jbnToolbarButtons.setFocusPainted(false);
		return jbnToolbarButtons;
	}
	
	private JRadioButton radioButtonSetup() {
		JRadioButton rbutton = new JRadioButton();
				buttonGroupVariables.add(rbutton);
		return rbutton;
	}
	
	private JLabel labelSetup(String title) {
		JLabel label = new JLabel();
		label.setText(title);
		label.setOpaque(false);

		return label;
	}

	private void updateVariable() {
		if (selectedComponent instanceof ClassFigure) {
			int index = selectedComponent.getController().getIndexOfVariableList();
			
			if(index >= 0 && isSelected && !variableNameField.getText().isEmpty()) {
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
	
	private void deleteVariable() {
		if (selectedComponent instanceof ClassFigure) {
			int index = selectedComponent.getController()
					.getIndexOfVariableList();
			if (index >= 0 && isSelected)
				selectedComponent.getController().removeComponentVariable(index);
			
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
