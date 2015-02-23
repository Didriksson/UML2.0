package runner;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import UML.Components.UMLClassVariable;
import UML.Components.UMLMethod;

public class ClassFigure extends JPanel {

	private JList listMethods, listVariables; 
	private JScrollPane vaiableScroll, methodScroll;
	private JTextField classNameField;
	private JPanel umlPanel, classPanel;
	private GridBagConstraints gbc;
	
	public ClassFigure(UMLController controller) {
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		listMethods = new JList<UMLMethod>(controller.getMethods().toArray(new UMLMethod[controller.getMethods().size()]));
		listVariables = new JList<UMLClassVariable>(controller.getVariables().toArray(new UMLClassVariable[controller.getVariables().size()]));	
		
		vaiableScroll = new JScrollPane(listVariables);
		methodScroll = new JScrollPane(listMethods);	
		
		classNameField = new JTextField("ClassName");	
		classNameField.setHorizontalAlignment(JLabel.CENTER);	
		classNameField.setText(controller.getClassName());
			
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.1;
		gbc.weightx = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;	
		this.add(classNameField, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.45;
		gbc.weightx = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(vaiableScroll, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.45;
		gbc.weightx = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(methodScroll, gbc);
		
	}
	
}
