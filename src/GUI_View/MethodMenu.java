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

	private JPanel methodPanel, parameterPanel;

	private JLabel parameterNameLabel, returnTypeListLabel,
			returnTypeFieldLabel;
	private JTextField methodNameField, parameterNameField, returnTypeField;

	private JButton addMethodButton, updateMethodButton, addParameterButton;
	private ButtonGroup buttonGroup;
	private JRadioButton returnTypeListRButton;
	private JRadioButton returnTypeFieldRButton;

	private TitledBorder titledBorder;

	private JComboBox<String> returnTypelist;
	private Vector<String> paratmeterVectorList;

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

		methodPanel = panelSetup("Method Name:");
		parameterPanel = panelSetup("Parameters:");

		parameterNameLabel = labelSetup("Parameter Name:");
		returnTypeListLabel = labelSetup("Return Type List:");
		returnTypeFieldLabel = labelSetup("Return Type:");

		methodNameField = textFieldSetup();
		parameterNameField = textFieldSetup();
		returnTypeField = textFieldSetup();

		addMethodButton = buttonSetup("Add New Method");
		updateMethodButton = buttonSetup("Update Method");
		addParameterButton = buttonSetup("Add");

		buttonGroup = new ButtonGroup();
		returnTypeListRButton = radioButtonSetup();
		returnTypeFieldRButton = radioButtonSetup();

		paratmeterVectorList = new Vector<String>(selectedComponent
				.getController().getParameterList());
		returnTypelist = new JComboBox<String>(paratmeterVectorList);
		manipulateComponents();
	}

	private void manipulateComponents() {
		
		methodPanel.add(methodNameField);

		parameterPanel.add(parameterNameLabel, "wrap");
		parameterPanel.add(parameterNameField, "span 2 1, wrap");
		parameterPanel.add(returnTypeListLabel, "wrap");
		parameterPanel.add(returnTypeListRButton);
		parameterPanel.add(returnTypelist, "wrap");
		parameterPanel.add(returnTypeFieldLabel, "wrap");
		parameterPanel.add(returnTypeFieldRButton);
		parameterPanel.add(returnTypeField, "wrap");
		parameterPanel.add(addParameterButton, "span 2 1");

		this.add(addMethodButton);
		this.add(parameterPanel, "span 1 6, wrap");
		this.add(methodPanel, "wrap");
		this.add(new RadioButtonVisability(this), "wrap");
		this.add(updateMethodButton);

		addMethodButton.addActionListener(e -> newMethod());
		updateMethodButton.addActionListener(e -> updateMethod());
		addParameterButton.addActionListener(e -> addParametersFromList());

		
		returnTypeListRButton.doClick();
		returnTypeListRButton.addActionListener(e -> {
			returnTypelist.setEnabled(true);
			returnTypeField.setEnabled(false);
		});

		returnTypeField.setEnabled(false);
		returnTypeFieldRButton.addActionListener(e -> {
			returnTypeField.setEnabled(true);
			returnTypelist.setEnabled(false);
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

	private JRadioButton radioButtonSetup() {
		JRadioButton rbutton = new JRadioButton();
		buttonGroup.add(rbutton);
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
			if (index >= 0 && isSelected) {

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
				if (!returnTypeField.getText().isEmpty()) {
					selectedComponent
							.getController()
							.getMethods()
							.get(index)
							.addParameter(
									new UMLVariable(returnTypeField.getText(),
											parameterNameField.getText()));
				} else {
					String returnType = String.valueOf(returnTypelist
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
