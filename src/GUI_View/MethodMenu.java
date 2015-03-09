package GUI_View;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;

public class MethodMenu extends JPanel implements IVisability {
	private static final long serialVersionUID = 1L;

	private GUIComponent selectedComponent;

	private JButton addMethodButton, updateMethodButton, addParameterButton;

	private JTextField methodNameField;
	private TitledBorder titledBorder;

	private JComboBox<String> parameterlist;
	private Vector<String> paratmeterVectorList;

	private String visabilityIdentyfier = "";

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

		methodNameField = textFieldSetup("Method Name:");

		addMethodButton = buttonSetup("Add Method");
		updateMethodButton = buttonSetup("Update Method");
		addParameterButton = buttonSetup("Add Parameter");

		paratmeterVectorList = new Vector<String>();
		parameterlist = new JComboBox<String>(paratmeterVectorList);
		manipulateComponents();
	}

	private void manipulateComponents() {

		this.add(addMethodButton, "wrap");
		this.add(methodNameField);
		this.add(updateMethodButton, "wrap");
		this.add(parameterlist);
		this.add(addParameterButton, "wrap");
		this.add(new RadioButtonVisability(this));

		addMethodButton.addActionListener(e -> newMethod());
		updateMethodButton.addActionListener(e -> updateMethod());

	}

	public String setVisabilityIdentyfier(String title) {
		return visabilityIdentyfier = title;
	}

	private JTextField textFieldSetup(String title) {
		JTextField textfield = new JTextField();
		// titledBorder = BorderFactory.createTitledBorder(title);
		// textfield.setBorder(titledBorder)
		return textfield;
	}

	private JButton buttonSetup(String buttonText) {
		JButton jbnToolbarButtons = new JButton(buttonText);
		jbnToolbarButtons.setFocusPainted(false);
		return jbnToolbarButtons;
	}

	private void updateMethod() {
		if (selectedComponent instanceof ClassFigure) {
			int index = selectedComponent.getController()
					.getIndexOfMethodList();
			if (index >= 0) {			
								
				selectedComponent.getController().getMethods().get(index)
						.setMethodName(methodNameField.getText());
				selectedComponent.getController().getMethods().get(index)
						.setScopeModifier(visabilityIdentyfier);
			}
			((ClassFigure) selectedComponent).updatateList();
		}
		methodNameField.setText("");
	}

	private void newMethod() {
		if (selectedComponent instanceof ClassFigure) {
			selectedComponent.getController().newMethod();
			((ClassFigure) selectedComponent).updatateList();
		}
	}

	public void updateTextField(String nameInField) {
		methodNameField.setText(nameInField);		
	}

	@Override
	public void updateVisability(String returnType) {
		visabilityIdentyfier = returnType;
	}

	
	
}
