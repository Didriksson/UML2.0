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

import UML.Components.UMLVariable;
import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;

public class MethodMenu extends JPanel implements IVisability {
	private static final long serialVersionUID = 1L;

	private GUIComponent selectedComponent;

	private JPanel methodPanel, methodReturnTypePanel, parameterPanel;

	private JLabel methodNameLabel, returnTypeMethodListLabel, returnTypeMethodFieldLabel, parameterNameLabel, returnTypeParameterListLabel,
			returnTypeParameterFieldLabel;
	
	private JTextField methodNameField, parameterNameField, returnTypeMethodField ,returnTypeParameterField;

	private JButton addMethodButton, updateMethodButton, addParameterButton;
	private ButtonGroup buttonGroupParameters, buttonGroupMethods;
	private JRadioButton returnTypeListParaRButton;
	private JRadioButton returnTypeFieldParaRButton;
	private JRadioButton returnTypeListMethodsRButton;
	private JRadioButton returnTypeFieldMethodsRButton;

	private TitledBorder titledBorder;

	private JComboBox<String> returnTypelistParameter, returnTypelistMethod;
	private Vector<String> returnTypeVectorList;

	private String visabilityIdentyfier = "";

	private boolean isSelected = false;

	public MethodMenu(GUIComponent selectedComponent) {
		this.selectedComponent = selectedComponent;
		this.selectedComponent.getController().setMethodMenu(this);
		init();
	}

	private void init() {
		this.setLayout(new MigLayout("", "[grow, fill]", ""));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		createComponents();
	}

	private void createComponents() {

		methodPanel = panelSetup("Method Settings:");
		methodReturnTypePanel = panelSetup("Return Type");
		parameterPanel = panelSetup("Parameter Settings:");

		methodNameLabel = labelSetup("Mehod Name:");
		returnTypeMethodListLabel = labelSetup("Return Type");
		returnTypeMethodFieldLabel = labelSetup("Return Type");
		
		parameterNameLabel = labelSetup("Parameter Name:");
		returnTypeParameterListLabel = labelSetup("Return Type:");
		returnTypeParameterFieldLabel = labelSetup("Return Type:");

		methodNameField = textFieldSetup();
		returnTypeMethodField = textFieldSetup();
		parameterNameField = textFieldSetup();
		returnTypeParameterField = textFieldSetup();

		addMethodButton = buttonSetup("Add Default Method");
		updateMethodButton = buttonSetup("Update Method");
		addParameterButton = buttonSetup("Add");

		buttonGroupParameters = new ButtonGroup();
		returnTypeListParaRButton = radioButtonSetup(false);
		returnTypeFieldParaRButton = radioButtonSetup(false);
		
		buttonGroupMethods = new ButtonGroup();
		returnTypeListMethodsRButton = radioButtonSetup(true);
		returnTypeFieldMethodsRButton = radioButtonSetup(true);

		returnTypeVectorList = new Vector<String>(selectedComponent
				.getController().getParameterList());
	
		returnTypelistParameter = new JComboBox<String>(returnTypeVectorList);
		returnTypelistMethod = new JComboBox<String>(returnTypeVectorList);
		
		manipulateComponents();
	}

	private void manipulateComponents() {
		methodReturnTypePanel.add(returnTypeMethodListLabel, "wrap");
		methodReturnTypePanel.add(returnTypeListMethodsRButton);
		methodReturnTypePanel.add(returnTypelistMethod, "wrap");
		methodReturnTypePanel.add(returnTypeMethodFieldLabel, "wrap");
		methodReturnTypePanel.add(returnTypeFieldMethodsRButton);
		methodReturnTypePanel.add(returnTypeMethodField);
		
		methodPanel.add(addMethodButton);
		methodPanel.add(methodReturnTypePanel, "span 1 4, wrap");
		methodPanel.add(methodNameLabel, "wrap");
		methodPanel.add(methodNameField, "wrap");
		methodPanel.add(new RadioButtonVisability(this), "wrap");
		methodPanel.add(updateMethodButton, "span 2 1");		

		
		parameterPanel.add(parameterNameLabel, "wrap");
		parameterPanel.add(parameterNameField, "span 2 1, wrap");
		parameterPanel.add(returnTypeParameterListLabel, "wrap");
		parameterPanel.add(returnTypeListParaRButton);
		parameterPanel.add(returnTypelistParameter, "wrap");
		parameterPanel.add(returnTypeParameterFieldLabel, "wrap");
		parameterPanel.add(returnTypeFieldParaRButton);
		parameterPanel.add(returnTypeParameterField, "wrap");
		parameterPanel.add(addParameterButton, "span 2 1");

		this.add(methodPanel);
		this.add(parameterPanel);


		addMethodButton.addActionListener(e -> newMethod());
		updateMethodButton.addActionListener(e -> updateMethod());
		addParameterButton.addActionListener(e -> addParametersFromList());

		returnTypeListMethodsRButton.doClick();
		returnTypeListMethodsRButton.addActionListener(e -> {
			returnTypelistMethod.setEnabled(true);
			returnTypeMethodField.setEnabled(false);
		});

		returnTypeMethodField.setEnabled(false);
		returnTypeFieldMethodsRButton.addActionListener(e -> {
			returnTypeMethodField.setEnabled(true);
			returnTypelistMethod.setEnabled(false);
		});
	
		
		returnTypeListParaRButton.doClick();
		returnTypeListParaRButton.addActionListener(e -> {
			returnTypelistParameter.setEnabled(true);
			returnTypeParameterField.setEnabled(false);
		});

		returnTypeParameterField.setEnabled(false);
		returnTypeFieldParaRButton.addActionListener(e -> {
			returnTypeParameterField.setEnabled(true);
			returnTypelistParameter.setEnabled(false);
		});
	}

	public String setVisabilityIdentyfier(String title) {
		return visabilityIdentyfier = title;
	}

	private JTextField textFieldSetup() {
		JTextField textfield = new JTextField();
		return textfield;
	}

	private JLabel labelSetup(String title) {
		JLabel label = new JLabel();
		label.setText(title);
		label.setOpaque(false);

		return label;
	}

	private JRadioButton radioButtonSetup(boolean methodType) {
		JRadioButton rbutton = new JRadioButton();
		
		if(methodType) {
			buttonGroupMethods.add(rbutton);
		} else
			buttonGroupParameters.add(rbutton);
		return rbutton;
	}

	private JButton buttonSetup(String buttonText) {
		JButton jbnToolbarButtons = new JButton(buttonText);
		jbnToolbarButtons.setFocusPainted(false);
		return jbnToolbarButtons;
	}

	private JPanel panelSetup(String title) {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow, fill]", "grow"));
		titledBorder = BorderFactory.createTitledBorder(title);
		panel.setBorder(titledBorder);
		return panel;
	}

	private void newMethod() {
		if (selectedComponent instanceof ClassFigure) {
			selectedComponent.getController().newMethod();
			((ClassFigure) selectedComponent).updatateList();
		}
	}

	private void updateMethod() {
		if (selectedComponent instanceof ClassFigure) {
			int index = selectedComponent.getController()
					.getIndexOfMethodList();
			if (index >= 0 && isSelected && !methodNameField.getText().isEmpty()) {
				if (!returnTypeMethodField.getText().isEmpty()) {
					selectedComponent
							.getController()
							.getMethods()
							.get(index)
							.setReturnType(returnTypeMethodField.getText());
							
				} else {
					String returnType = String.valueOf(returnTypelistMethod.getSelectedItem());
					selectedComponent
							.getController()
							.getMethods()
							.get(index)
							.setReturnType(returnType);
				}
				
				
				selectedComponent.getController().getMethods().get(index)
						.setMethodName(methodNameField.getText());
				selectedComponent.getController().getMethods().get(index)
						.setScopeModifier(visabilityIdentyfier);
			}
			((ClassFigure) selectedComponent).updatateList();
		}
		methodNameField.setText("");
	}

	private void addParametersFromList() {
		if (selectedComponent instanceof ClassFigure) {
			int index = selectedComponent.getController()
					.getIndexOfMethodList();
			if (index >= 0 && isSelected && !parameterNameField.getText().isEmpty()) {
				if (!returnTypeParameterField.getText().isEmpty()) {
					selectedComponent
							.getController()
							.getMethods()
							.get(index)
							.addParameter(
									new UMLVariable(returnTypeParameterField.getText(),
											parameterNameField.getText()));
				} else {
					String returnType = String.valueOf(returnTypelistParameter
							.getSelectedItem());
					selectedComponent
							.getController()
							.getMethods()
							.get(index)
							.addParameter(
									new UMLVariable(returnType,
											parameterNameField.getText()));
				}
			}
			((ClassFigure) selectedComponent).updatateList();
		}
		parameterNameField.setText("");
	}
	
		
	public void setIsSelectedInList(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public boolean getIfSelectedInList() {
		return this.isSelected;
	}
	

	public void updateTextField(String nameInField) {
		methodNameField.setText(nameInField);
	}

	@Override
	public void updateVisability(String returnType) {
		visabilityIdentyfier = returnType;
	}
}
