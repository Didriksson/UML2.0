package Figures;

import java.awt.Point;

import javax.swing.JPanel;

import UML.Components.UMLComponent;
import Controller.UMLComponentController;

public class GUIComponent extends JPanel {
	protected UMLComponentController controller;

	public UMLComponentController getController() {
		return controller;
	}

	public UMLComponent getUMLComponent() {
		return controller.getComponent();
	}

}
