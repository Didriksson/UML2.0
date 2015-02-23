package Figures;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import runner.UMLComponentController;
import UML.Components.UMLClassVariable;
import UML.Components.UMLMethod;

public class ClassFigure extends JPanel {

    private JList listMethods, listVariables;
    private JScrollPane vaiableScroll, methodScroll;
    private JTextField classNameField;
    private JPanel umlPanel, classPanel;
    private GridBagConstraints gbc;
    private UMLComponentController controller;
    private JButton addMethodButton;

    public ClassFigure(UMLComponentController controller) {
	this.setLayout(new GridBagLayout());
	this.controller = controller;
	gbc = new GridBagConstraints();
	createAndAddComponents();

    }

    private void createAndAddComponents() {
	listMethods = new JList<UMLMethod>(new Vector<UMLMethod>(
		controller.getMethods()));
	listVariables = new JList<UMLClassVariable>(
		new Vector<UMLClassVariable>(controller.getVariables()));

	
	listMethods.addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent e){
		JList list = (JList)e.getSource();

		if(e.getClickCount() == 2)
		{
		    int index = list.locationToIndex(e.getPoint());
		    String name = JOptionPane.showInputDialog(null, "Rename method: ");
		    if(!isNullOrEmpty(name))
			controller.getMethods().get(index).setMethodName(name);
		}
	    }

	   
	});
	
	vaiableScroll = new JScrollPane(listVariables);
	methodScroll = new JScrollPane(listMethods);

	classNameField = new JTextField("ClassName");
	classNameField.setHorizontalAlignment(JLabel.CENTER);
	classNameField.setText(controller.getClassName());
	classNameField.getDocument().addDocumentListener(
		new DocumentListener() {

		    @Override
		    public void removeUpdate(DocumentEvent e) {
			controller.setName(classNameField.getText());
		    }

		    @Override
		    public void insertUpdate(DocumentEvent e) {
			controller.setName(classNameField.getText());
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		    }
		});

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
	addMethodButton = new JButton("");
	addMethodButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		controller.newMethod();
		updatateList();
	    }
	});
	this.add(addMethodButton);

    }

    protected void updatateList() {
	this.removeAll();
	createAndAddComponents();
	repaint();
	revalidate();
    }
    private boolean isNullOrEmpty(String name) {
	return name == null || name.isEmpty();
    }
}
