package Figures;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.UMLComponentController;
import GUI_View.ComponentManipulationToolbar;
import UML.Components.UMLClassVariable;
import UML.Components.UMLMethod;

public class ClassFigure extends GUIComponent {
	private static final long serialVersionUID = -3733269893885327522L;
	
	private ComponentManipulationToolbar componentTools;
	private JList<UMLMethod> listMethods;
	private JList<UMLClassVariable> listVariables;
	private JScrollPane vaiableScroll, methodScroll;
	private JTextField classNameField;
	private GridBagConstraints gbc;

	public ClassFigure(ComponentManipulationToolbar componentTools, UMLComponentController c) {
		this.setLayout(new GridBagLayout());
		controller = c;
		this.componentTools = componentTools;
		gbc = new GridBagConstraints();
		createAndAddComponents();
	}

	private void createAndAddComponents() {
		listMethods = new JList<UMLMethod>(new Vector<UMLMethod>(
				controller.getMethods()));
		listVariables = new JList<UMLClassVariable>(
				new Vector<UMLClassVariable>(controller.getVariables()));

		listVariables.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				listMethods.clearSelection();
				
				componentTools.updateMenyToolbar(1);
				
				JList<?> list = (JList<?>) e.getSource();
				int index = list.locationToIndex(e.getPoint());
				if (index >= 0) {
					String text = controller.getVariables().get(index)
							.getName();
					controller.setIndexOfVariableList(index, text, !listMethods.isSelectionEmpty());
				}
				if (e.getClickCount() == 2 && !listVariables.isSelectionEmpty()) {
					String variableName = JOptionPane.showInputDialog(null,
							"Rename Variable: ");
					if (!isNullOrEmpty(variableName))
						controller.getVariables().get(index)
								.setvariableName(variableName);
				}
			}


		});

		listMethods.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				listVariables.clearSelection();
				
				componentTools.updateMenyToolbar(0);
				
				JList<?> list = (JList<?>) e.getSource();
				int index = list.locationToIndex(e.getPoint());
				
				if (index >= 0) {
					String text = controller.getMethods().get(index)
							.getMethodName();
					controller.setIndexOfMethodList(index, text, !listMethods.isSelectionEmpty());
				}
				if (e.getClickCount() == 2 && !listMethods.isSelectionEmpty()) {
					String methodName = JOptionPane.showInputDialog(null,
							"Rename method: ");
					if (!isNullOrEmpty(methodName))
						controller.getMethods().get(index)
								.setMethodName(methodName);
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

	}

	public void updatateList() {
		this.removeAll();
		createAndAddComponents();
		repaint();
		revalidate();
	}

	private boolean isNullOrEmpty(String name) {
		return name == null || name.isEmpty();
	}
}
