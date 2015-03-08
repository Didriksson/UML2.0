package GUI_View;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;

public class ComponentManipulationToolbar extends JPanel {
	private static final long serialVersionUID = 1L;

	private GUIComponent selectedComponent; 
	private JPanel modifyingPanel, addingPanel, visabilityPanel;

	private JButton addMethodButton, addVariableButton, updateMethodButton, updateVariableButton;
	private ButtonGroup buttonGroup;
	private JRadioButton visabilityPrivate, visabilityPublic, visabilityProtected, visabilityPackage;

	private JTextField methodNameField, variableNameField;
	private TitledBorder titledBorder;

	private String visabilityIdentyfier = "hej";
	
	public ComponentManipulationToolbar() {
		init();
	}

	private void init() {
		this.setLayout(new MigLayout("", "[grow, fill]", ""));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		createComponents();
	}

	private void createComponents() {
		modifyingPanel = panelSetup(false, "");
		addingPanel = panelSetup(false, "");
		visabilityPanel = panelSetup(true, "Visability");

		methodNameField = textFieldSetup("Method Name:");
		variableNameField = textFieldSetup("Variable Name:");
		
		buttonGroup = new ButtonGroup();
		visabilityPublic = radioButtonSetup(Constants.PUBLIC_RETURN_TYPE);
		visabilityPrivate = radioButtonSetup(Constants.PRIVATE_RETURN_TYPE);
		visabilityProtected = radioButtonSetup(Constants.PROTECTED_RETURN_TYPE);
		visabilityPackage = radioButtonSetup(Constants.PACKAGE_RETURN_TYPE);
		
		addMethodButton = buttonSetup("Add Method");
		addVariableButton = buttonSetup("Add field");
		updateMethodButton = buttonSetup("Update Method");
		updateVariableButton = buttonSetup("Update field");
			

		manipulateComponents();
	}

	private void manipulateComponents() {
		
		visabilityPanel.add(visabilityPublic);
		visabilityPanel.add(visabilityPrivate);
		visabilityPanel.add(visabilityProtected);
		visabilityPanel.add(visabilityPackage);
		
		modifyingPanel.add(methodNameField, "span 2 1");
		modifyingPanel.add(updateMethodButton, "wrap");
		modifyingPanel.add(variableNameField, "span 2 1");
		modifyingPanel.add(updateVariableButton, "wrap");
		modifyingPanel.add(visabilityPanel);
			
		addMethodButton.addActionListener(e -> newMethod());
		addVariableButton.addActionListener(e -> newVariable());
		
		updateMethodButton.addActionListener(e -> updateMethod());
		updateVariableButton.addActionListener(e -> updateVariable());

		visabilityPublic.addActionListener(e -> setVisabilityIdentyfier(Constants.PUBLIC_RETURN_TYPE));
		visabilityPrivate.addActionListener(e -> setVisabilityIdentyfier(Constants.PRIVATE_RETURN_TYPE));
		visabilityProtected.addActionListener(e -> setVisabilityIdentyfier(Constants.PROTECTED_RETURN_TYPE));
		visabilityPackage.addActionListener(e -> setVisabilityIdentyfier(Constants.PACKAGE_RETURN_TYPE));
		visabilityPublic.doClick();
		
		addingPanel.add(addMethodButton, "wrap");
		addingPanel.add(addVariableButton);

//		if(selectedComponent instanceof ClassFigure) {
//		if(selectedComponent.getController().getVariableState()) {
//			methodNameField.setEnabled(false);
//			updateMethodButton.setEnabled(false);
//		} else if(!selectedComponent.getController().getVariableState()) {
//			variableNameField.setEnabled(false);
//			updateVariableButton.setEnabled(false);
//		}
//	}
		
		
		this.add(modifyingPanel);
		this.add(addingPanel, "grow");
	}
	
	private JRadioButton radioButtonSetup(String title) {
		JRadioButton rbutton = new JRadioButton(title);
		buttonGroup.add(rbutton);
		return rbutton;
	}

	private String setVisabilityIdentyfier(String title) {
		return visabilityIdentyfier = title;
	}

	private JPanel panelSetup(boolean titleBorder, String title) {
		JPanel panel;
		panel = new JPanel(new MigLayout("", "[grow, fill]", "grow"));
			
		if(titleBorder){
			titledBorder = BorderFactory.createTitledBorder(title);	
			panel.setBorder(titledBorder);
		} else	
			panel.setBorder(Constants.RAISED_BEVEL_BORDER);
		
		return panel;
	}

	private JTextField textFieldSetup(String title) {
		JTextField textfield = new JTextField();
		titledBorder = BorderFactory.createTitledBorder(title);	
		textfield.setBorder(titledBorder);	
		return textfield;
	}
	
	private JButton buttonSetup(String buttonText) {
		JButton jbnToolbarButtons = new JButton(buttonText);
		jbnToolbarButtons.setFocusPainted(false);
		return jbnToolbarButtons;
	}
	

	private void updateMethod() {
		if (selectedComponent instanceof ClassFigure) {
			int index = selectedComponent.getController().getIndexOfVariableList();
			selectedComponent.getController().getMethods().get(index).setMethodName(methodNameField.getText());
			selectedComponent.getController().getMethods().get(index).setScopeModifier(visabilityIdentyfier);
			updateListInClassComponent();
		}
		methodNameField.setText("");
	}
	
	private void updateVariable() {
		if (selectedComponent instanceof ClassFigure) {
			int index = selectedComponent.getController().getIndexOfMethodList();
			selectedComponent.getController().getVariables().get(index).setvariableName(variableNameField.getText());
			selectedComponent.getController().getVariables().get(index).setScopeModifier(visabilityIdentyfier);
			updateListInClassComponent();
		}
		variableNameField.setText("");
	}
	
	private void newMethod() {
		if (selectedComponent instanceof ClassFigure) {
			selectedComponent.getController().newMethod();
			updateListInClassComponent();
		}
	}

	private void newVariable() {
		if (selectedComponent instanceof ClassFigure) {
			selectedComponent.getController().newVariable();
			updateListInClassComponent();
		}
	}
	
	private void updateListInClassComponent() {
		((ClassFigure) selectedComponent).updatateList();
	}
	
	public GUIComponent getSelectedComponent() {
		return selectedComponent;
	}
	
	public void setSelectedComponent(GUIComponent selectedComponent) {
		this.selectedComponent = selectedComponent;
	}
}
