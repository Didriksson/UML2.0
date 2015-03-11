package GUI_View;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;

public class RadioButtonVisability extends JPanel {
	private static final long serialVersionUID = 1L;
	
	
	private IVisability listener;
	private ButtonGroup buttonGroup;
	private JRadioButton visabilityPrivate;
	private JRadioButton visabilityPublic;
	private JRadioButton visabilityProtected;
	private JRadioButton visabilityPackage;

	public TitledBorder titledBorder;
	
	public RadioButtonVisability(IVisability listener) {
		this.listener = listener;
		init();
	}
	

	private void init() {
		this.setLayout(new MigLayout("", "[grow, fill]", ""));
		titledBorder = BorderFactory.createTitledBorder("Visability:");
		this.setBorder(titledBorder);
		createComponents();
	}

	private void createComponents() {
		buttonGroup = new ButtonGroup();
		visabilityPublic = radioButtonSetup(Constants.PUBLIC_RETURN_TYPE);
		visabilityPrivate = radioButtonSetup(Constants.PRIVATE_RETURN_TYPE);
		visabilityProtected = radioButtonSetup(Constants.PROTECTED_RETURN_TYPE);
		visabilityPackage = radioButtonSetup(Constants.PACKAGE_RETURN_TYPE);
		
		manipulateComponents();
	}

	private void manipulateComponents() {
		this.add(visabilityPublic);
		this.add(visabilityPrivate);
		this.add(visabilityProtected);
		this.add(visabilityPackage);

		visabilityPublic.addActionListener(e -> setVisabilityIdentyfier(Constants.PUBLIC_RETURN_TYPE));
		visabilityPrivate.addActionListener(e -> setVisabilityIdentyfier(Constants.PRIVATE_RETURN_TYPE));
		visabilityProtected.addActionListener(e -> setVisabilityIdentyfier(Constants.PROTECTED_RETURN_TYPE));
		visabilityPackage.addActionListener(e -> setVisabilityIdentyfier(Constants.PACKAGE_RETURN_TYPE));
		visabilityPublic.doClick();
	}
	
	
	private void setVisabilityIdentyfier(String returnType) {			
		listener.updateVisability(returnType);
	}

	private JRadioButton radioButtonSetup(String title) {
		JRadioButton rbutton = new JRadioButton(title);
		buttonGroup.add(rbutton);
		return rbutton;
	}
	
}